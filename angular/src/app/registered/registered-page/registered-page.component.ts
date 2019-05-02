import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../resource/user';

@Component({
  selector: 'app-registered-page',
  templateUrl: './registered-page.component.html',
  styleUrls: ['./registered-page.component.css']
})
export class RegisteredPageComponent implements OnInit {

  user: string;
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.route.snapshot.params['user'];

  }

}
