import {Component, OnInit} from '@angular/core';
import {IBook, Book} from "../../shared/model/book";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: './book-form.component.html',
  styles: []
})
export class BookFormComponent implements OnInit {

  book?: IBook;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.book = new Book();
  }

  save() {
    this.bookEndpointService.createBook(this.book).subscribe((response: string) => {
      this.router.navigate(['/book-list']);
    });
  }

}
