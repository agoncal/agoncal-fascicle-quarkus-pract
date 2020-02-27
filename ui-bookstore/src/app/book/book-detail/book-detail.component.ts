import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {Book} from "../../shared/model/book";

@Component({
  templateUrl: './book-detail.component.html',
  styles: []
})
export class BookDetailComponent implements OnInit {

  book: Book;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.load(params['id']);
    });
  }

  load(id: number) {
    this.bookEndpointService.getBook(id).subscribe((book: Book) => {
      this.book = book;
    });
  }
}
