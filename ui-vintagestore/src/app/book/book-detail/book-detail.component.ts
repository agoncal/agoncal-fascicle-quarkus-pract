import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {IBook} from "../../shared/model/book";

@Component({
  templateUrl: './book-detail.component.html',
  styles: []
})
export class BookDetailComponent implements OnInit {

  book?: IBook;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.load(params['id']);
    });
  }

  load(id: number) {
    this.bookEndpointService.getBook(id).subscribe((book: IBook) => {
      this.book = book;
    });
  }
}
