import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'; //x button
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ApplicationComponent } from './components/applications/application.component';
import { AddBtnComponent } from './components/add-btn/add-btn.component';
import { ApplicationItemComponent } from './components/application-item/application-item.component';
import { AddApplicationFormComponent } from './components/add-application-form/add-application-form.component';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PopupFormComponent } from './components/popup-form/popup-form.component';
import { PopupFormUpdateComponent } from './components/popup-form-update/popup-form-update.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component'
import { HttpInterceptorService } from './services/http-interceptor.service';
import {ApplicationServiceService} from "./services/application-service.service";
import {AppRoutingModule} from "./app-routing.module";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ApplicationComponent,
    AddBtnComponent,
    ApplicationItemComponent,
    AddApplicationFormComponent,
    PopupFormComponent,
    PopupFormUpdateComponent,
    FileUploadComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [ApplicationServiceService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
