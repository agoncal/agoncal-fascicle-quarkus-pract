import {Component, OnInit} from '@angular/core';

import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {Book} from "../../shared/model/book";

@Component({
  templateUrl: './book-list.component.html',
  styles: []
})
export class BookListComponent implements OnInit {

  books?: Book[];

  constructor(private numberEndpointService: BookEndpointService) {
  }

  ngOnInit(): void {
    this.listBooks();
  }

  listBooks(): void {
    this.numberEndpointService.getAllBooks().subscribe(books => this.books = books);
  }
}
