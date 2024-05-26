import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';
import {User} from "../user";
import {LoginRequest} from "../types/LoginRequest";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
baseUrl = "http://localhost:8080/login"
  constructor(private httpClient: HttpClient) { }

  login(user: LoginRequest){
    return this.httpClient.post<LoginResponse>(`${this.baseUrl}`,user).pipe(
      tap((value) => {
        sessionStorage.setItem("auth-token", value.token)
        sessionStorage.setItem("username", value.name)
      })
    )
  }
}
