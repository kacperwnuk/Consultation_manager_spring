import { Component, OnInit } from '@angular/core';
import {Principal} from '../../rejestration/resource/principal';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-main-strip',
  templateUrl: './main-strip.component.html',
  styleUrls: ['./main-strip.component.css']
})
export class MainStripComponent implements OnInit {
  user: Principal;
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
    console.log(this.user.username);
    console.log(this.user.password);
  }

}
