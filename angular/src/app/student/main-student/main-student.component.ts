import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Principal} from '../../rejestration/resource/principal';


@Component({
  selector: 'app-main-student',
  templateUrl: './main-student.component.html',
  styleUrls: ['./main-student.component.css']
})
export class MainStudentComponent implements OnInit {

  user: Principal;
  //username: string;
  //password: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
  }

}
