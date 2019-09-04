import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { LogowanieComponent } from './logowanie/logowanie.component';
import { StandardLoginComponent } from './standard-login/standard-login.component';
import { RouterModule } from '@angular/router';

const routes = [
  { path: 'login', 
    component: LogowanieComponent,
    children: [{
      path: '',
      component: StandardLoginComponent,
    }]
  },

  { path: 'forgot', 
    component: LogowanieComponent,
    children: [{
      path: '',
      component: ForgotPasswordComponent,
    }]
  },
];

@NgModule({
  declarations: [
    ForgotPasswordComponent,
    LogowanieComponent, 
    StandardLoginComponent
  ],
  imports: [
    FormsModule,
    RouterModule,
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    LogowanieComponent
  ]
})
export class LoginModule { }
