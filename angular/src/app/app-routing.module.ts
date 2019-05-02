import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisteredPageComponent} from './registered/registered-page/registered-page.component';

const routes: Routes = [
  {path: 'registered/:user', component: RegisteredPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
