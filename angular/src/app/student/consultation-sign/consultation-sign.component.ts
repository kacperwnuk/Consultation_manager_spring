import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-consultation-sign',
  templateUrl: './consultation-sign.component.html',
  styleUrls: ['./consultation-sign.component.css']
})
export class ConsultationSignComponent implements OnInit {
  user: Principal;
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
    console.log(this.user.username);
    console.log(this.user.password);
  }

}
