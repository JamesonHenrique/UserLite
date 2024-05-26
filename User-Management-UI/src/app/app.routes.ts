import { Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { SignUpComponent } from './pages/signup/signup.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './services/auth-guard.service';
import { UserListComponent } from './user-list/user-list.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import {ForgotPasswordComponent} from "./forgot-password/forgot-password.component";
import {ChangePasswordComponent} from "./change-password/change-password.component";
export const routes: Routes = [

  { path: 'users', component: UserListComponent,     canActivate: [AuthGuard] },

  { path: 'user-update', component: UserUpdateComponent,     canActivate: [AuthGuard] },
  {
    path: "",
    component: LoginComponent
},
  {path:"forgot-password",component:ForgotPasswordComponent},
  {path:"change-password",component:ChangePasswordComponent},
{
    path: "signup",
    component: SignUpComponent
},
{ path: '**', redirectTo: 'users' }

];
