import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute, Router} from '@angular/router';
import {Consultation, SearchConsultation} from '../resource/consultation';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-consultation-archiwe',
  templateUrl: './consultation-archiwe.component.html',
  styleUrls: ['./consultation-archiwe.component.css']
})
export class ConsultationArchiweComponent implements OnInit {
  user: Principal;
  szukanie: SearchConsultation;

  date: string;
  startTime: string;
  endTime: string;
  tutor: string;
	userName: string;
  status: string;

  public collection: Consultation[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.szukanie = new SearchConsultation();
    this.user.username = this.route.snapshot.params.username;
    this.user.password = this.route.snapshot.params.password;
    console.log(this.user.username);
    console.log(this.user.password);
    
    this.userName = this.user.username;
    this.status = null;

    this.reload();
  }

reload(){
  this.startTime = null;
  this.endTime = null;
  this.tutor = null;
  this.date = null;
}

  search(){
    if(this.date!=null){
      const data = new Date(this.date);
      const stringStart = data.toDateString() + ', ' + this.startTime;
      const stringEnd = data.toDateString() + ', ' + this.endTime;
  
      this.szukanie.dateStart = new Date(new DatePipe('en-Us').transform(new Date(stringStart),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
      this.szukanie.dateEnd = new Date(new DatePipe('en-Us').transform(new Date(stringEnd),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
    }
    this.szukanie.status = this.status;
    this.szukanie.tutorUsername = this.tutor;
    this.szukanie.studentUsername = this.userName;

    let params = new HttpParams();
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/searchConsultations?' + params.toString();

    this.http.post(url, this.szukanie, {
      headers: new HttpHeaders()
          .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))
    }).subscribe((res: Consultation[]) => {
      this.collection = res;
      this.reload();
      console.log(this.collection);
    },
    error =>{
      alert('Błąd przy wczytywaniu');
      this.reload();
      console.log(error);
    });
  }
}
