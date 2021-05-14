import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Appointment } from './models/appointment.model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiUrl = '/helpet/appointments';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                'Access-Control-Allow-Origin': '*' })
  };

  constructor(
    private http: HttpClient) { }

  get(): Observable<Appointment[]> {
    return this.http.get(this.apiUrl, this.httpOptions)
    .pipe(
      tap((req: Appointment[]) => console.log(`req w/ id=${req}`))
    );
  }

  getForBusiness(id): Observable<Appointment[]> {
    return this.http.get(this.apiUrl + '/business/' + id, this.httpOptions)
    .pipe(
      tap((req: Appointment[]) => console.log(`req w/ id=${req}`))
    );
  }

  create(appointment: Appointment): Observable<Appointment> {
    return this.http.post(this.apiUrl, appointment, this.httpOptions)
    .pipe(
      tap((req: Appointment) => console.log(`req w/ id=${req}`))
    );
  }

  update(appointment: Appointment): Observable<Appointment> {
    return this.http.put(this.apiUrl + '/' + appointment.id, appointment, this.httpOptions)
    .pipe(
      tap((req: Appointment) => console.log(`req w/ id=${req}`))
    );
  }

  remove(appintmentId: number): Observable<Appointment> {
    return this.http.delete(this.apiUrl + '/' + appintmentId, this.httpOptions)
    .pipe(
      tap((req: Appointment) => console.log(`req w/ id=${req}`))
    );
  }
}
