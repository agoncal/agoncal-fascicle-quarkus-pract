import { Component, OnInit } from '@angular/core';
import {NumberEndpointService} from "../../shared/api/numberEndpoint.service";
import {BookNumbers} from "../../shared/model/bookNumbers";

@Component({
  templateUrl: 'number-generate.component.html',
  styles: []
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
