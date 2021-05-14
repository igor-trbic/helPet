import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Business } from './models/business.model';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {
  private apiUrl = '/helpet/business';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  get(): Observable<Business[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Business[]) => console.log(`req w/ id=${req}`))
    );
  }
}
