import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NumberRoutingModule } from './number-routing.module';
import { NumberGenerateComponent } from './number-generate/number-generate.component';


@NgModule({
  declarations: [NumberGenerateComponent],
  imports: [
    CommonModule,
    NumberRoutingModule
  ]
})
export class NumberModule { }
