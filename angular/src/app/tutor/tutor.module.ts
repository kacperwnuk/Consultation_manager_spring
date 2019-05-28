import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TutorCreateConsultationComponent } from './tutor-create-consultation/tutor-create-consultation.component';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { MainStripTutorComponent } from './main-strip-tutor/main-strip-tutor.component';
import { VerifyUsersComponent } from './verify-users/verify-users.component';
import { TutorConsultationListComponent } from './tutor-consultation-list/tutor-consultation-list.component';
import { AcceptConsultationComponent } from './accept-consultation/accept-consultation.component';


const routes = [
  { path: 'tutorcreateconsultation', component: TutorCreateConsultationComponent},
  { path: 'consultationlist', component: TutorConsultationListComponent},
  { path: 'acceptconsultation', component: AcceptConsultationComponent},
];

@NgModule({
  declarations: [TutorCreateConsultationComponent, MainStripTutorComponent, VerifyUsersComponent, TutorConsultationListComponent, AcceptConsultationComponent],
  imports: [
    CommonModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forChild(routes),
  ],
  exports: [
    TutorCreateConsultationComponent
  ]
})
export class TutorModule { }
