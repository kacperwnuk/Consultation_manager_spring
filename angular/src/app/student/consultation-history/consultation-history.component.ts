import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-consultation-history',
  templateUrl: './consultation-history.component.html',
  styleUrls: ['./consultation-history.component.css']
})
export class ConsultationHistoryComponent implements OnInit {
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
