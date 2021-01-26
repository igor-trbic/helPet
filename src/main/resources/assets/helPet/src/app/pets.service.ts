import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Pet } from './models/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetsService {

  private registerUrl = '/helpet/pets';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getPets(): Observable<Pet[]> {
    return this.http.get(this.registerUrl, this.httpOptions)
    .pipe(
      tap((req: Pet[]) => console.log(`req w/ id=${req}`))
    );
  }
}
