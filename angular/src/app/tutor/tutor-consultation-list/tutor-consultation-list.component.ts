import { Component, OnInit } from '@angular/core';
import { Principal } from 'src/app/rejestration/resource/principal';
import { Consultation } from 'src/app/student/resource/consultation';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-tutor-consultation-list',
  templateUrl: './tutor-consultation-list.component.html',
  styleUrls: ['./tutor-consultation-list.component.css']
})
export class TutorConsultationListComponent implements OnInit {
  user: Principal;
  public collection: Consultation[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
    this.reload();
  }

  reload(){
    this.user = new Principal();
    this.user.username = this.route.snapshot.params['username'];
    this.user.password = this.route.snapshot.params['password'];

    const url = 'https://localhost:8443/consultations';
    this.http.get(url, {
      headers: new HttpHeaders().set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`))}
    ).subscribe((res: Consultation[]) => {
      this.collection = res;
      console.log(this.collection);
      console.log(typeof this.collection[0].date);
    });
  }

  cancel(i) {
    console.log("Zapisujesz sie na konsultacje nr " + i);
    let params = new HttpParams();
    params = params.set('consultationId', this.collection[i].id);
    params = params.set('username', this.user.username);
    const url = 'https://localhost:8443/cancelConsultation?' + params.toString();
    console.log(url);

    this.http.get(url, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(`${this.user.username}:${this.user.password}`)),
      observe: 'response',
      responseType: 'text'
    }).subscribe(response =>{
        console.log(response);
        this.reload();
        alert('Anulowanie powiodło się!');
      },
      error =>{
        alert('Anulowanie nie powiodło się');
        console.log(error);
      } );
  }

}
