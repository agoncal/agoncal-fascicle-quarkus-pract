import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/model/book";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";

@Component({
  templateUrl: './book-random.component.html',
  styles: []
})
export class BookRandomComponent implements OnInit {

  book: Book;

  constructor(private bookEndpointService: BookEndpointService) { }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.bookEndpointService.getRandomBook().subscribe((book) => {
      this.book = book;
    });
  }
}
