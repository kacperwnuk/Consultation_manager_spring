import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainRegisteredComponent } from './main-registered/main-registered.component';
import { RouterModule } from '@angular/router';
import { RegisteredPageComponent } from './registered-page/registered-page.component';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

const routes = [
  { path: 'rejestration', component: MainRegisteredComponent },
  { path: 'registered', component: RegisteredPageComponent },
];

@NgModule({
  declarations: [
    MainRegisteredComponent,
    RegisteredPageComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    MainRegisteredComponent
  ]
})
export class RejestrationModule { }
