import { Component, Input, OnInit } from '@angular/core';
import { User} from '../user';
import {Router, RouterLink} from '@angular/router';
import { UserService } from '../user-service';
import { CommonModule } from '@angular/common';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatAnchor, MatButton} from "@angular/material/button";
import {MatToolbar} from "@angular/material/toolbar";
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, MatTable, MatColumnDef, MatButton, MatHeaderCell, MatCell, MatRow, MatHeaderRow, MatHeaderCellDef, MatCellDef, MatHeaderRowDef, MatRowDef, MatToolbar, MatAnchor, RouterLink],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {

 @Input() users: User[] = [];
  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
  }
  userDetails(id: number){
    this.router.navigate(['user-details', id]);
  }

  private getUsers(){
    this.userService.getUserList().subscribe(data => {
      this.users = data;
    });
  }


  updateUser(id: number){
    this.router.navigate(['update-user', id]);
  }

  deleteUser(id: number){
    this.userService.deleteUser(id).subscribe( data => {
      console.log(data);
      this.getUsers();
    })
  }

  async logout() {
    await this.userService.logout();
    this.router.navigate(['/login']).then(() => {
      window.location.reload();
    });
  }
}
