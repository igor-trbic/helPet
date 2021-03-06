import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Pet } from './models/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetsService {

  private apiUrl = '/helpet/pets';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getPets(): Observable<Pet[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Pet[]) => console.log(`req w/ id=${req}`))
    );
  }

  getPet(id): Observable<Pet> {
    return this.http.get(this.apiUrl + '/' + id, this.httpOptions)
    .pipe(
      tap((req: Pet) => console.log(`req w/ id=${req}`))
    );
  }

  createPet(pet: Pet): Observable<Pet> {
    return this.http.post(this.apiUrl, pet, this.httpOptions)
    .pipe(
      tap((req: Pet) => console.log(`req w/ id=${req}`))
    );
  }

  updatePet(pet: Pet): Observable<Pet> {
    return this.http.put(this.apiUrl + '/' + pet.id, pet, this.httpOptions)
    .pipe(
      tap((req: Pet) => console.log(`req w/ id=${req}`))
    );
  }

  removePet(petId: number): Observable<Pet> {
    return this.http.delete(this.apiUrl + '/' + petId, this.httpOptions)
    .pipe(
      tap((req: Pet) => console.log(`req w/ id=${req}`))
    );
  }
}
