import { Component } from '@angular/core';
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatFormField, MatInput} from "@angular/material/input";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {User} from "../user";
import {UserService} from "../user-service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {DefaultLoginLayoutComponent} from "../components/default-login-layout/default-login-layout.component";
import {PrimaryInputComponent} from "../components/primary-input/primary-input.component";
import {LoginService} from "../services/login.service";
import {ToastrService} from "ngx-toastr";
interface UpdateForm{
  firstName: FormControl,
  email: FormControl,

  lastName: FormControl
}

@Component({
  selector: 'app-user-update',
  standalone: true,
  imports: [
    MatButton,
    MatInput,
    MatFormField,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    DefaultLoginLayoutComponent,
    PrimaryInputComponent,
    ReactiveFormsModule,
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent
  ],
  templateUrl: './user-update.component.html',
  styleUrl: './user-update.component.css'
})
export class UserUpdateComponent {
  updateForm!: FormGroup<UpdateForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastrService,
    private userService: UserService
  ){
    this.updateForm = new FormGroup({

      firstName: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),

      lastName: new FormControl('', [Validators.required, Validators.minLength(6)]),
    })
  }





  navigate(){
    this.router.navigate(["users"])
  }
  onSubmit(){
    const user: User = {
      id: 0,
      email: this.updateForm.value.email,
      password:'',
      firstName: this.updateForm.value.firstName,
      lastName: this.updateForm.value.lastName
    };

    this.userService.updateUser(user.id,user).subscribe(
      data => {
        this.toastService.success("Usuario alterado feito com sucesso!");
        this.router.navigate(['/users']);
      },
      error => {
        this.toastService.error("Erro inesperado! Tente novamente mais tarde");
      }
  )}
}
