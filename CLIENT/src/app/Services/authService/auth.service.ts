import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8181/authentication'

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user, { observe: 'response', responseType: 'text' });
  }

  login(username: string, password: string): Observable<any> {
    const params = new HttpParams().set('username', username).set('password', password);
    return this.http.post(`${this.apiUrl}/login`, {}, { params, observe: 'response', responseType: 'text' });
  }

  adminLogin(username: string, password: string): Observable<any> {
    const params = new HttpParams().set('username', username).set('password', password);
    return this.http.post(`http://localhost:8181/adminAuthentication/login`, {}, { params });
  }


}
