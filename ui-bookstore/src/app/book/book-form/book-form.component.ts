import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/model/book";

@Component({
  templateUrl: './book-form.component.html',
  styles: []
})
export class BookFormComponent implements OnInit {

  book?:Book;

  constructor() {
  }

  ngOnInit(): void {
  }
}
