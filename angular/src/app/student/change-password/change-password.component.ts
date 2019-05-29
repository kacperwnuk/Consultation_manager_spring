import { Component, OnInit } from '@angular/core';
import {Principal} from "../../rejestration/resource/principal";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ChangePasswordForm} from "../resource/ChangePasswordForm";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  user: Principal;
  newpassword1: string;
  newpassword2: string;
  form : ChangePasswordForm;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.newpassword1 = "";
    this.newpassword2 = "";
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];
    console.log(this.user.username);
    console.log(this.user.password);
  }


  verification() {
    console.log("newpass1 " + this.newpassword1);
    console.log("newpass2 " + this.newpassword2);

    if (this.newpassword1 !== this.newpassword2)
    {
      alert('Hasła muszą być identyczne!');
    }
    else if(this.newpassword1.length < 4)
    {
      alert('Haslo zbyt krotkie!');
    }
    else
    {
      const url = 'https://localhost:8443/user/changePassword/' + this.user.username;
      // const toBody = JSON.stringify({oldPassword: this.user.password, newPassword: this.newpassword1, newPasswordRepeat: this.newpassword2});
      this.form = new ChangePasswordForm();
      this.form.oldPassword = this.user.password;
      this.form.newPassword = this.newpassword1;
      this.form.newPasswordRepeat = this.newpassword2;

      console.log('Lącze pod ' + url);
      this.http.post(url, this.form, {
        headers: new HttpHeaders()
          .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
        observe: 'response',
        responseType: 'text'
      }).subscribe(response =>{
          console.log(response);
          alert('Zmiana hasla powiodła się!');
          this.user.password = this.form.newPassword;
          this.router.navigate(['mainstudent', this.user.username, this.user.password], {skipLocationChange: true});
        },
        error =>{
          alert('Zmiana hasła nie powiodła się');
          console.log(error);
        } );
    }

  }
}
