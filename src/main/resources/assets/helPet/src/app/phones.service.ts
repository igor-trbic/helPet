import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Phone } from './models/phone.model';

@Injectable({
  providedIn: 'root'
})
export class PhonesService {

  private apiUrl = '/helpet/phones';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getPhones(): Observable<Phone[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Phone[]) => console.log(`req w/ id=${req}`))
    );
  }

  createPhone(phone: Phone): Observable<Phone> {
    return this.http.post(this.apiUrl, phone, this.httpOptions)
    .pipe(
      tap((req: Phone) => console.log(`req w/ id=${req}`))
    );
  }

  updatePhone(phone: Phone): Observable<Phone> {
    return this.http.put(this.apiUrl + '/' + phone.id, Phone, this.httpOptions)
    .pipe(
      tap((req: Phone) => console.log(`req w/ id=${req}`))
    );
  }

  removePhone(phoneId: number): Observable<Phone> {
    return this.http.delete(this.apiUrl + '/' + phoneId, this.httpOptions)
    .pipe(
      tap((req: Phone) => console.log(`req w/ id=${req}`))
    );
  }
}
