import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {Consultation} from "../../student/resource/consultation";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-tutor-create-consultation',
  templateUrl: './tutor-create-consultation.component.html',
  styleUrls: ['./tutor-create-consultation.component.css']
})
export class TutorCreateConsultationComponent implements OnInit {
  user: Principal;
  konsultacja: Consultation;
  date: string;
  startTime: string;
  endTime: string;
  room: number;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.user = new Principal();
    this.konsultacja = new Consultation();
    this.user.username = this.route.snapshot.params.username;
    this.user.password = this.route.snapshot.params.password;
    console.log(this.user.username);
    console.log(this.user.password);
  }

}
