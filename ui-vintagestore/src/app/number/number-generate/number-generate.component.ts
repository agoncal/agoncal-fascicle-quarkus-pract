import { Component, OnInit } from '@angular/core';
import {NumberEndpointService} from "../../shared/api/numberEndpoint.service";
import {BookNumbers} from "../../shared/model/bookNumbers";

// tag::adocSnippet[]
@Component({
  templateUrl: 'number-generate.component.html'
})
export class NumberGenerateComponent implements OnInit {

  bookNumbers?: BookNumbers;

  constructor(private numberEndpointService: NumberEndpointService) { }

  ngOnInit(): void {
    this.generateBookNumber();
  }

  generateBookNumber() {
    this.numberEndpointService.generatesBookNumbers().subscribe(bookNumbers => this.bookNumbers = bookNumbers);
  }
}
// end::adocSnippet[]
