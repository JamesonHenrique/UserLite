import { HttpClient } from "@angular/common/http";
import {map, Observable} from "rxjs";
import { User } from "./user";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',

})
export class UserService {

  private baseURL = "http://localhost:8080/api/users";

  constructor(private httpClient: HttpClient) { }

  getUserList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }

  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, user);
  }

  getUserId(id: number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/${id}`);
  }

  updateUser(id: number, user: User): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, user);
  }

  deleteUser(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

logout(): Promise<void> {
  return new Promise<void>(resolve => {
    sessionStorage.removeItem('auth-token');
    resolve();
  });
}

  forgotPassword(email: string): Observable<string> {
    return this.httpClient.get<string>(`${this.baseURL}/forgot-password?email=${email}`);
  }
  changePassword(token: string, newPassword: string): Observable<string> {
    return this.httpClient.put(`${this.baseURL}/change-password?token=${token}&newPassword=${newPassword}`, {}, {responseType: 'text'});
  }

  }
