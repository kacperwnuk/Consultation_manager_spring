import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {Consultation} from "../../student/resource/consultation";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-tutor-create-consultation',
  templateUrl: './tutor-create-consultation.component.html',
  styleUrls: ['./tutor-create-consultation.component.css']
})
export class TutorCreateConsultationComponent implements OnInit {
  user: Principal;
  konsultacja: Consultation;
  date: string;
  startTime: string;
  endTime: string;
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
    this.konsultacja.consultationStartTime = new Date(stringStart);
    this.konsultacja.consultationEndTime = new Date(stringEnd);
    this.konsultacja.room = this.room;
    console.log('Pokój: ' + this.konsultacja.room);
    console.log('Data: ' + this.konsultacja.date);
    console.log('Rozpoczecie: ' + this.konsultacja.consultationStartTime);
    console.log('Zakonczenie: ' + this.konsultacja.consultationEndTime);

    let params = new HttpParams();
    params = params.set('tutorUsername', this.user.username);
    const url = 'https://localhost:8443/consultation?' + params.toString();

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
