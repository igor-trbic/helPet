import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthReq } from './models/authReq.model';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private registerUrl = '/helpet/auth';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  login(authReq: AuthReq): Observable<AuthReq> {
    return this.http.post<AuthReq>(this.registerUrl, authReq, this.httpOptions).pipe(
      tap((req: AuthReq) => console.log(`req w/ id=${req}`))
    );
  }
}
