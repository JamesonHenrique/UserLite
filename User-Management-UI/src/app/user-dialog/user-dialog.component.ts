import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {User} from "../user";
import {MatDialogRef} from "@angular/material/dialog";
import {UserService} from "../user-service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-dialog',
  standalone: true,
    imports: [
        FormsModule,
        MatButton,
        MatCard,
        MatCardContent,
        MatCardHeader,
        MatCardTitle,
        MatFormField,
        MatInput,
        MatLabel,
        ReactiveFormsModule
    ],
  templateUrl: './user-dialog.component.html',
  styleUrl: './user-dialog.component.css'
})
export class UserDialogComponent {
  user: User = {id: 0, firstName: '', lastName: '', email: '', password: ''};
  constructor( public dialogRef: MatDialogRef<UserDialogComponent>,private userService: UserService, private router: Router, private snackBar: MatSnackBar) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
  saveUser(){
    this.userService.createUser(this.user).subscribe(
      data => {
        console.log(data);

        this.snackBar.open('User Created Successfully', 'Close', {
          duration: 5000,
        });
        this.goToUserList();
      },
      error => console.log(error)
    );
  }

  goToUserList(){
    this.router.navigate(['/users']);
  }
  onSubmit(){
    console.log(this.user);
    this.saveUser();
  }
}
