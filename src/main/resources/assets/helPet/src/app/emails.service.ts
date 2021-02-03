import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Email } from './models/email.model';

@Injectable({
  providedIn: 'root'
})
export class EmailsService {

  private apiUrl = '/helpet/emails';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getEmails(): Observable<Email[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Email[]) => console.log(`req w/ id=${req}`))
    );
  }

  createEmail(email: Email): Observable<Email> {
    return this.http.post(this.apiUrl, email, this.httpOptions)
    .pipe(
      tap((req: Email) => console.log(`req w/ id=${req}`))
    );
  }

  updateEmail(email: Email): Observable<Email> {
    return this.http.put(this.apiUrl + '/' + email.id, email, this.httpOptions)
    .pipe(
      tap((req: Email) => console.log(`req w/ id=${req}`))
    );
  }

  removeEmail(emailId: number): Observable<Email> {
    return this.http.delete(this.apiUrl + '/' + emailId, this.httpOptions)
    .pipe(
      tap((req: Email) => console.log(`req w/ id=${req}`))
    );
  }
}
