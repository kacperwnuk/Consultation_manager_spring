import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { User } from '../model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Principal} from '../../rejestration/resource/principal';

@Component({
    selector: 'app-standard-login',
    templateUrl: './standard-login.component.html',
    styleUrls: ['./standard-login.component.css']
})
export class StandardLoginComponent implements OnInit {

  isActiveSpecialWord: boolean = false;
  @Output() shift = new EventEmitter<boolean>();
  username: string;
  password: string;
  user: Principal;

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    ngOnInit() {
    }

    verification() {
        const url = 'https://localhost:8443/login';
        this.user = new Principal();
        this.user.username = this.username;
        this.user.password = this.password;


        let headers = new HttpHeaders();
        headers = headers.append('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`));
        headers = headers.append('Content-Type', 'application/x-www-form-urlencoded');

        const body = JSON.stringify({username: this.user.username, password: this.user.password});

        this.http.get(url, {
          headers: new HttpHeaders()
            .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
          observe: 'response',
          responseType: 'text'
        }).subscribe(response =>{
            console.log(response);
            alert('Logowanie powiodło się!');
            //this.router.navigate(['loged', response.body.username]);
          },
          error =>{
            alert('Logowanie nie powiodło się!');
            console.log(error);
          } );
    }

    forgotPassword() {
        this.shift.emit(false);
    }
}
