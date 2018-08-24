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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class I18n {

    private static final String L10N_FILE_PATH_PREFIX = "i18n/io.atlasmap.localized";
    private static final String L10N_META_FILE_PATH_PREFIX = "META-INF/" + L10N_FILE_PATH_PREFIX;
    private static final char LOCALE_SEPARATOR = '_';
    private static final String L10N_FILE_EXT = ".text";
    private static final String DEFAULT_L10N_COMMENT = "The text in this file is localized for US English"
                                                       + " and should be moved to appropriate localization files"
                                                       + " in the module containing the calls to localize said text";

    private static final Logger LOG = LoggerFactory.getLogger(I18n.class);

    private static String localL10nFilePathPrefix;
    private static Map<Locale, Map<String, String>> l10nTextByKeyByLocaleCache = new HashMap<>();

    public static String localL10nFilePathPrefix() {
        if (localL10nFilePathPrefix == null) {
            setLocalL10nFilePathPrefix(null);
        }
        return localL10nFilePathPrefix;
    }

    public static void setLocalL10nFilePathPrefix(String folder) {
        Path path;
        if (folder == null) {
            path = Paths.get(System.getProperty("user.home"), ".io.atlasmap");
        } else {
            path = Paths.get(folder);
        }
        path = path.resolve(L10N_FILE_PATH_PREFIX);
        localL10nFilePathPrefix = path.toString();
    }

    public static String localize(String text, Object... arguments) {
        return localize(null, text, arguments);
    }

    public static String localize(Locale locale, String text, Object... arguments) {
        VerifyArgument.isNotEmpty("text", text);

        if (locale == null) {
            locale = Locale.getDefault();
        }

        // Ensure cache contains map for locale
        Map<String, String> l10nTextByKeyCache = l10nTextByKeyByLocaleCache.get(locale);
        if (l10nTextByKeyCache == null) {
            l10nTextByKeyCache = new WeakHashMap<>();
            l10nTextByKeyByLocaleCache.put(locale, l10nTextByKeyCache);
        }

        // Check if l10n has been cached
        String l10nText = l10nTextByKeyCache.get(text);
        if (l10nText != null) {
            return String.format(l10nText, arguments);
        }

        // Determine all possible local l10n paths and look for text in them
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
            l10nText = props.getProperty(text);
            if (l10nText != null) {
                l10nTextByKeyCache.put(text, l10nText);
                return String.format(l10nText, arguments);
            }
        }

        // Determine all possible local l10n paths and look for text in them
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
                l10nText = props.getProperty(text);
                if (l10nText != null) {
                    l10nTextByKeyCache.put(text, l10nText);
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
        l10nText = props.getProperty(text);
        if (l10nText != null) {
            l10nTextByKeyCache.put(text, l10nText);
            return String.format(l10nText, arguments);
        }

        // Create l10n entry
        props.setProperty(text, text);
        File file = new File(defaultLocalL10nFilePathName);
        file.getParentFile().mkdirs();
        try (OutputStream stream = new FileOutputStream(file)) {
            props.store(stream, DEFAULT_L10N_COMMENT);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write default local l10n file " + defaultLocalL10nFilePathName, e);
        }

        // Determine calling class and place the text and class in comments of default messages file so an appropriate one
        // can be created for the calling class's module
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StringBuilder builder = new StringBuilder("Default localization created for '");
        builder.append(text);
        builder.append("', io.atlasmap stack trace:");
        for (int index = 1; index < stack.length; index++) {
            StackTraceElement caller = stack[index];
            if (!caller.getClassName().equals(I18n.class.getName())) {
                builder.append("\n\t");
                builder.append(caller);
                for (index++; index < stack.length; index++) {
                    caller = stack[index];
                    if (caller.getClassName().startsWith("io.atlasmap")) {
                        builder.append("\n\t");
                        builder.append(caller);
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        LOG.trace(builder.toString());

        // Use text that was passed in, without localization
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

    private I18n() {}
}
