import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { BusinessRoleType } from './models/businessRoleType.model';
import { BusinessStaffDTO } from './models/businessStaff.dto.model';

@Injectable({
  providedIn: 'root'
})
export class BusinessStaffService {

  private apiUrl = '/helpet/staff';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  getAll(id): Observable<BusinessStaffDTO[]> {
    return this.http.get(this.apiUrl + '/' + id, this.httpOptions)
    .pipe(
      tap((req: BusinessStaffDTO[]) => console.log(`req w/ id=${req}`))
    );
  }

  update(businessStaff: BusinessStaffDTO): Observable<BusinessStaffDTO> {
    return this.http.put(this.apiUrl + '/' + businessStaff.id, businessStaff, this.httpOptions)
    .pipe(
      tap((req: BusinessStaffDTO) => console.log(`req w/ id=${req}`))
    );
  }

  // remove(businessRoleId: number): Observable<BusinessRoleType> {
  //   return this.http.delete(this.apiUrl + '/' + businessRoleId, this.httpOptions)
  //   .pipe(
  //     tap((req: BusinessRoleType) => console.log(`req w/ id=${req}`))
  //   );
  // }

  invite(id, businessRole: BusinessStaffDTO): Observable<BusinessStaffDTO> {
    return this.http.post(this.apiUrl + '/' + id + '/invite', businessRole, this.httpOptions)
    .pipe(
      tap((req: BusinessStaffDTO) => console.log(`req w/ id=${req}`))
    );
  }
}
