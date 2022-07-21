import { IndustryInfo } from "./industry-info";
import { IntraDayHighLow } from "./intra-day-high-low";

export interface Company {
      companyName: string;
      isin: string;
      status: string;
      listingDate:Date;
      industryInfo:IndustryInfo;
      lastUpdateTime:Date;
      minOfWeek:number;
      minWeekDate:Date;
      maxOfWeek:number;
      maxWeekDate: Date;
      intraDayHighLow:IntraDayHighLow;
}
