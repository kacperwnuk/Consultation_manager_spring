import { Component, OnInit } from '@angular/core';
import {Principal} from '../../rejestration/resource/principal';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
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
    console.log(this.user.username);
    console.log(this.user.password);

    const url = 'https://localhost:8443/consultations';
    this.http.get(url).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
      
    });
  }
}
