/*
Copyright (C) 2019 Red Hat, Inc.
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

import { Component, ElementRef, OnInit } from '@angular/core';

import { ModalWindowComponent, ModalWindowValidator } from './modal-window.component';
import { ExpressionModel } from '../models/expression.model';
import { Field } from '../models/field.model';
import { Function } from '../models/function.model';

@Component({
  selector: 'mapping',
  templateUrl: './mapping.component.html',
})

export class MappingComponent implements ModalWindowValidator, OnInit {

  name = 'Mapping';
  modalWindow: ModalWindowComponent;
  selectedField: Field = null;
  expressionModel: ExpressionModel = null;
  functions: Function[];

  onAdd() {
    this.functions.unshift(null);
  }

  getInitialFocusElement(): ElementRef {
    return undefined;
  }

  isDataValid(): boolean {
    return true;
  }

  ngOnInit() {
    this.functions = [ Function.AVAILABLE_FUNCTIONS[0] ];
  }
}
