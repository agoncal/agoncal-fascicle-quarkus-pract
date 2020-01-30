import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  templateUrl: './book-delete.component.html',
  styles: []
})
export class BookDeleteComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<BookDeleteComponent>) {
  }

  ngOnInit(): void {
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
