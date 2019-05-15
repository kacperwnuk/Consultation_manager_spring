import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    }
    mail: string = "";
    sendedMessage: boolean = false;

    sendMessage() {
        this.sendedMessage = true;
    }

    @Output() shift = new EventEmitter<boolean>();

    returnToLogin() {
        this.shift.emit(true);
    }
}
