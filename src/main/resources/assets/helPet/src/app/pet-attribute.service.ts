import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { PetAttribute } from './models/petAttribute.model';

@Injectable({
  providedIn: 'root'
})
export class PetAttributeService {

  private apiUrl = '/helpet/pet-attributes';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getPetAttributes(petId): Observable<PetAttribute[]> {
    return this.http.get(this.apiUrl + '/' + petId, this.httpOptions)
    .pipe(
      tap((req: PetAttribute[]) => console.log(`req w/ id=${req}`))
    );
  }

  createPetAttribute(petId, petAttribute: PetAttribute): Observable<PetAttribute> {
    return this.http.post(this.apiUrl + "/" + petId, petAttribute, this.httpOptions)
    .pipe(
      tap((req: PetAttribute) => console.log(`req w/ id=${req}`))
    );
  }

  updatePetAttribute(petId, petAttribute: PetAttribute): Observable<PetAttribute> {
    return this.http.put(this.apiUrl + "/" + petId + '/attribute/' + petAttribute.id, PetAttribute, this.httpOptions)
    .pipe(
      tap((req: PetAttribute) => console.log(`req w/ id=${req}`))
    );
  }

  removePetAttribute(petId, petAttributeId: number): Observable<PetAttribute> {
    return this.http.delete(this.apiUrl + "/" + petId + '/attribute/' + petAttributeId, this.httpOptions)
    .pipe(
      tap((req: PetAttribute) => console.log(`req w/ id=${req}`))
    );
  }
}
