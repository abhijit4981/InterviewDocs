import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Companies } from 'src/app/model/companies';
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/service/company.service';
import { StaticService } from 'src/app/service/static.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  companyForm:FormGroup;
  adminForm:FormGroup;
  companies:Companies;
  viewCompanyTab:boolean=false;
  viewAllCompanyTab:boolean=false;
  viewMoneyPage:boolean=false;
  viewPositionTab:boolean=false;
  company:Company;

  dataSource : MatTableDataSource<Company> = new MatTableDataSource<Company>([]);
  displayedColumns: string[] = ['companyName', 'industry','listingDate','lastUpdateTime','value','update','clone','delete'];
  constructor(private formBuilder: FormBuilder,private staticdata:StaticService,private companyService:CompanyService) { }

  ngOnInit(): void {
    this.companyForm= this.formBuilder.group({
      companyName:['']
    });
    this.adminForm= this.formBuilder.group({
      amount:['']
    });
  }

  onGoToAddPageCompany(){
    this.viewCompanyTab=true;
  }
  onGoToAddMoneyPage(){
    this.viewMoneyPage=true;
  }
  onSaveCompany(companyData:FormGroup){
    console.log("received details for form : "+companyData.value);
    console.log('Valid?', companyData.valid); // true or false
    console.log('Value', companyData.value);
    this.updatePositionTab();
    let name = companyData.value.companyName
    this.companyService.saveCompany(name)
    .subscribe((data: Company)=>{
         console.log(data);
         this.company = data;
       });
  }
  onAddMoney(adminData:FormGroup){
    console.log("received details for form : "+adminData.value);
    console.log('Value', adminData.value); 
    this.updatePositionTab();
  }
  updatePositionTab(){
    this.viewMoneyPage=false;
    this.viewCompanyTab=false; 
    this.viewPositionTab=true;
  }
  viewAllCompanyPage(){
    //this.dataSource = this.staticdata.getAllCompany().companyList;
    // .subscribe((data: any)=>{
    //   console.log(data);
    //   this.dataSource = new MatTableDataSource(data.nseListedCompanyModelList);
    // }) 
    this.viewAllCompanyTab=true;
  }
  onDelete(user:any){
    console.log("Calling rest call to update.."+user);
  }
  onUpdate(user:any){
    console.log("Calling rest call to update.."+user);
  }

}