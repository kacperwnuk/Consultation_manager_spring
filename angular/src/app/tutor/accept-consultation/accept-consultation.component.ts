import { Component, OnInit } from '@angular/core';
import { Principal } from 'src/app/rejestration/resource/principal';
import { Consultation, SearchConsultation } from 'src/app/student/resource/consultation';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-accept-consultation',
  templateUrl: './accept-consultation.component.html',
  styleUrls: ['./accept-consultation.component.css']
})
export class AcceptConsultationComponent implements OnInit {
  user: Principal;
  public collection: Consultation[] = [];
  szukanie: SearchConsultation;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.szukanie = new SearchConsultation;
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
    this.szukanie.status = "CREATED_BY_STUDENT";
    this.szukanie.tutorUsername = this.user.username;

    this.reload();
  }

  reload(){
    let params = new HttpParams();
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/searchConsultations?' + params.toString();

    this.http.post(url, this.szukanie, {
      headers: new HttpHeaders()
          .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))
    }).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
    },
    error =>{
      alert('Błąd przy wczytywaniu');
      console.log(error);
    });
  }

  accept(i) {
    console.log("Zapisujesz sie na konsultacje nr " + i);
    let params = new HttpParams();
    params = params.set('consultationId', this.collection[i].id);
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/acceptConsultation?' + params.toString();
    console.log(url);

    this.http.get(url, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
    }).subscribe(response =>{
        console.log(response);
        this.reload();
        //alert('Zaakceptowano');
      },
      error =>{
        alert('Akceptacja nie powiodła się');
        console.log(error);
      } );
  }

}