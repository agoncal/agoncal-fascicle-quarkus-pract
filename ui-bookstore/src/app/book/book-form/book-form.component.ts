import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/model/book";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: './book-form.component.html',
  styles: []
})
export class BookFormComponent implements OnInit {

  book:Book;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.bookEndpointService.createBook(this.book).subscribe((response: string) => {
      this.router.navigate(['/book-list']);
    });
  }

}
