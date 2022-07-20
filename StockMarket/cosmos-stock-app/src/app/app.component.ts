import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cosmos-stock-app';
  ifLoggedIn:boolean=false;
  longText:string="N/A";
  constructor(private router:Router){}
  callSSO(){
    this.ifLoggedIn = true;
    this.router.navigate(['dashboard']);
  }
}
