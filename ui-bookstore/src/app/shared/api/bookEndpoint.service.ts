/**
 * Book API
 * This API allows CRUD operations on books
 *//* tslint:disable:no-unused-variable member-ordering */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../model/book';


@Injectable()
export class BookEndpointService {

  protected basePath = 'http://localhost:8082';
  public defaultHeaders = new HttpHeaders();

  constructor(protected httpClient: HttpClient) {
  }

  /**
   * Returns all the books from the database
   *
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public apiBooksGet(observe?: 'body', reportProgress?: boolean): Observable<Array<Book>>;
  public apiBooksGet(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<Book>>>;
  public apiBooksGet(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<Book>>>;
  public apiBooksGet(observe: any = 'body', reportProgress: boolean = false): Observable<any> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [];

    return this.httpClient.request<Array<Book>>('get', `${this.basePath}/api/books`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Deletes an exiting book
   *
   * @param id Book identifier
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public apiBooksIdDelete(id: number, observe?: 'body', reportProgress?: boolean): Observable<any>;
  public apiBooksIdDelete(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
  public apiBooksIdDelete(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
  public apiBooksIdDelete(id: number, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

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
  public apiBooksIdGet(id: number, observe?: 'body', reportProgress?: boolean): Observable<Book>;
  public apiBooksIdGet(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Book>>;
  public apiBooksIdGet(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Book>>;
  public apiBooksIdGet(id: number, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

    if (id === null || id === undefined) {
      throw new Error('Required parameter id was null or undefined when calling apiBooksIdGet.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<Book>('get', `${this.basePath}/api/books/${encodeURIComponent(String(id))}`,
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
  public apiBooksPost(body: Book, observe?: 'body', reportProgress?: boolean): Observable<string>;
  public apiBooksPost(body: Book, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
  public apiBooksPost(body: Book, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
  public apiBooksPost(body: Book, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

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
   * Updates an exiting  book
   *
   * @param body
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public apiBooksPut(body: Book, observe?: 'body', reportProgress?: boolean): Observable<Book>;
  public apiBooksPut(body: Book, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Book>>;
  public apiBooksPut(body: Book, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Book>>;
  public apiBooksPut(body: Book, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

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

    return this.httpClient.request<Book>('put', `${this.basePath}/api/books`,
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
  public apiBooksRandomGet(observe?: 'body', reportProgress?: boolean): Observable<Book>;
  public apiBooksRandomGet(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Book>>;
  public apiBooksRandomGet(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Book>>;
  public apiBooksRandomGet(observe: any = 'body', reportProgress: boolean = false): Observable<any> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<Book>('get', `${this.basePath}/api/books/random`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }
}
