import { Component } from '@angular/core';
import {DefaultLoginLayoutComponent} from "../components/default-login-layout/default-login-layout.component";
import {PrimaryInputComponent} from "../components/primary-input/primary-input.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../user-service";
interface ChangePasswordForm {
  token: FormControl,
  newPassword: FormControl,
  }
@Component({
  selector: 'app-change-password',
  standalone: true,
    imports: [
        DefaultLoginLayoutComponent,
        PrimaryInputComponent,
        ReactiveFormsModule
    ],
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {
  changePasswordForm!: FormGroup<ChangePasswordForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService,
    private userService: UserService
  ){
    this.changePasswordForm = new FormGroup({

      token: new FormControl('', [Validators.required]),
      newPassword: new FormControl('', [Validators.required, Validators.minLength(6)]),

    })
  }





  navigate(){
    this.router.navigate(["login"])
  }
  submit(){
    const token: string = this.changePasswordForm.value.token;
    const newPassword: string = this.changePasswordForm.value.newPassword;
    this.userService.changePassword(token,newPassword).subscribe(
      data => {
        this.toastService.success("Senha alterada  com sucesso!");
        this.router.navigate(['/login']);
      },
      error => {
        this.toastService.error("Erro inesperado! Tente novamente mais tarde");
      },

    )}
}

