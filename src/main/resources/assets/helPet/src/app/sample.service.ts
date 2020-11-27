import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sample } from './models/user.model';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class SampleService {

  private registerUrl = '/helpet/sample';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  register(user: Sample): Observable<Sample> {
    return this.http.post<Sample>(this.registerUrl, user, this.httpOptions).pipe(
      tap((newUser: Sample) => console.log(`Returned id=${newUser.returnMe}`))
    );
  }
}
