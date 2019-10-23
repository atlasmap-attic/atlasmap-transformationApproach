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

export class Function {

  static AVAILABLE_FUNCTIONS: Function[] = [{
    name: 'Map',
    description: 'Maps the source argument to a target model field.',
    parameters: ['Source', 'Target']
  }, {
    name: 'Concatenate',
    description: 'Concatenates all string arguments into a single string using the supplied delimiter, or a space if none is supplied.',
    parameters: ['String 1', 'String 2', 'Delimiter']
  }, {
    name: 'If',
    description: 'Tests a condition and evaluates the \'then\' expression if true or the \'else\' expression if false.',
    parameters: ['Condition', 'Then', 'Else']
  }, {
    name: 'IsEmpty',
    description: 'Returns true if the argument is null, empty text, or an empty collection, false otherwise..',
    parameters: ['Argument']
  }, {
    name: 'IsLessThan',
    description: 'Returns true if argument 1 is less than argument 2, false otherwise.',
    parameters: ['Argument 1', 'Argument 2']
  }, {
    name: 'Lowercase',
    description: 'Converts the string argument to lowercase.',
    parameters: ['String']
  }, {
    name: 'Split',
    description: 'Splits the string argument into a list of strings using the supplied delimiter, or a space if none is supplied.',
    parameters: ['String', 'Delimiter']
  }];

  name: string;
  description: string;
  parameters: string[];
}
