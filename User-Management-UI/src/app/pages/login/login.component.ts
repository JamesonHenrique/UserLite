import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, FormRecord, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';
import {User} from "../../user";

interface LoginForm {
  email: FormControl,
  password: FormControl
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent
  ],
  providers: [
    LoginService
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm!: FormGroup<LoginForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService
  ){
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    })
  }

  submit(){
    const user: { password: any; email: any } = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    };

    this.loginService.login(user).subscribe({
      next: () => {
        this.toastService.success("Login feito com sucesso!");
        this.router.navigate(['/users']);
      },
      error: () => this.toastService.error("Erro inesperado! Tente novamente mais tarde")
    })
  }
  navigate(){
    this.router.navigate(["signup"])
  }
  forgotPassword() {
    this.router.navigate(["forgot-password"])
  }
}
