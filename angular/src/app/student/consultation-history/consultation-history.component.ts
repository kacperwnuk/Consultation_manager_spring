import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute, Router} from '@angular/router';
import {Consultation} from '../resource/consultation';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { ResourceLoader } from '@angular/compiler';

@Component({
  selector: 'app-consultation-history',
  templateUrl: './consultation-history.component.html',
  styleUrls: ['./consultation-history.component.css']
})
export class ConsultationHistoryComponent implements OnInit {
  user: Principal;
  public collection: Consultation[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
    
    this.reload();
  }

  reload(){
    const url = 'https://localhost:8443/consultations';
    this.http.get(url, {
      headers: new HttpHeaders().set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))}
    ).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
      console.log(typeof this.collection[0].date);
    });
  }

  cancel(i) {
    console.log("Zapisujesz sie na konsultacje nr " + i);
    let params = new HttpParams();
    params = params.set('consultationId', this.collection[i].id);
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/cancelConsultation?' + params.toString();
    console.log(url);

    this.http.get(url, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
    }).subscribe(response =>{
        console.log(response);
        this.reload();
        //alert('Anulowanie powiodło się!');
      },
      error =>{
        alert('Anulowanie nie powiodło się');
        console.log(error);
      } );
  }
}
