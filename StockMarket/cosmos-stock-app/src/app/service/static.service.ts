import { Injectable } from '@angular/core';
import { Companies } from '../model/companies';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class StaticService {
  
  constructor() { }

  getUserDetails(){
    return "Abhijit";
  }
}
