import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from "./user/user.component";
import { MainComponent } from "./main/main.component";
import { ActivityComponent } from "./activity/activity.component";
import { UserManagerComponent } from "./user-manager/user-manager.component";
import { ProfileManagerComponent } from "./profile-manager/profile-manager.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: UserComponent },
  { path: 'main', component: MainComponent },
  { path: 'activity', component: ActivityComponent },
  { path: 'userManager', component: UserManagerComponent },
  { path: 'profileManager', component: ProfileManagerComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
