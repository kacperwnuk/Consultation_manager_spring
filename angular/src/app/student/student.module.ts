import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainStudentComponent } from './main-student/main-student.component';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {MainRegisteredComponent} from "../rejestration/main-registered/main-registered.component";
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ConsultationSignComponent } from './consultation-sign/consultation-sign.component';
import { ConsultationHistoryComponent } from './consultation-history/consultation-history.component';
import { ConsultationCreateComponent } from './consultation-create/consultation-create.component';
import { ConsultationArchiweComponent } from './consultation-archiwe/consultation-archiwe.component';

const routes = [
  { path: 'mainstudent', component: MainStudentComponent},
  { path: 'changepass', component: ChangePasswordComponent},
  {path: 'consultationhistory', component: ConsultationHistoryComponent},
  {path: 'consultationsign', component: ConsultationSignComponent},
  {path: 'consultationcreate', component: ConsultationCreateComponent},
  {path: 'consultationarchiwe', component:  ConsultationArchiweComponent},
];

@NgModule({
  declarations: [MainStudentComponent, ChangePasswordComponent, ConsultationSignComponent, ConsultationHistoryComponent, ConsultationCreateComponent, ConsultationArchiweComponent],
  imports: [
    CommonModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forChild(routes),
  ],
  exports: [
    MainStudentComponent
  ]
})
export class StudentModule { }
