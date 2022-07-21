import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { baseUrl } from 'src/environments/environment';
import { Companyview } from '../model/companyview';
import { Companyviews } from '../model/companyviews';

@Injectable({
  providedIn: 'root'
})
export class CompanyviewService {

  constructor(private http: HttpClient) { }
  getCompanyViewUrl = baseUrl+"/companyview/";

  getAllCompanies() {
    return this.http.get<Companyviews>(this.getCompanyViewUrl).pipe(catchError(this.handleError));
  }
  getCompanyViewById(companyName:String){
    return this.http.get<Companyview>(this.getCompanyViewUrl+companyName).pipe(catchError(this.handleError));
  }
  updateCompanyView(companyView:Companyview){
    return this.http.put<String>(this.getCompanyViewUrl+companyView.companyName,companyView).pipe(catchError(this.handleError));
  }

  private handleError(httpError: HttpErrorResponse) {
    if (httpError.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', httpError.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${httpError.status}, ` +
        `body was: ${httpError.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError('Something bad happened; please try again later.');
  }
}
