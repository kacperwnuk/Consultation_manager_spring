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
import {VerifyUsersComponent} from './tutor/verify-users/verify-users.component';
import { TutorConsultationListComponent } from './tutor/tutor-consultation-list/tutor-consultation-list.component';
import { ConsultationArchiweComponent } from './student/consultation-archiwe/consultation-archiwe.component';
import { AcceptConsultationComponent } from './tutor/accept-consultation/accept-consultation.component';

const routes: Routes = [
  {path: 'registered/', component: RegisteredPageComponent},
  {path: 'mainstudent/:username/:password', component: MainStudentComponent},
  {path: 'consultationsign/:username/:password', component: ConsultationSignComponent},
  {path: 'consultationhistory/:username/:password', component: ConsultationHistoryComponent},
  {path: 'consultationarchiwe/:username/:password', component:  ConsultationArchiweComponent},
  {path: 'changepass/:username/:password', component: ChangePasswordComponent},
  {path: 'consultationcreate/:username/:password', component: ConsultationCreateComponent},
  {path: 'app-main-strip/:username/:password', component: MainStripComponent},
  {path: 'tutorcreateconsultation/:username/:password', component: TutorCreateConsultationComponent},
  {path: 'consultationlist/:username/:password', component: TutorConsultationListComponent},
  {path: 'acceptconsultation/:username/:password', component: AcceptConsultationComponent},
  {path: 'verifyusers/:username/:password', component: VerifyUsersComponent},
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
