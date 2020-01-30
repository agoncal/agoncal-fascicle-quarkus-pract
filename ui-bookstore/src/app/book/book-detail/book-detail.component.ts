import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {ActivatedRoute} from "@angular/router";
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
    this.bookEndpointService.apiBooksIdGet(id).subscribe((book) => {
      this.book = book;
    });
  }
}
