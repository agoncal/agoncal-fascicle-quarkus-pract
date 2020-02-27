import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {Book} from "../../shared/model/book";

@Component({
  templateUrl: './book-delete.component.html',
  styles: []
})
export class BookDeleteComponent implements OnInit {

  book: Book;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.load(params['id']);
    });
  }

  load(id: number) {
    this.bookEndpointService.getBook(id).subscribe((book) => {
      this.book = book;
    });
  }

  delete(id: number) {
    this.bookEndpointService.deleteBook(id).subscribe((response) => {
      this.router.navigate(['/book-list']);
    });
  }
}
