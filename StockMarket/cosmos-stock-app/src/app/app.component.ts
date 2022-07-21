import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cosmos-stock-app';
  ifLoggedIn:boolean=true;
  longText:string="N/A";
  constructor(private router:Router){}
  callSSO(){
    //turn ifLoggedIn on once u get the auth cleared..
    this.ifLoggedIn = true;
    this.router.navigate(['dashboard']);
  }
}
