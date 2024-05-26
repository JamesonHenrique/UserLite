import {Component, Input} from '@angular/core';
import {User} from "../user";
import {UserService} from "../user-service";
import {FormsModule} from "@angular/forms";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {Router, RouterLink} from "@angular/router";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {UserDialogComponent} from "../user-dialog/user-dialog.component";

@Component({
  selector: 'app-user-create',
  standalone: true,
  imports: [
    FormsModule,
    MatFormField,
    MatFormFieldModule,
    MatInput,
    MatButton,
    RouterLink,
    MatCardContent,
    MatCardTitle,
    MatCardHeader,
    MatCard
  ],
  templateUrl: './user-create.component.html',
  styleUrl: './user-create.component.css'
})
export class UserCreateComponent {
  user: User = {id: 0, firstName: '', lastName: '', email: '', password: ''};
  constructor(public dialog:MatDialog ,private userService: UserService, private router: Router, private snackBar: MatSnackBar) { }


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
  openDialog(): void {
    const dialogRef = this.dialog.open(UserDialogComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Handle the result
    });
    }
    goToUserList(){
    this.router.navigate(['/users']);
  }
  onSubmit(){
    console.log(this.user);
    this.saveUser();
  }
  }
