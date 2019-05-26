import { Component, OnInit } from '@angular/core';
import {Principal} from '../../rejestration/resource/principal';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Consultation} from '../resource/consultation';
import {DatePipe} from '@angular/common';
import { registerLocaleData } from '@angular/common';
import localePl from '@angular/common/locales/pl';


@Component({
  selector: 'app-consultation-create',
  templateUrl: './consultation-create.component.html',
  styleUrls: ['./consultation-create.component.css']
})
export class ConsultationCreateComponent implements OnInit {
  user: Principal;
  konsultacja: Consultation;
  date: string;
  startTime: string;
  endTime: string;
  tutor: string;
  room: number;
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.user = new Principal();
    this.konsultacja = new Consultation();
    this.user.username = this.route.snapshot.params.username;
    this.user.password = this.route.snapshot.params.password;
    console.log(this.user.username);
    console.log(this.user.password);
  }

  verification() {
    const data = new Date(this.date);
    this.konsultacja.date = data;
    const stringStart = data.toDateString() + ', ' + this.startTime;
    const stringEnd = data.toDateString() + ', ' + this.endTime;

    this.konsultacja.consultationStartTime = new Date(new DatePipe('en-Us').transform(new Date(stringStart),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
    this.konsultacja.consultationEndTime = new Date(new DatePipe('en-Us').transform(new Date(stringEnd),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
    this.konsultacja.room = this.room;
    console.log('Tutor: ' + this.tutor);
    console.log('Pokój: ' + this.konsultacja.room);
    console.log('Data: ' + this.konsultacja.date);
    console.log('Rozpoczecie: ' + this.konsultacja.consultationStartTime);
    console.log('Zakonczenie: ' + this.konsultacja.consultationEndTime);

    let params = new HttpParams();
    params = params.set('tutorUsername', this.tutor);
    params = params.set('studentUsername', this.user.username);
    const url = 'https://localhost:8443/studentConsultation?' + params.toString();

    this.http.post(url, this.konsultacja, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
    }).subscribe(response =>{
        console.log(response);
        alert('Konsultacja utworzona pomyślnie!');
      },
      error =>{
        alert('Błąd przy tworzeniu konsultacji!');
        console.log(error);
      } );

  }

}
