/*
    Copyright (C) 2017 Red Hat, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Function } from '../models/function.model';

@Component({
  selector: 'function',
  templateUrl: './function.component.html',
})

export class FunctionComponent {

  @Input() functions;
  @Input() selectedFunction;
  @Output() add = new EventEmitter<string>();

  addFunction() {
    this.selectedFunction = null;
    this.add.emit(null);
  }

  availableFunctions(): Function[] {
    const functions = Object.keys(Function).filter(value => isNaN(Number(value)) === false).map(key => Function[key]);
    functions.unshift(null);
    return functions;
  }

  handleMouseClick(event: MouseEvent) {
    // this.selectedCallback(this);
  }
}
