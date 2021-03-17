import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthReq } from './models/authReq.model';
import { UserDTO } from './models/user.dto.model';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = '/helpet/auth';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  login(authReq: UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(this.apiUrl, authReq, this.httpOptions).pipe(
      tap((req: UserDTO) => console.log(`req w/ id=${req}`))
    );
  }
}
