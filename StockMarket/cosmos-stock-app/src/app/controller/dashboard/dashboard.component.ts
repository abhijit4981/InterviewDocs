import { Component, OnInit } from '@angular/core';
import { StaticService } from 'src/app/service/static.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  name:String;

  constructor(private staticdata:StaticService) { }

  ngOnInit(): void {
    this.name=this.staticdata.getUserDetails();
  }

}
