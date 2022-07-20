import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StaticService {

  constructor() { }

  getUserDetails(){
    return "Abhijit";
  }
}
