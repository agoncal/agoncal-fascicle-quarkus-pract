import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatDialog } from "@angular/material/dialog";

import { BookDetailComponent } from "../book-detail/book-detail.component";
import { BookFormComponent } from "../book-form/book-form.component";
import { BookDeleteComponent } from "../book-delete/book-delete.component";
import { Book } from "../../shared/model/book";
import { BookEndpointService } from "../../shared/api/bookEndpoint.service";

@Component({
  templateUrl: './book-list.component.html',
  styles: []
})
export class BookListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'isbn13', 'title', 'author', 'actions'];
  dataSource = new MatTableDataSource<Book>();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
              private numberEndpointService: BookEndpointService) {
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.listBooks();
  }

  listBooks(): void{
    this.numberEndpointService.apiBooksGet().subscribe(books => this.dataSource.data = books);
  }

  openDetail(): void {
    const dialogRef = this.dialog.open(BookDetailComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openForm(): void {
    const dialogRef = this.dialog.open(BookFormComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDelete(): void {
    const dialogRef = this.dialog.open(BookDeleteComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
