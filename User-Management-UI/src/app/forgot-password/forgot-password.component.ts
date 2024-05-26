import { Component } from '@angular/core';
import {DefaultLoginLayoutComponent} from "../components/default-login-layout/default-login-layout.component";
import {PrimaryInputComponent} from "../components/primary-input/primary-input.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../user-service";
import {User} from "../user";
interface EmailForm {
  email: FormControl
}
@Component({
  selector: 'app-forgot-password',
  standalone: true,
    imports: [
        DefaultLoginLayoutComponent,
        PrimaryInputComponent,
        ReactiveFormsModule
    ],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  emailForm!: FormGroup<EmailForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService,
    private userService: UserService
  ){
    this.emailForm = new FormGroup({

      email: new FormControl('', [Validators.required, Validators.email]),

    })
  }





  navigate(){
    this.router.navigate(["login"])
  }
  submit(){
    const email: string = this.emailForm.value.email;
this.router.navigate(["change-password"]);
    this.userService.forgotPassword(email).subscribe(
      next => {
        this.toastService.success("Token para mudar senha enviado  com sucesso!");

      },
      error => {
        this.toastService.error("Erro inesperado! Tente novamente mais tarde");
      }
    );

  }


}
