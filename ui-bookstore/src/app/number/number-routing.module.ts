import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BookRandomComponent} from "../book/book-random/book-random.component";
import {BookDeleteComponent} from "../book/book-delete/book-delete.component";
import {NumberGenerateComponent} from "./number-generate/number-generate.component";


const routes: Routes = [
  { path: '', component:NumberGenerateComponent},
  { path: 'number-generate', component:NumberGenerateComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NumberRoutingModule { }
