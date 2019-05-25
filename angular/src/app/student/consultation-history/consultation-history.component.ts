import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute, Router} from '@angular/router';
import {Consultation} from '../resource/consultation';
import {HttpClient, HttpHeaders} from '@angular/common/http';

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

    const url = 'https://localhost:8443/consultations';
    this.http.get(url, {
      headers: new HttpHeaders().set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))}
    ).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
      console.log(typeof this.collection[0].date);
    });
  }

}
