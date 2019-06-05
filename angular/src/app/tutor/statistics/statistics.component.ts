import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './canvasjs.min';
import { SearchConsultation, Consultation } from 'src/app/student/resource/consultation';
import { Principal } from 'src/app/rejestration/resource/principal';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  user: Principal;
  szukanie: SearchConsultation;

  dateStart: string;
  dateEnd: string;
  startTime: string;
  endTime: string;
  student: string;
	userName: string;
  status: string;
  public activeGraph: boolean;

  miesiac: number[] = [];

  public collection: Consultation[] = [];


  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }
  
	ngOnInit() {
    this.generateGraph();

    this.user = new Principal();
    this.szukanie = new SearchConsultation();
    this.user.username = this.route.snapshot.params.username;
    this.user.password = this.route.snapshot.params.password;
    
    this.userName = this.user.username;
    this.status = null;
    this.activeGraph = true;

    this.reload();

  }
  reload(){
    this.startTime = "01:00";
    this.endTime = "23:00";
    this.student = null;
    this.dateStart = null;
    this.dateEnd = null;
  }
  
  search(){
    if(this.dateStart!=null && this.dateEnd!=null){
      const dataStart = new Date(this.dateStart);
      const dataEnd = new Date(this.dateEnd);
      const stringStart = dataStart.toDateString() + ', ' + this.startTime;
      const stringEnd = dataEnd.toDateString() + ', ' + this.endTime;
  
      this.szukanie.dateStart = new Date(new DatePipe('en-Us').transform(new Date(stringStart),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
      this.szukanie.dateEnd = new Date(new DatePipe('en-Us').transform(new Date(stringEnd),'EEE MMM dd yyyy HH:mm:ss', 'GMT+0400'));
    }
    this.szukanie.status = this.status;
    this.szukanie.tutorUsername = this.userName;
    this.szukanie.studentUsername = this.student;

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

  generateGraph(){
    for (const iterator of this.collection) {
      this.miesiac[iterator.date.getMonth()]++;
    }

    var chart = new CanvasJS.Chart("chartContainer", {
      animationEnabled: true,
      
      title:{
        text:"Konsultacje w dniach"
      },
      axisX:{
        interval: 1
      },
      axisY2:{
        interlacedColor: "rgba(1,77,101,.2)",
        gridColor: "rgba(1,77,101,.1)",
        title: ""
      },
      data: [{
        type: "bar",
        name: "companies",
        axisYType: "secondary",
        color: "#014D65",
        dataPoints: [
          { y: this.miesiac[0], label: "Styczeń" },
          { y: this.miesiac[1], label: "Luty" },
          { y: this.miesiac[2], label: "Marzec" },
          { y: this.miesiac[3], label: "Kwiecień" },
          { y: this.miesiac[4], label: "Maj" },
          { y: this.miesiac[5], label: "Czerwiec" },
          { y: this.miesiac[6], label: "Lipiec" },
          { y: this.miesiac[7], label: "Sierpień" },
          { y: this.miesiac[8], label: "Wrzesień" },
          { y: this.miesiac[9], label: "Październik" },
          { y: this.miesiac[10], label: "Listopad" },
          { y: this.miesiac[11], label: "Grudzień" }
        ]
      }]
    });
    chart.render();
    
  }
}
