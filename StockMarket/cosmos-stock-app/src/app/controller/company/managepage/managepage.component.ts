import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Companies } from 'src/app/model/companies';
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-managepage',
  templateUrl: './managepage.component.html',
  styleUrls: ['./managepage.component.css']
})
export class ManagepageComponent implements OnInit {

  dataSource : MatTableDataSource<Company> = new MatTableDataSource<Company>([]);
  displayedColumns: string[] = ['companyName','update'];

  constructor(private companyService:CompanyService,private router:Router) { }

  ngOnInit(): void {
    this.companyService.getAllCompanies()
     .subscribe((data: Companies)=>{
       this.dataSource = new MatTableDataSource(data.companyList);
     }) 
  }
  onUpdate(company:Company){
    console.log("Calling rest call to update..");
    this.router.navigate(['/updatecompany/',company.companyName]);
  }

}
