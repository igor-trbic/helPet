import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { BusinessRoleType } from './models/businessRoleType.model';

@Injectable({
  providedIn: 'root'
})
export class BusinessRoleTypeService {

  private apiUrl = '/helpet/business-roles';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getBusinessRoles(id): Observable<BusinessRoleType[]> {
    return this.http.get(this.apiUrl + '/' + id, this.httpOptions)
    .pipe(
      tap((req: BusinessRoleType[]) => console.log(`req w/ id=${req}`))
    );
  }

  get(id): Observable<BusinessRoleType> {
    return this.http.get(this.apiUrl + '/' + id, this.httpOptions)
    .pipe(
      tap((req: BusinessRoleType) => console.log(`req w/ id=${req}`))
    );
  }

  create(id, businessRole: BusinessRoleType): Observable<BusinessRoleType> {
    return this.http.post(this.apiUrl + '/' + id, businessRole, this.httpOptions)
    .pipe(
      tap((req: BusinessRoleType) => console.log(`req w/ id=${req}`))
    );
  }

  update(id, businessRole: BusinessRoleType): Observable<BusinessRoleType> {
    return this.http.put(this.apiUrl + '/' + id + '/type/' + businessRole.id, businessRole, this.httpOptions)
    .pipe(
      tap((req: BusinessRoleType) => console.log(`req w/ id=${req}`))
    );
  }

  remove(id, businessRoleId: number): Observable<BusinessRoleType> {
    return this.http.delete(this.apiUrl + '/' + id + '/type/' + businessRoleId, this.httpOptions)
    .pipe(
      tap((req: BusinessRoleType) => console.log(`req w/ id=${req}`))
    );
  }
}
