import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisteredPageComponent} from './rejestration/registered-page/registered-page.component';
import {MainStudentComponent} from "./student/main-student/main-student.component";
import {ConsultationSignComponent} from "./student/consultation-sign/consultation-sign.component";
import {ConsultationHistoryComponent} from "./student/consultation-history/consultation-history.component";
import {ChangePasswordComponent} from "./student/change-password/change-password.component";

const routes: Routes = [
  {path: 'registered/:user', component: RegisteredPageComponent},
  {path: 'mainstudent/:username/:password', component: MainStudentComponent},
  {path: 'consultationsign/:username/:password', component: ConsultationSignComponent},
  {path: 'consultationhistory/:username/:password', component: ConsultationHistoryComponent},
  {path: 'changepass/:username/:password', component: ChangePasswordComponent},

  { path: '', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
