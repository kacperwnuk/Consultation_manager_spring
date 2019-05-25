import { Component, OnInit } from '@angular/core';
import {Principal} from '../../rejestration/resource/principal';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from '../../rejestration/resource/user';
import {Consultation} from '../resource/consultation';
import {promise} from 'selenium-webdriver';

@Component({
  selector: 'app-consultation-sign',
  templateUrl: './consultation-sign.component.html',
  styleUrls: ['./consultation-sign.component.css']
})
export class ConsultationSignComponent implements OnInit {
  user: Principal;
  public collection: Consultation[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];

    this.seeFree();
  }

  sign(i) {
    console.log("Zapisujesz sie na konsultacje nr " + i);
    let params = new HttpParams();
    params = params.set('consultationId', this.collection[i].id);
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/reserveConsultation?' + params.toString();
    console.log(url);

    this.http.get(url, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
    }).subscribe(response =>{
        console.log(response);
        alert('Rejestracja powiodła się!');
      },
      error =>{
        alert('Rejestracja nie powiodła się');
        console.log(error);
      } );
  }
  seeAll() {
    const url = 'https://localhost:8443/consultations';
    this.http.get(url).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
      console.log(typeof this.collection[0].date);
    });
  }

  seeFree(){
      const url = 'https://localhost:8443/freeConsultations';
      this.http.get(url).subscribe((res: Consultation[]) => {
      this.collection = res;
        console.log(this.collection);
        console.log(typeof this.collection[0].date);
      });
    }


}
