import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { User } from '../model';
import { HttpClient } from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
    selector: 'app-standard-login',
    templateUrl: './standard-login.component.html',
    styleUrls: ['./standard-login.component.css']
})
export class StandardLoginComponent implements OnInit {

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    ngOnInit() {
    }

    isActiveSpecialWord: boolean = false;
    @Output() shift = new EventEmitter<boolean>();
    username: string;
    password: string;
    user: User;

    verification() {
        const url = 'https://localhost:8443/login';
        this.user = new User();
        this.user.username = this.username;
        this.user.password = this.password;
        this.user.enabled = false;
        this.user.role = 1;

        this.http.post<User>(url, this.user, {
            observe: 'response',
            responseType: 'json'
          }).subscribe(response =>{
            console.log(response);
            this.router.navigate(['loged', response.body.username]);
          },
          error =>{
            alert("Logowanie nie powiodło się!");
            console.log(error);
          } );
    }

    forgotPassword() {
        this.shift.emit(false);
    }
}
