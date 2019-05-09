import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {User} from '../resource/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main-registered',
  templateUrl: './main-registered.component.html',
  styleUrls: ['./main-registered.component.css']
})
export class MainRegisteredComponent implements OnInit {

  ngOnInit() {
  }

  title = 'pik-app';
  username: string;
  password: string;
  user: User;
  constructor(private http: HttpClient, private router: Router) {}

  public getOne() {
    const url = 'https://localhost:8443/register';
    this.user = new User();
    this.user.username = this.username;
    this.user.password = this.password;
    this.user.enabled = false;
    this.user.role = 1;

    this.http.post<User>(url, this.user, {
      observe: 'response',
      responseType: 'json'
    }).subscribe(response => {
      console.log(response);
      this.router.navigate(['registered', response.body.username], {skipLocationChange: true});
    },
    error =>{
      alert("Rejestracja nie powiodła się!");
      console.log(error);
    } );
  }

}
