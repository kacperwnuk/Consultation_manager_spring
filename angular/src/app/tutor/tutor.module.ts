import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TutorCreateConsultationComponent } from './tutor-create-consultation/tutor-create-consultation.component';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";


const routes = [
  { path: 'tutorcreateconsultation', component: TutorCreateConsultationComponent},
];

@NgModule({
  declarations: [TutorCreateConsultationComponent],
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
