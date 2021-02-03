import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Address } from './models/address.model';

@Injectable({
  providedIn: 'root'
})
export class AddressesService {

  private apiUrl = '/helpet/addresses';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getAddresses(): Observable<Address[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Address[]) => console.log(`req w/ id=${req}`))
    );
  }

  createAddress(address: Address): Observable<Address> {
    return this.http.post(this.apiUrl, address, this.httpOptions)
    .pipe(
      tap((req: Address) => console.log(`req w/ id=${req}`))
    );
  }

  updateAddress(address: Address): Observable<Address> {
    return this.http.put(this.apiUrl + '/' + address.id, address, this.httpOptions)
    .pipe(
      tap((req: Address) => console.log(`req w/ id=${req}`))
    );
  }

  removeAddress(addressId: number): Observable<Address> {
    return this.http.delete(this.apiUrl + '/' + addressId, this.httpOptions)
    .pipe(
      tap((req: Address) => console.log(`req w/ id=${req}`))
    );
  }
}
