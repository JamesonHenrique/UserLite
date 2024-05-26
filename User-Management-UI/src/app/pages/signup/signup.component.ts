import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, FormRecord, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../user-service';
import { User } from '../../user';

interface SignupForm {
  firstName: FormControl,
  email: FormControl,
  password: FormControl,
  lastName: FormControl
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent
  ],
  providers: [
    LoginService
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignUpComponent {
  signupForm!: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService,
    private userService: UserService
  ){
    this.signupForm = new FormGroup({

      firstName: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(6)]),
    })
  }





  navigate(){
    this.router.navigate(["login"])
  }
  submit(){
    const user: User = {
      id: 0,
      email: this.signupForm.value.email,
      password: this.signupForm.value.password,
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName
    };

    this.userService.createUser(user).subscribe(
      data => {
        this.toastService.success("Login feito com sucesso!");
        this.router.navigate(['/users']);
      },
      error => {
        this.toastService.error("Erro inesperado! Tente novamente mais tarde");
      }
  )}
}



