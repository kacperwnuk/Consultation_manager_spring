import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './login/login.module';
import { CoreModule } from './core/core.module';
import { StandardLoginComponent } from './login/standard-login/standard-login.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { MainRegisteredComponent } from './rejestration/main-registered/main-registered.component';
import { RejestrationModule } from './rejestration/rejestration.module';
import { LogowanieComponent } from './login/logowanie/logowanie.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch:'full' },

];

@NgModule({
  declarations: [
    AppComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ToastrModule.forRoot(),
    LoginModule,
    CoreModule,
    RejestrationModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
