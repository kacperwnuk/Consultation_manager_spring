import { Component, OnInit } from '@angular/core';
import {Principal} from '../../rejestration/resource/principal';
import {Consultation} from '../../student/resource/consultation';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {User} from 'src/app/rejestration/resource/user';

@Component({
  selector: 'app-verify-users',
  templateUrl: './verify-users.component.html',
  styleUrls: ['./verify-users.component.css']
})
export class VerifyUsersComponent implements OnInit {

  user: Principal;
  public collection: User[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];


    let params = new HttpParams();
    params = params.set('tutorUsername', this.user.username);
    const url = 'https://localhost:8443/user/inactiveStudents?' + params.toString();

    this.http.get(url, {
      headers: new HttpHeaders().set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))}
    ).subscribe((res: any) => {
      console.log(res);
      this.collection = res;
      console.log(this.collection);
    });
  }

  verify(u) {
    let params = new HttpParams();
    params = params.set('tutorUsername', this.user.username);
    params = params.set('studentUsername', u.username);
    const url = 'https://localhost:8443/user/activateStudent?' + params.toString();

    this.http.get(url, {
      headers: new HttpHeaders().set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
      }
    ).subscribe((res: any) => {
        alert('Poprawnie zweryfikowano użytkownika!');
    });
}

}
