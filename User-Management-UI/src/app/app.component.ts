import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';

import { HttpClient, HttpClientModule } from '@angular/common/http';
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";

import { ToastrModule, ToastrService } from 'ngx-toastr';
import { UserUpdateComponent } from './user-update/user-update.component';
import { UserDialogComponent } from './user-dialog/user-dialog.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,

    HttpClientModule,
    MatToolbarModule,
    MatCardModule,
    MatDialogModule,
    MatTableModule,
    MatButtonModule, RouterLink,
    ToastrModule

  ],
  providers: [HttpClient, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'User Lite';

  constructor(public dialog: MatDialog) { }
  updateDialog(): void {
    const dialogRef = this.dialog.open(UserUpdateComponent, {
      width: '250px',

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Handle the result
    });

  }
  createDialog(): void {
    const dialogRef = this.dialog.open(UserDialogComponent, {
      width: '250px',

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Handle the result
    });

}
}


