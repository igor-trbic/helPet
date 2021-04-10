import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './models/user.model';
import { catchError, map, tap } from 'rxjs/operators';
import { BusinessRegister } from './models/businesRegister.model';
import { UserDTO } from './models/user.dto.model';

@Injectable()
export class RegisterService {

  private apiUrl = '/helpet/register';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  register(user: UserDTO, token?: String): Observable<UserDTO> {
    let url = this.apiUrl;
    if (token) {
      url += "?token=" + token;
    }
    return this.http.post<UserDTO>(url, user, this.httpOptions).pipe(
      tap((newUser: UserDTO) => console.log(`added hero w/ id=${newUser.username}`))
    );
  }

  registerBusiness(business: BusinessRegister): Observable<BusinessRegister> {
    return this.http.post<BusinessRegister>(this.apiUrl + '/business', business, this.httpOptions).pipe(
      tap((newUser: BusinessRegister) => console.log(`added hero w/ id=${newUser}`))
    );
  }
}
