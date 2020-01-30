import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookRandomComponent } from "./book-random/book-random.component";
import { BookListComponent } from "./book-list/book-list.component";


const routes: Routes = [
  { path: '', component:BookRandomComponent},
  { path: 'book-list', component:BookListComponent},
  { path: 'book-random', component:BookRandomComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
