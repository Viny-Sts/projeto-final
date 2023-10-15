import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgOptimizedImage } from "@angular/common";
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UserComponent } from './user/user.component';
import { MainComponent } from './main/main.component';
import { ActivityComponent } from './activity/activity.component';
import { UserManagerComponent } from './user-manager/user-manager.component';
import { ProfileManagerComponent } from './profile-manager/profile-manager.component';
import { ProfileComponent } from './profile/profile.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    NavbarComponent,
    LoginComponent,
    UserComponent,
    MainComponent,
    ActivityComponent,
    UserManagerComponent,
    ProfileManagerComponent,
    ProfileComponent,
    ForbiddenComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgOptimizedImage,
        FormsModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
