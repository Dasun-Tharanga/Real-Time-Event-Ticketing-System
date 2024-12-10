import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, Signal, signal } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private apiUrl = 'http://localhost:8181/tickets'

  constructor(private http: HttpClient) { }

  buyTickets(ticketCount: number, customerID: string = '1234'): Observable<string> {
    // const payload = { customerID, ticketCount };

    const params = new HttpParams().set('customerID', customerID).set('ticketCount', ticketCount);
    return this.http.post<string>(`${this.apiUrl}/purchase`, null, { params });
  }

  addTickets(ticketCount: number, vendorID: string = 'v1234'): Observable<string> {

    const params = new HttpParams().set('ticketCount', ticketCount).set('vendorID', vendorID);
    return this.http.post<string>(`${this.apiUrl}/add`, null, { params });
  }

  // stopAdding(vendorId: string = 'v1234'): Observable<string> {

  //   const params = new HttpParams().set('vendorId', vendorId);
  //   return this.http.post<string>(`${this.apiUrl}/stopReleasingTickets`, null, { params });
  // }
  stopAdding(vendorId: string = 'v1234') {

    const params = new HttpParams().set('vendorId', vendorId);
    return this.http.post(`${this.apiUrl}/stopReleasingTickets`, params);
  }


}
