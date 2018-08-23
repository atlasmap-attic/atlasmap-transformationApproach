/**
 * Copyright (C) 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.spi.v3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class I18n {

    private static final String L10N_FILE_PATH_PREFIX = "i18n/io.atlasmap.localized";
    private static final String L10N_META_FILE_PATH_PREFIX = "META-INF/" + L10N_FILE_PATH_PREFIX;
    private static final char LOCALE_SEPARATOR = '_';
    private static final String L10N_FILE_EXT = ".messages";

    private static String localL10nFilePathPrefix;

    public static String localL10nFilePathPrefix() {
        if (localL10nFilePathPrefix == null) {
            setLocalL10nFilePathPrefix(null);
        }
        return localL10nFilePathPrefix;
    }

    public static void setLocalL10nFilePathPrefix(String folder) {
        localL10nFilePathPrefix = folder == null ? System.getProperty("user.home"): folder;
        if (!localL10nFilePathPrefix.endsWith("/")) {
            localL10nFilePathPrefix += "/";
        }
        if (folder == null) {
            localL10nFilePathPrefix += ".io.atlasmap/";
        }
        localL10nFilePathPrefix += L10N_FILE_PATH_PREFIX;
    }

    public static String localize(String text, Object... arguments) {
        return localize(null, text, arguments);
    }

    public static String localize(Locale locale, String text, Object... arguments) {
        VerifyArgument.isNotEmpty("text", text);
        if (locale == null) {
            locale = Locale.getDefault();
        }

        // Determine all possible local l10n paths and look for text, converted to key, in them
        List<String> l10nPathNames = new ArrayList<>();
        populateL10nPathNames(l10nPathNames, locale, localL10nFilePathPrefix());
        for (String pathName : l10nPathNames) {
            Properties props = new Properties();
            try (InputStream stream = new FileInputStream(pathName)) {
                props.load(stream);
            } catch (@SuppressWarnings("unused") FileNotFoundException okay) {
            } catch (IOException e) {
                throw new RuntimeException("Unable to read local l10n file " + pathName, e);
            }
            String l10nText = props.getProperty(key(text));
            if (l10nText != null) {
                return String.format(l10nText, arguments);
            }
        }

        // Determine all possible local l10n paths and look for text, converted to key, in them
        l10nPathNames.clear();
        populateL10nPathNames(l10nPathNames, locale, L10N_META_FILE_PATH_PREFIX);
        for (String pathName : l10nPathNames) {
            Enumeration<URL> urls;
            try {
                urls = Thread.currentThread().getContextClassLoader().getResources(pathName);
            } catch (IOException e) {
                throw new RuntimeException("Unable to access l10n resource files at " + pathName, e);
            }
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                Properties props = new Properties();
                try (InputStream stream = url.openStream()) {
                    props.load(stream);
                } catch (IOException e) {
                    throw new RuntimeException("Unable to read l10n resource file " + url, e);
                }
                String l10nText = props.getProperty(key(text));
                if (l10nText != null) {
                    return String.format(l10nText, arguments);
                }
            }
        }

        // Look for text in default local l10n file
        String defaultLocalL10nFilePathName = localL10nFilePathPrefix() + "Default_" + locale.toString() + L10N_FILE_EXT;
        Properties props = new Properties();
        try (InputStream stream = new FileInputStream(defaultLocalL10nFilePathName)) {
            props.load(stream);
        } catch (@SuppressWarnings("unused") FileNotFoundException okay) {
        } catch (IOException e) {
            throw new RuntimeException("Unable to read default local l10n file " + defaultLocalL10nFilePathName, e);
        }
        String l10nText = props.getProperty(key(text));
        if (l10nText != null) {
            return String.format(l10nText, arguments);
        }

        // Create l10n entry
        props.setProperty(key(text), text);
        File file = new File(defaultLocalL10nFilePathName);
        file.getParentFile().mkdirs();
        try (OutputStream stream = new FileOutputStream(file)) {
            props.store(stream, null);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write default local l10n file " + defaultLocalL10nFilePathName, e);
        }

        // Use text that was passed in
        return String.format(text, arguments);
    }

    private static void populateL10nPathNames(List<String> l10nPathNames, Locale locale, String pathPrefix) {
        String localeVariant = LOCALE_SEPARATOR + locale.toString();
        int ndx = localeVariant.lastIndexOf(LOCALE_SEPARATOR);
        while (ndx >= 0) {
            String path = pathPrefix + localeVariant + L10N_FILE_EXT;
            l10nPathNames.add( path );
            localeVariant = localeVariant.substring(0, ndx);
            ndx = localeVariant.lastIndexOf(LOCALE_SEPARATOR);
        }
        l10nPathNames.add(pathPrefix + L10N_FILE_EXT);
    }

    private static String key(String text) {
        return text.replaceAll("\\s", "_");
    }
}
