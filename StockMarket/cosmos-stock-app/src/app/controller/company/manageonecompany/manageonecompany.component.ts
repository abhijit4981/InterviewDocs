import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Companyview } from 'src/app/model/companyview';
import { CompanyviewService } from 'src/app/service/companyview.service';
import { StaticService } from 'src/app/service/static.service';

@Component({
  selector: 'app-manageonecompany',
  templateUrl: './manageonecompany.component.html',
  styleUrls: ['./manageonecompany.component.css']
})
export class ManageonecompanyComponent implements OnInit {

  companyViewForm: FormGroup;
  trends :String[];
  watchLists : String[];
  companyView:Companyview;
  constructor(private formBuilder: FormBuilder,private route:ActivatedRoute,private companyviewService:CompanyviewService,private staticService:StaticService) { }

  ngOnInit(): void {
    this.trends =this.staticService.getTrendPatterns();
    this.watchLists = this.staticService.getWatchListIn();
    let companyName = this.route.snapshot.params['id'];
    console.log("request rest call for "+companyName);
    this.companyviewService.getCompanyViewById(companyName)
    .subscribe((data:Companyview )=>{
      console.log("data received : ",data);

      this.companyViewForm = this.formBuilder.group({
        companyName: new FormControl(data.companyName),
        companyDaysAsNumbers: new FormControl(data.companyDaysAsNumbers),
        companyTrendPattern: new FormControl(data.companyTrendPattern),
        companyWatchList: new FormControl(data.companyWatchList),
        companyPosition: new FormControl(data.companyPosition),
      });
    })
  }
  onSubmit(companyViewForm:any){
    console.log(companyViewForm.value);
    this.companyView =companyViewForm.value;
    this.companyviewService.updateCompanyView(this.companyView)
    .subscribe((data: String)=>{
      console.log(data);
    }) 
  }

}
