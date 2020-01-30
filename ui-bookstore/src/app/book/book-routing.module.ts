import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BookRandomComponent} from "./book-random/book-random.component";
import {BookDeleteComponent} from "./book-delete/book-delete.component";
import {BookFormComponent} from "./book-form/book-form.component";
import {BookListComponent} from "./book-list/book-list.component";


const routes: Routes = [
  { path: '', component:BookRandomComponent},
  { path: 'book-delete', component:BookDeleteComponent},
  { path: 'book-form', component:BookFormComponent},
  { path: 'book-list', component:BookListComponent},
  { path: 'book-random', component:BookRandomComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
