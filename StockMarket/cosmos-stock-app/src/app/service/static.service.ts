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
  getTrendPatterns(){
    let patterns : String[] =["UpSide",'DownSide','SideWays'];
    return patterns;
  }
  getWatchListIn(){
    let watchList:String[] =['IT','Banking','LTThan1000','Popular','MayBe','Radar', 'Recommendation','MustBuy','BigHouse'];
    return watchList;
  }
}
