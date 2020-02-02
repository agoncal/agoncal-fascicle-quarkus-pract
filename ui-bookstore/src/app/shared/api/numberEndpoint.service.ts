/**
 * Number API
 * This API allows to generate all sorts of numbers
 *//* tslint:disable:no-unused-variable member-ordering */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BookNumbers} from '../model/bookNumbers';

// tag::adocSnippet[]
@Injectable()
export class NumberEndpointService {

  protected basePath = 'http://localhost:8081';

  constructor(protected httpClient: HttpClient) {
  }

  // tag::adocSkip[]

  public defaultHeaders = new HttpHeaders();

  /**
   * Generates book numbers
   * These book numbers have several formats: ISBN, ASIN and EAN
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  // end::adocSkip[]
  public generatesBookNumbers(observe: any = 'body', reportProgress: boolean = false): Observable<any> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<BookNumbers>('get', `${this.basePath}/api/numbers/book`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }
}
// end::adocSnippet[]
