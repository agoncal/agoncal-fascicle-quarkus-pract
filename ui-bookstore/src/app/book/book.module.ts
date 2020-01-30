import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { BookListComponent } from './book-list/book-list.component';
import { BookFormComponent } from './book-form/book-form.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { BookDeleteComponent } from './book-delete/book-delete.component';
import { BookRandomComponent } from './book-random/book-random.component';
import {MaterialModule} from "../shared/material.module";


@NgModule({
  declarations: [BookListComponent, BookFormComponent, BookDetailComponent, BookDeleteComponent, BookRandomComponent],
  imports: [
    CommonModule,
    MaterialModule,
    BookRoutingModule
  ]
})
export class BookModule { }
