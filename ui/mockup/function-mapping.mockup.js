function setSourcesVisibility(visibility) {
  sourcesRemoveIcon.style.visibility = visibility;
}

setSourcesVisibility("hidden");
sourcesHeader.addEventListener('mouseover', function() {
  setSourcesVisibility("visible");
});
sourcesHeader.addEventListener('mouseout', function() {
  setSourcesVisibility("hidden");
});

function setTargetsVisibility(visibility) {
  targetsRemoveIcon.style.visibility = visibility;
}

setTargetsVisibility("hidden");
targetsHeader.addEventListener('mouseover', function() {
  setTargetsVisibility("visible");
});
targetsHeader.addEventListener('mouseout', function() {
  setTargetsVisibility("hidden");
});

function setBooksSourceVisibility(visibility) {
  booksSourceEditIcon.style.visibility = visibility;
  booksSourceRemoveIcon.style.visibility = visibility;
}

setBooksSourceVisibility("hidden");
booksSourceHeader.addEventListener('mouseover', function() {
  setBooksSourceVisibility("visible");
});
booksSourceHeader.addEventListener('mouseout', function() {
  setBooksSourceVisibility("hidden");
});

function setPropsSourceVisibility(visibility) {
  propsAddIcon.style.visibility = visibility;
}

setPropsSourceVisibility("hidden");
propsHeader.addEventListener('mouseover', function() {
  setPropsSourceVisibility("visible");
});
propsHeader.addEventListener('mouseout', function() {
  setPropsSourceVisibility("hidden");
});

function setBooksTargetVisibility(visibility) {
  booksTargetEditIcon.style.visibility = visibility;
  booksTargetRemoveIcon.style.visibility = visibility;
}

setBooksTargetVisibility('hidden');
booksTargetHeader.addEventListener('mouseover', function() {
  setBooksTargetVisibility('visible');
});
booksTargetHeader.addEventListener('mouseout', function() {
  setBooksTargetVisibility('hidden');
});

const authorPath = 'Books:bookstore/book[*]/author';
const firstNamePath = 'Books:bookstore/book[*]/firstName';
const lastNamePath = 'Books:bookstore/book[*]/lastName';
const middleInitialPath = 'Books:bookstore/book[*]/middleInitial';

const combineExpandedInput = "Combine( " +
  "Source 1: " + lastNamePath + ", " +
  "Source 2: ', ', " +
  "Source 3: " + firstNamePath + ", " +
  "Source 4: ' ', " +
  "Source 5: " + middleInitialPath + ", " +
  "Delimiter: '' " +
")";

function setCombineControlVisibility(visibility) {
  combineEditIcon.style.visibility = visibility;
  combineRemoveIcon.style.visibility = visibility;
  combineSource1AddFuncIcon.style.visibility = visibility;
  combineSource2AddFuncIcon.style.visibility = visibility;
  combineSource3AddFuncIcon.style.visibility = visibility;
  combineSource3RemoveIcon.style.visibility = visibility;
  combineSource4AddFuncIcon.style.visibility = visibility;
  combineSource4RemoveIcon.style.visibility = visibility;
  combineSource5AddFuncIcon.style.visibility = visibility;
  combineSource5RemoveIcon.style.visibility = visibility;
  combineAddSourceLink.style.visibility = visibility;
  combineDelimiterAddFuncIcon.style.visibility = visibility;
}

tippy('#combineSource1Input', {
  content: lastNamePath,
});
tippy('#combineSource3Input', {
  content: firstNamePath,
});
tippy('#combineSource5Input', {
  content: middleInitialPath,
});

setCombineControlVisibility("hidden");
combine.addEventListener('mouseover', function() {
  setCombineControlVisibility("visible");
  lowercase.classList.add('dependent');
});
combine.addEventListener('mouseout', function() {
  setCombineControlVisibility("hidden");
  lowercase.classList.remove('dependent');
});
combine.addEventListener('focus', function() {
  setCombineControlVisibility("visible");
  lowercase.classList.add('dependent');
}, true);
combine.addEventListener('blur', function() {
  setCombineControlVisibility("hidden");
  lowercase.classList.remove('dependent');
}, true);
combineSource1Input.addEventListener('mouseover', function() {
  lastNameSource.classList.add('dependency');
});
combineSource1Input.addEventListener('mouseout', function() {
  lastNameSource.classList.remove('dependency');
});
combineSource1Input.addEventListener('focus', function() {
  combineSource1Input.value = lastNamePath;
  lastNameSource.classList.add('dependency');
});
combineSource1Input.addEventListener('blur', function() {
  combineSource1Input.value = "lastName";
  lastNameSource.classList.remove('dependency');
});
combineSource3Input.addEventListener('mouseover', function() {
  firstNameSource.classList.add('dependency');
});
combineSource3Input.addEventListener('mouseout', function() {
  firstNameSource.classList.remove('dependency');
});
combineSource3Input.addEventListener('focus', function() {
  combineSource3Input.value = firstNamePath;
  firstNameSource.classList.add('dependency');
});
combineSource3Input.addEventListener('blur', function() {
  combineSource3Input.value = "firstName";
  firstNameSource.classList.remove('dependency');
});
combineSource5Input.addEventListener('mouseover', function() {
  middleInitialSource.classList.add('dependency');
});
combineSource5Input.addEventListener('mouseout', function() {
  middleInitialSource.classList.remove('dependency');
});
combineSource5Input.addEventListener('focus', function() {
  combineSource5Input.value = middleInitialPath;
  middleInitialSource.classList.add('dependency');
});
combineSource5Input.addEventListener('blur', function() {
  combineSource5Input.value = "middleInitial";
  middleInitialSource.classList.remove('dependency');
});

tippy('#lowercaseSourceInput', {
  content: combineExpandedInput,
});

const lowercaseExpandedInput = "Lowercase( " +
  "Source: " + combineExpandedInput + " " +
")";

function setLowercaseControlVisibility(visibility) {
  lowercaseEditIcon.style.visibility = visibility;
  lowercaseRemoveIcon.style.visibility = visibility;
  lowercaseSourceAddFuncIcon.style.visibility = visibility;
}

setLowercaseControlVisibility("hidden");
lowercase.addEventListener('mouseover', function() {
  setLowercaseControlVisibility("visible");
  map1.classList.add('dependent');
});
lowercase.addEventListener('mouseout', function() {
  setLowercaseControlVisibility("hidden");
  map1.classList.remove('dependent');
});
lowercase.addEventListener('focus', function() {
  setLowercaseControlVisibility("visible");
  map1.classList.add('dependent');
}, true);
lowercase.addEventListener('blur', function() {
  setLowercaseControlVisibility("hidden");
  map1.classList.remove('dependent');
}, true);
lowercaseSourceInput.addEventListener('mouseover', function() {
  if (lowercaseSourceInput.value == 'author') {
    authorSource.classList.add('dependency');
  } else {
    combine.classList.add('dependency');
  }
});
lowercaseSourceInput.addEventListener('mouseout', function() {
  if (lowercaseSourceInput.value == 'author') {
    authorSource.classList.remove('dependency');
  } else {
    combine.classList.remove('dependency');
  }
});
lowercaseSourceInput.addEventListener('focus', function() {
  if (lowercaseSourceInput.value == 'author') {
    lowercaseSourceInput.value = authorPath;
    authorSource.classList.add('dependency');
  } else {
    lowercaseSourceInput.value = combineExpandedInput;
    combine.classList.add('dependency');
  }
});
lowercaseSourceInput.addEventListener('blur', function() {
  if (lowercaseSourceInput.value == authorPath) {
    lowercaseSourceInput.value = 'author';
    authorSource.classList.remove('dependency');
  } else {
    lowercaseSourceInput.value = "Combine(...)";
    combine.classList.remove('dependency');
  }
});

const map1ExpandedInput = "Map( " +
  "Source: " + lowercaseExpandedInput + ", " +
  "Target: " + authorPath + " " +
")";

function setMap1ControlVisibility(visibility) {
  map1EditIcon.style.visibility = visibility;
  map1RemoveIcon.style.visibility = visibility;
  map1SourceAddFuncIcon.style.visibility = visibility;
}

setMap1ControlVisibility("hidden");
map1.addEventListener('mouseover', function() {
  setMap1ControlVisibility("visible");
  if (mapIfFuncDropdown.value == 'MapIf') {
    mapIf.classList.add('dependent');
  }
});
map1.addEventListener('mouseout', function() {
  setMap1ControlVisibility("hidden");
  mapIf.classList.remove('dependent');
});
map1.addEventListener('focus', function() {
  setMap1ControlVisibility("visible");
  if (mapIfFuncDropdown.value == 'MapIf') {
    mapIf.classList.add('dependent');
  }
}, true);
map1.addEventListener('blur', function() {
  setMap1ControlVisibility("hidden");
  mapIf.classList.remove('dependent');
}, true);
map1SourceInput.addEventListener('mouseover', function() {
  if (map1SourceInput.value == 'author') {
    authorSource.classList.add('dependency');
  } else if (mapIfFuncDropdown.value == 'MapIf') {
    lowercase.classList.add('dependency');
  }
});
map1SourceInput.addEventListener('mouseout', function() {
  if (map1SourceInput.value == 'author') {
    authorSource.classList.remove('dependency');
  } else {
    lowercase.classList.remove('dependency');
  }
});
map1SourceInput.addEventListener('focus', function() {
  map1SourceInput.value = lowercaseExpandedInput;
  if (mapIfFuncDropdown.value == 'MapIf') {
    lowercase.classList.add('dependency');
  }
});
map1SourceInput.addEventListener('blur', function() {
  map1SourceInput.value = "Lowercase(...)";
  lowercase.classList.remove('dependency');
});
map1TargetInput.addEventListener('mouseover', function() {
  if (map1TargetInput.value != '') {
    authorTarget.classList.add('dependency');
    if (authorToAuthor.style.visibility == 'visible') {
      authorToAuthor.style.stroke = 'dodgerBlue';
    } else {
      svg.appendChild(firstNameToAuthor);
      svg.appendChild(lastNameToAuthor);
      svg.appendChild(middleInitialToAuthor);
      firstNameToAuthor.style.stroke = 'dodgerBlue';
      lastNameToAuthor.style.stroke = 'dodgerBlue';
      middleInitialToAuthor.style.stroke = 'dodgerBlue';
    }
  }
});
map1TargetInput.addEventListener('mouseout', function() {
  if (map1TargetInput.value != '') {
    authorTarget.classList.remove('dependency');
    if (authorToAuthor.style.visibility == 'visible') {
      authorToAuthor.style.stroke = 'silver';
    } else {
      firstNameToAuthor.style.stroke = 'silver';
      lastNameToAuthor.style.stroke = 'silver';
      middleInitialToAuthor.style.stroke = 'silver';
    }
  }
});
map1TargetInput.addEventListener('focus', function() {
  if (map1TargetInput.value != '') {
    map1TargetInput.value = "Books:bookstore/book[*]/author";
    authorTarget.classList.add('dependency');
    if (authorToAuthor.style.visibility == 'visible') {
      authorToAuthor.style.stroke = 'dodgerBlue';
    } else {
      svg.appendChild(firstNameToAuthor);
      svg.appendChild(lastNameToAuthor);
      svg.appendChild(middleInitialToAuthor);
      firstNameToAuthor.style.stroke = 'dodgerBlue';
      lastNameToAuthor.style.stroke = 'dodgerBlue';
      middleInitialToAuthor.style.stroke = 'dodgerBlue';
    }
  }
});
map1TargetInput.addEventListener('blur', function() {
  if (map1TargetInput.value != '') {
    map1TargetInput.value = "author";
    authorTarget.classList.remove('dependency');
    if (authorToAuthor.style.visibility == 'visible') {
      authorToAuthor.style.stroke = 'silver';
    } else {
      firstNameToAuthor.style.stroke = 'silver';
      lastNameToAuthor.style.stroke = 'silver';
      middleInitialToAuthor.style.stroke = 'silver';
    }
  }
});

tippy('#map1SourceInput', {
  content: lowercaseExpandedInput,
});
tippy('#map1TargetInput', {
  content: authorPath,
});

const splitExpandedInput = "Split( " +
  "Source: " + lowercaseExpandedInput + ", " +
  "Delimiter: ' ' " +
")";

function setSplitControlVisibility(visibility) {
  splitEditIcon.style.visibility = visibility;
  splitRemoveIcon.style.visibility = visibility;
  splitSourceAddFuncIcon.style.visibility = visibility;
  splitDelimiterAddFuncIcon.style.visibility = visibility;
}

setSplitControlVisibility("hidden");
split.addEventListener('mouseover', function() {
  setSplitControlVisibility("visible");
  crop.classList.add('dependent');
  map3.classList.add('dependent');
  map4.classList.add('dependent');
});
split.addEventListener('mouseout', function() {
  setSplitControlVisibility("hidden");
  crop.classList.remove('dependent');
  map3.classList.remove('dependent');
  map4.classList.remove('dependent');
});
split.addEventListener('focus', function() {
  setSplitControlVisibility("visible");
  crop.classList.add('dependent');
  map3.classList.add('dependent');
  map4.classList.add('dependent');
}, true);
split.addEventListener('blur', function() {
  setSplitControlVisibility("hidden");
  crop.classList.remove('dependent');
  map3.classList.remove('dependent');
  map4.classList.remove('dependent');
}, true);
splitSourceInput.addEventListener('mouseover', function() {
  lowercase.classList.add('dependency');
});
splitSourceInput.addEventListener('mouseout', function() {
  lowercase.classList.remove('dependency');
});
splitSourceInput.addEventListener('focus', function() {
  splitSourceInput.value = lowercaseExpandedInput;
  lowercase.classList.add('dependency');
});
splitSourceInput.addEventListener('blur', function() {
  splitSourceInput.value = "Lowercase(...)";
  lowercase.classList.remove('dependency');
});

tippy('#splitSourceInput', {
  content: lowercaseExpandedInput,
});

const cropExpandedInput = "Crop( " +
  "Source: " + splitExpandedInput + "[1], " +
  "From: End, " +
  "Characters: 1 " +
")";

function setCropControlVisibility(visibility) {
  cropEditIcon.style.visibility = visibility;
  cropRemoveIcon.style.visibility = visibility;
  cropSourceAddFuncIcon.style.visibility = visibility;
  cropCharactersAddFuncIcon.style.visibility = visibility;
}

setCropControlVisibility("hidden");
crop.addEventListener('mouseover', function() {
  setCropControlVisibility("visible");
  map2.classList.add('dependent');
});
crop.addEventListener('mouseout', function() {
  setCropControlVisibility("hidden");
  map2.classList.remove('dependent');
});
crop.addEventListener('focus', function() {
  setCropControlVisibility("visible");
  map2.classList.add('dependent');
}, true);
crop.addEventListener('blur', function() {
  setCropControlVisibility("hidden");
  map2.classList.remove('dependent');
}, true);
cropSourceInput.addEventListener('mouseover', function() {
  split.classList.add('dependency');
});
cropSourceInput.addEventListener('mouseout', function() {
  split.classList.remove('dependency');
});
cropSourceInput.addEventListener('focus', function() {
  cropSourceInput.value = splitExpandedInput;
  split.classList.add('dependency');
});
cropSourceInput.addEventListener('blur', function() {
  cropSourceInput.value = "Split Full Name";
  split.classList.remove('dependency');
});

tippy('#cropSourceInput', {
  content: splitExpandedInput + '[1]',
});

const map2ExpandedInput = "Map( " +
  "Source: " + cropExpandedInput + ", " +
  "Target: " + lastNamePath + " " +
")";

function setMap2ControlVisibility(visibility) {
  map2EditIcon.style.visibility = visibility;
  map2RemoveIcon.style.visibility = visibility;
  map2SourceAddFuncIcon.style.visibility = visibility;
}

setMap2ControlVisibility("hidden");
map2.addEventListener('mouseover', function() {
  setMap2ControlVisibility("visible");
});
map2.addEventListener('mouseout', function() {
  setMap2ControlVisibility("hidden");
});
map2.addEventListener('focus', function() {
  setMap2ControlVisibility("visible");
}, true);
map2.addEventListener('blur', function() {
  setMap2ControlVisibility("hidden");
}, true);
map2SourceInput.addEventListener('mouseover', function() {
  crop.classList.add('dependency');
});
map2SourceInput.addEventListener('mouseout', function() {
  crop.classList.remove('dependency');
});
map2SourceInput.addEventListener('focus', function() {
  map2SourceInput.value = cropExpandedInput;
  crop.classList.add('dependency');
});
map2SourceInput.addEventListener('blur', function() {
  map2SourceInput.value = "Crop(...)";
  crop.classList.remove('dependency');
});
map2TargetInput.addEventListener('mouseover', function() {
  lastNameTarget.classList.add('dependency');
  svg.appendChild(firstNameToLastName);
  svg.appendChild(lastNameToLastName);
  svg.appendChild(middleInitialToLastName);
  firstNameToLastName.style.stroke = 'dodgerBlue';
  lastNameToLastName.style.stroke = 'dodgerBlue';
  middleInitialToLastName.style.stroke = 'dodgerBlue';
});
map2TargetInput.addEventListener('mouseout', function() {
  lastNameTarget.classList.remove('dependency');
  firstNameToLastName.style.stroke = 'silver';
  lastNameToLastName.style.stroke = 'silver';
  middleInitialToLastName.style.stroke = 'silver';
});
map2TargetInput.addEventListener('focus', function() {
  map2TargetInput.value = "Books:bookstore/book[*]/lastName";
  lastNameTarget.classList.add('dependency');
  svg.appendChild(firstNameToLastName);
  svg.appendChild(lastNameToLastName);
  svg.appendChild(middleInitialToLastName);
  firstNameToLastName.style.stroke = 'dodgerBlue';
  lastNameToLastName.style.stroke = 'dodgerBlue';
  middleInitialToLastName.style.stroke = 'dodgerBlue';
});
map2TargetInput.addEventListener('blur', function() {
  map2TargetInput.value = "lastName";
  lastNameTarget.classList.remove('dependency');
  firstNameToLastName.style.stroke = 'silver';
  lastNameToLastName.style.stroke = 'silver';
  middleInitialToLastName.style.stroke = 'silver';
});

tippy('#map2SourceInput', {
  content: cropExpandedInput,
});
tippy('#map2TargetInput', {
  content: lastNamePath,
});

const map3ExpandedInput = "Map( " +
  "Source: " + splitExpandedInput + ", " +
  "Target: " + firstNamePath + " " +
")";

function setMap3ControlVisibility(visibility) {
  map3EditIcon.style.visibility = visibility;
  map3RemoveIcon.style.visibility = visibility;
  map3SourceAddFuncIcon.style.visibility = visibility;
}

setMap3ControlVisibility("hidden");
map3.addEventListener('mouseover', function() {
  setMap3ControlVisibility("visible");
});
map3.addEventListener('mouseout', function() {
  setMap3ControlVisibility("hidden");
});
map3.addEventListener('focus', function() {
  setMap3ControlVisibility("visible");
}, true);
map3.addEventListener('blur', function() {
  setMap3ControlVisibility("hidden");
}, true);
map3SourceInput.addEventListener('mouseover', function() {
  split.classList.add('dependency');
});
map3SourceInput.addEventListener('mouseout', function() {
  split.classList.remove('dependency');
});
map3SourceInput.addEventListener('focus', function() {
  map3SourceInput.value = splitExpandedInput;
  split.classList.add('dependency');
});
map3SourceInput.addEventListener('blur', function() {
  map3SourceInput.value = "Split Full Name";
  split.classList.remove('dependency');
});
map3TargetInput.addEventListener('mouseover', function() {
  firstNameTarget.classList.add('dependency');
  svg.appendChild(firstNameToFirstName);
  svg.appendChild(lastNameToFirstName);
  svg.appendChild(middleInitialToFirstName);
  firstNameToFirstName.style.stroke = 'dodgerBlue';
  lastNameToFirstName.style.stroke = 'dodgerBlue';
  middleInitialToFirstName.style.stroke = 'dodgerBlue';
});
map3TargetInput.addEventListener('mouseout', function() {
  firstNameTarget.classList.remove('dependency');
  firstNameToFirstName.style.stroke = 'silver';
  lastNameToFirstName.style.stroke = 'silver';
  middleInitialToFirstName.style.stroke = 'silver';
});
map3TargetInput.addEventListener('focus', function() {
  map3TargetInput.value = "Books:bookstore/book[*]/firstName";
  firstNameTarget.classList.add('dependency');
  svg.appendChild(firstNameToFirstName);
  svg.appendChild(lastNameToFirstName);
  svg.appendChild(middleInitialToFirstName);
  firstNameToFirstName.style.stroke = 'dodgerBlue';
  lastNameToFirstName.style.stroke = 'dodgerBlue';
  middleInitialToFirstName.style.stroke = 'dodgerBlue';
});
map3TargetInput.addEventListener('blur', function() {
  map3TargetInput.value = "firstName";
  firstNameTarget.classList.remove('dependency');
  firstNameToFirstName.style.stroke = 'silver';
  lastNameToFirstName.style.stroke = 'silver';
  middleInitialToFirstName.style.stroke = 'silver';
});

tippy('#map3SourceInput', {
  content: splitExpandedInput + '[2]',
});
tippy('#map3TargetInput', {
  content: firstNamePath,
});

const map4ExpandedInput = "Map( " +
  "Source: " + splitExpandedInput + ", " +
  "Target: " + middleInitialPath + " " +
")";

function setMap4ControlVisibility(visibility) {
  map4EditIcon.style.visibility = visibility;
  map4RemoveIcon.style.visibility = visibility;
  map4SourceAddFuncIcon.style.visibility = visibility;
}

setMap4ControlVisibility("hidden");
map4.addEventListener('mouseover', function() {
  setMap4ControlVisibility("visible");
});
map4.addEventListener('mouseout', function() {
  setMap4ControlVisibility("hidden");
});
map4.addEventListener('focus', function() {
  setMap4ControlVisibility("visible");
}, true);
map4.addEventListener('blur', function() {
  setMap4ControlVisibility("hidden");
}, true);
map4SourceInput.addEventListener('mouseover', function() {
  split.classList.add('dependency');
});
map4SourceInput.addEventListener('mouseout', function() {
  split.classList.remove('dependency');
});
map4SourceInput.addEventListener('focus', function() {
  map4SourceInput.value = splitExpandedInput;
  split.classList.add('dependency');
});
map4SourceInput.addEventListener('blur', function() {
  map4SourceInput.value = "Split Full Name";
  split.classList.remove('dependency');
});
map4TargetInput.addEventListener('mouseover', function() {
  middleInitialTarget.classList.add('dependency');
  svg.appendChild(firstNameToMiddleInitial);
  svg.appendChild(lastNameToMiddleInitial);
  svg.appendChild(middleInitialToMiddleInitial);
  firstNameToMiddleInitial.style.stroke = 'dodgerBlue';
  lastNameToMiddleInitial.style.stroke = 'dodgerBlue';
  middleInitialToMiddleInitial.style.stroke = 'dodgerBlue';
});
map4TargetInput.addEventListener('mouseout', function() {
  middleInitialTarget.classList.remove('dependency');
  firstNameToMiddleInitial.style.stroke = 'silver';
  lastNameToMiddleInitial.style.stroke = 'silver';
  middleInitialToMiddleInitial.style.stroke = 'silver';
});
map4TargetInput.addEventListener('focus', function() {
  map4TargetInput.value = "Books:bookstore/book[*]/middleInitial";
  middleInitialTarget.classList.add('dependency');
  svg.appendChild(firstNameToMiddleInitial);
  svg.appendChild(lastNameToMiddleInitial);
  svg.appendChild(middleInitialToMiddleInitial);
  firstNameToMiddleInitial.style.stroke = 'dodgerBlue';
  lastNameToMiddleInitial.style.stroke = 'dodgerBlue';
  middleInitialToMiddleInitial.style.stroke = 'dodgerBlue';
});
map4TargetInput.addEventListener('blur', function() {
  map4TargetInput.value = "middleInitial";
  middleInitialTarget.classList.remove('dependency');
  firstNameToMiddleInitial.style.stroke = 'silver';
  lastNameToMiddleInitial.style.stroke = 'silver';
  middleInitialToMiddleInitial.style.stroke = 'silver';
});

tippy('#map4SourceInput', {
  content: splitExpandedInput + '[3]',
});
tippy('#map4TargetInput', {
  content: middleInitialPath,
});

function setMapIfControlVisibility(visibility) {
  mapIfEditIcon.style.visibility = visibility;
  mapIfRemoveIcon.style.visibility = visibility;
  mapIfConditionAddFuncIcon.style.visibility = visibility;
  mapIfThenAddFuncIcon.style.visibility = visibility;
  mapIfAddThenLink.style.visibility = visibility;
}

setMapIfControlVisibility("hidden");
mapIf.addEventListener('mouseover', function() {
  setMapIfControlVisibility("visible");
});
mapIf.addEventListener('mouseout', function() {
  setMapIfControlVisibility("hidden");
});
mapIf.addEventListener('focus', function() {
  setMapIfControlVisibility("visible");
}, true);
mapIf.addEventListener('blur', function() {
  setMapIfControlVisibility("hidden");
}, true);
mapIfThenInput.addEventListener('mouseover', function() {
  map1.classList.add('dependency');
});
mapIfThenInput.addEventListener('mouseout', function() {
  map1.classList.remove('dependency');
});
mapIfThenInput.addEventListener('focus', function() {
  map1.classList.add('dependency');
});
mapIfThenInput.addEventListener('blur', function() {
  map1.classList.remove('dependency');
});

tippy('#mapIfConditionInput', {
  content: 'someBooleanProp = true',
});
tippy('#mapIfThenInput', {
  content: map1ExpandedInput,
});

authorSource.addEventListener('click', function() {
  authorSource.classList.add('fieldSelected');
  mapping.style.visibility = 'visible';
  map1SourceDropdown.value = '/';
  map1SourceInput.value = 'author';
  map1SourceInput._tippy.setContent(authorPath);
  map1TargetLabel.style.color = 'red';
  map1TargetInput.value = '';
  map1TargetInput._tippy.disable();
  map1TargetInput.focus();
  map1Preview.value = "'John H. Doe'";
});

function addLowercase() {
  lowercase.style.display = 'block';
  lowercaseFuncDropdown.value = '';
  lowercaseFuncDropdown.focus();
  map1SourceDropdown.value = 'f';
  map1SourceInput.value = '';
  map1SourceInput._tippy.disable();
}

authorTarget.addEventListener('click', function() {
  authorTarget.classList.add('fieldSelected');
  bookstoreSourceMapIcon.style.visibility = "visible";
  bookSourceMapIcon.style.visibility = "visible";
  authorSourceMapIcon.style.visibility = "visible";
  bookstoreTargetMapIcon.style.visibility = "visible";
  bookTargetMapIcon.style.visibility = "visible";
  authorSourceMapIcon.style.visibility = 'visible';
  authorTargetMapIcon.style.visibility = "visible";
  svg.style.width = connectors.clientWidth;
  svg.style.height = connectors.clientHeight;
  authorToAuthor.setAttribute('x2', connectors.clientWidth);
  authorToAuthor.style.visibility = 'visible';
  map1TargetLabel.style.color = 'black';
  map1TargetInput.value = 'author';
  map1TargetInput._tippy.enable();
  map1SourceAddFuncIcon.addEventListener('click', addLowercase);
});

const walkthroughLowercaseExpandedInput = "Lowercase( " +
  "Source: " + authorPath +  " " +
")";

lowercaseFuncDropdown.addEventListener('change', function() {
  lowercaseTable.style.display = 'block';
  lowercaseSourceDropdown.value = '/';
  lowercaseSourceInput.value = 'author';
  lowercaseSourceInput._tippy.setContent(authorPath);
  lowercaseSourceDropdown.focus();
  lowercasePreview.value = "'john h. doe'";
  map1SourceInput.value = 'Lowercase(...)';
  map1SourceInput._tippy.setContent(walkthroughLowercaseExpandedInput);
  map1SourceInput._tippy.enable();
  map1Preview.value = "'john h. doe'";
  map1SourceAddFuncIcon.removeEventListener('click', addLowercase);
  map1SourceAddFuncIcon.addEventListener('click', function() {
    mapIf.style.display = 'block';
    mapIfFuncDropdown.value = '';
    mapIfFuncDropdown.focus();
    map1.style.order = 10;
    map1SourceDropdown.value = 'f';
    map1SourceInput.value = '';
    map1SourceInput._tippy.disable();
  });
});

mapIfFuncDropdown.addEventListener('change', function() {
  map1.style.order = null;
  map1SourceInput.value = 'Lowercase(...)';
  map1SourceInput._tippy.enable();
  mapIfTable.style.display = 'block';
  mapIfConditionDropdown.value = '/';
  mapIfConditionInput.value = '';
  mapIfConditionInput._tippy.disable();
  mapIfConditionDropdown.focus();
  mapIfThenInput._tippy.setContent("Map( " +
    "Source: " + walkthroughLowercaseExpandedInput + ", " +
    "Target: " + authorPath + " " +
  ")");
});

function handleTooltip() {
  mapIfConditionInput.value = 'someBooleanProp';
  const tooltip = mapIfConditionInput._tippy;
  // tooltip.hide();
  tooltip.setContent('someBooleanProp = true');
  tooltip.setProps({
    arrow: true,
    distance: 10,
    interactive: false,
    interactiveBorder: 2,
    // trigger: 'focus',
    placement: 'top',
    theme: '',
  });
  mapIfConditionInput._tippy.enable();
  mapIfConditionInput.addEventListener('mouseover', function() {
    someBooleanProp.classList.add('dependency');
  });
  mapIfConditionInput.addEventListener('mouseout', function() {
    someBooleanProp.classList.remove('dependency');
  });
  mapIfConditionInput.addEventListener('focus', function() {
    someBooleanProp.classList.add('dependency');
  });
  mapIfConditionInput.addEventListener('blur', function() {
    someBooleanProp.classList.remove('dependency');
  });
}

mapIfConditionDropdown.addEventListener('change', function() {
  const tooltip = mapIfConditionInput._tippy;
  tooltip.setContent('<span onclick="handleTooltip()">someBooleanProp</span>');
  tooltip.setProps({
    arrow: false,
    distance: 0,
    interactive: true,
    interactiveBorder: 5,
    // trigger: 'focus',
    placement: 'bottom',
    theme: 'contentAssist',
  });
  tooltip.enable();
  mapIfConditionInput.focus();
});

const mappingsHeader = document.getElementById('mappingsHeader');
mappingsHeader.addEventListener('click', function() {
  bookstoreSourceMapIcon.style.visibility = 'visible';
  bookSourceMapIcon.style.visibility = 'visible';
  authorSource.classList.remove('fieldSelected');
  authorSourceMapIcon.style.visibility = 'hidden';
  firstNameSourceMapIcon.style.visibility = 'visible';
  lastNameSourceMapIcon.style.visibility = 'visible';
  middleInitialSourceMapIcon.style.visibility = 'visible';
  bookstoreTargetMapIcon.style.visibility = 'visible';
  bookTargetMapIcon.style.visibility = 'visible';
  authorTarget.classList.add('fieldSelected');
  authorTargetMapIcon.style.visibility = 'visible';
  firstNameTargetMapIcon.style.visibility = 'visible';
  lastNameTargetMapIcon.style.visibility = 'visible';
  middleInitialTargetMapIcon.style.visibility = 'visible';
  mapping.style.visibility = 'visible';
  combine.style.display = 'block';
  lowercase.style.display = 'block';
  lowercaseFuncDropdown.value = 'Lowercase';
  lowercaseTable.style.display = 'block';
  lowercaseSourceDropdown.value = 'f';
  lowercaseSourceInput.value = 'Combine(...)';
  lowercaseSourceInput._tippy.setContent(combineExpandedInput);
  lowercasePreview.value = "'doe, john h.'";
  map1.style.order = null;
  map1SourceDropdown.value = 'f';
  map1SourceInput.value = 'Lowercase(...)';
  map1SourceInput._tippy.setContent(lowercaseExpandedInput);
  map1SourceInput._tippy.enable();
  map1TargetLabel.style.color = 'black';
  map1TargetInput.value = 'author';
  map1TargetInput._tippy.setContent(authorPath);
  map1TargetInput._tippy.enable();
  map1Preview.value = "'doe, john h.'";
  split.style.display = "block";
  crop.style.display = "block";
  map2.style.display = "block";
  map3.style.display = "block";
  map4.style.display = "block";
  mapIf.style.display = "block";
  mapIfFuncDropdown.value = 'MapIf';
  mapIfTable.style.display = 'block';
  mapIfConditionDropdown.value = '$';
  mapIfConditionInput.value = 'someBooleanProp';
  const tooltip = mapIfConditionInput._tippy;
  tooltip.setContent('someBooleanProp = true');
  tooltip.setProps({
    arrow: true,
    distance: 10,
    interactive: false,
    interactiveBorder: 2,
    // trigger: 'focus',
    placement: 'top',
    theme: '',
  });
  tooltip.enable();
  mapIfConditionInput.addEventListener('mouseover', function() {
    someBooleanProp.classList.add('dependency');
  });
  mapIfConditionInput.addEventListener('mouseout', function() {
    someBooleanProp.classList.remove('dependency');
  });
  mapIfConditionInput.addEventListener('focus', function() {
    someBooleanProp.classList.add('dependency');
  });
  mapIfConditionInput.addEventListener('blur', function() {
    someBooleanProp.classList.remove('dependency');
  });
  mapIfThenInput._tippy.setContent(map1ExpandedInput);

  svg.style.width = connectors.clientWidth;
  svg.style.height = connectors.clientHeight;
  firstNameToAuthor.setAttribute('x2', connectors.clientWidth);
  lastNameToAuthor.setAttribute('x2', connectors.clientWidth);
  middleInitialToAuthor.setAttribute('x2', connectors.clientWidth);
  firstNameToFirstName.setAttribute('x2', connectors.clientWidth);
  lastNameToFirstName.setAttribute('x2', connectors.clientWidth);
  middleInitialToFirstName.setAttribute('x2', connectors.clientWidth);
  firstNameToLastName.setAttribute('x2', connectors.clientWidth);
  lastNameToLastName.setAttribute('x2', connectors.clientWidth);
  middleInitialToLastName.setAttribute('x2', connectors.clientWidth);
  firstNameToMiddleInitial.setAttribute('x2', connectors.clientWidth);
  lastNameToMiddleInitial.setAttribute('x2', connectors.clientWidth);
  middleInitialToMiddleInitial.setAttribute('x2', connectors.clientWidth);
  authorToAuthor.style.visibility = 'hidden';
  firstNameToAuthor.style.visibility = 'visible';
  lastNameToAuthor.style.visibility = 'visible';
  middleInitialToAuthor.style.visibility = 'visible';
  firstNameToFirstName.style.visibility = 'visible';
  lastNameToFirstName.style.visibility = 'visible';
  middleInitialToFirstName.style.visibility = 'visible';
  firstNameToLastName.style.visibility = 'visible';
  lastNameToLastName.style.visibility = 'visible';
  middleInitialToLastName.style.visibility = 'visible';
  firstNameToMiddleInitial.style.visibility = 'visible';
  lastNameToMiddleInitial.style.visibility = 'visible';
  middleInitialToMiddleInitial.style.visibility = 'visible';
});
