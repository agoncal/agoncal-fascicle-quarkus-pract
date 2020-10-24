/**
 * Book API
 * This API allows CRUD operations on books
 *//* tslint:disable:no-unused-variable member-ordering */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IBook} from '../model/book';


@Injectable()
export class BookEndpointService {

  protected basePath = 'http://localhost:8702';
  public defaultHeaders = new HttpHeaders();

  constructor(protected httpClient: HttpClient) {
  }

  /**
   * Returns all the books from the database
   *
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getAllBooks(observe?: 'body', reportProgress?: boolean): Observable<Array<IBook>> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [];

    return this.httpClient.request<Array<IBook>>('get', `${this.basePath}/api/books`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Deletes an existing book
   *
   * @param id Book identifier
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public deleteBook(id: number, observe?: 'body', reportProgress?: boolean): Observable<any> {

    if (id === null || id === undefined) {
      throw new Error('Required parameter id was null or undefined when calling apiBooksIdDelete.');
    }

    let headers = this.defaultHeaders;

    return this.httpClient.request<any>('delete', `${this.basePath}/api/books/${encodeURIComponent(String(id))}`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Returns a book for a given identifier
   *
   * @param id Book identifier
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getBook(id: number, observe?: 'body', reportProgress?: boolean): Observable<IBook> {

    if (id === null || id === undefined) {
      throw new Error('Required parameter id was null or undefined when calling apiBooksIdGet.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<IBook>('get', `${this.basePath}/api/books/${encodeURIComponent(String(id))}`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Creates a valid book
   *
   * @param body
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public createBook(body: IBook | undefined, observe?: 'body', reportProgress?: boolean): Observable<string> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling apiBooksPost.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    headers = headers.set('Content-Type', consumes);

    return this.httpClient.request<string>('post', `${this.basePath}/api/books`,
      {
        body: body,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Updates an existing  book
   *
   * @param body
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public updateBook(body: IBook, observe?: 'body', reportProgress?: boolean): Observable<IBook> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling apiBooksPut.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    headers = headers.set('Content-Type', consumes);

    return this.httpClient.request<IBook>('put', `${this.basePath}/api/books`,
      {
        body: body,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Returns a random book
   *
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getRandomBook(observe?: 'body', reportProgress?: boolean): Observable<IBook> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<IBook>('get', `${this.basePath}/api/books/random`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }
}
