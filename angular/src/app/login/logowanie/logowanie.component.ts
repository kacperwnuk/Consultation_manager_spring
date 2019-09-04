import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logowanie',
  templateUrl: './logowanie.component.html',
  styleUrls: ['./logowanie.component.css']
})
export class LogowanieComponent implements OnInit {

    constructor() { }

    ngOnInit() {
  }

    photoUrl = "assets/images/login.png";
    isActiveStandardLogin: boolean = true;

    onShift(is: boolean) {
        this.isActiveStandardLogin = is;
    }
}
