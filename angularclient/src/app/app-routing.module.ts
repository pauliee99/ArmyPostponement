import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HeaderComponent} from "./components/header/header.component";
import {LogoutComponent} from "./components/logout/logout.component";
import {LoginComponent} from "./components/login/login.component";
import {AppComponent} from "./app.component";

const routes: Routes = [
  // { path: 'users', component: UserListComponent },
  // { path: '', component: AppComponent },
  { path: 'postponements', component: HeaderComponent },
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  //forChild
  exports: [RouterModule]
})
export class AppRoutingModule { }
