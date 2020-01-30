import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookRandomComponent } from "./book-random/book-random.component";
import { BookListComponent } from "./book-list/book-list.component";
import {BookDeleteComponent} from "./book-delete/book-delete.component";
import {BookDetailComponent} from "./book-detail/book-detail.component";
import {BookFormComponent} from "./book-form/book-form.component";


const routes: Routes = [
  { path: '', component:BookRandomComponent},
  { path: 'book-delete/:id', component:BookDeleteComponent},
  { path: 'book-detail/:id', component:BookDetailComponent},
  { path: 'book-form', component:BookFormComponent},
  { path: 'book-list', component:BookListComponent},
  { path: 'book-random', component:BookRandomComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
