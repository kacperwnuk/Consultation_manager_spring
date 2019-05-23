import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisteredPageComponent} from './rejestration/registered-page/registered-page.component';
import {MainStudentComponent} from "./student/main-student/main-student.component";
import {ConsultationSignComponent} from "./student/consultation-sign/consultation-sign.component";
import {ConsultationHistoryComponent} from "./student/consultation-history/consultation-history.component";
import {ChangePasswordComponent} from "./student/change-password/change-password.component";
import {MainStripComponent} from './student/main-strip/main-strip.component';
import {ConsultationCreateComponent} from "./student/consultation-create/consultation-create.component";
import {TutorCreateConsultationComponent} from "./tutor/tutor-create-consultation/tutor-create-consultation.component";

const routes: Routes = [
  {path: 'registered/:user', component: RegisteredPageComponent},
  {path: 'mainstudent/:username/:password', component: MainStudentComponent},
  {path: 'consultationsign/:username/:password', component: ConsultationSignComponent},
  {path: 'consultationhistory/:username/:password', component: ConsultationHistoryComponent},
  {path: 'changepass/:username/:password', component: ChangePasswordComponent},
  {path: 'consultationcreate/:username/:password', component: ConsultationCreateComponent},
  {path: 'app-main-strip/:username/:password', component: MainStripComponent},
  {path: 'tutorcreateconsultation/:username/:password', component: TutorCreateConsultationComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  declarations: [
    MainStripComponent
  ],
  exports: [RouterModule, MainStripComponent]
})
export class AppRoutingModule { }
