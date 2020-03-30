import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NumberRoutingModule } from './number-routing.module';
import { NumberGenerateComponent } from './number-generate/number-generate.component';
import { NumberEndpointService } from "../shared/api/numberEndpoint.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [NumberGenerateComponent],
  imports: [
    CommonModule,
    NumberRoutingModule,
    HttpClientModule
  ],
  providers:[NumberEndpointService]
})
export class NumberModule { }
