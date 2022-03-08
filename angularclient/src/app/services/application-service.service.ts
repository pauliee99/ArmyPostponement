import {EventEmitter, Injectable, Input, Output} from '@angular/core';
import {Application} from '../enities/Application';
// import {NOTES} from '../hardcoded-notes';
import {catchError, map, Observable, throwError} from 'rxjs';
import {
  HttpClient,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest, HttpResponse
} from '@angular/common/http';
import { Activity } from '../Activity';
import {User} from "../enities/User";
import {AuthenticationService} from "./authentication.service";
import * as events from "events";
import {Authorities} from "../enities/Authorities";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'User': 'username',
  }),
  // observe: 'response',
};

@Injectable({
  providedIn: 'root'
})
export class ApplicationServiceService {
  // private apiUrl = 'http://localhost:5000/'
  private apiUrl2 = 'http://192.168.1.3:8080'
  // private apiUrl2 = 'http://localhost:8080'
  @Output() onGetAuthority: EventEmitter<Application> = new EventEmitter;
  @Input() onGetRole: EventEmitter<string> = new EventEmitter;
  applications = [];
  public userAuth //= []; // users can have multiple auths
  auth: Authorities

  constructor(private http:HttpClient, private authenticationService: AuthenticationService) { }

  getPostponements(): Observable<HttpResponse<Application[]>> {
    const url = `${this.apiUrl2}/politis/postponements`; //http://localhost:8080/politis/postponements
    return this.http.get<Application[]>(url,{ observe: 'response' })
  }

  getPostponementsPilitis(): Observable<HttpResponse<Application[]>> {
    const url = `${this.apiUrl2}/politis/postponements`;
    console.log("politis<--")
    return this.http.get<Application[]>(url,{ observe: 'response' })
  }
  getPostponementsOfficer(): Observable<HttpResponse<Application[]>> {
    const url = `${this.apiUrl2}/officer/postponements`;
    console.log("officer<--")
    return this.http.get<Application[]>(url,{ observe: 'response' })
  }
  getPostponementsYpallilos(): Observable<HttpResponse<Application[]>> {
    const url = `${this.apiUrl2}/ypallilos/postponements`;
    console.log("ypallilos<--")
    return this.http.get<Application[]>(url,{ observe: 'response' })
  }

  getCurrentUserDetails(): Observable<HttpResponse<User[]>> {
    const url = `${this.apiUrl2}/users/${this.authenticationService.username}`;
    return this.http.get<User[]>(url,{ observe: 'response' })
  }

  addPostponement(application: Application) {
    const url = `${this.apiUrl2}/politis/postponements`;
    return this.http.post<Application>(url, application, {observe: 'response'})//httpOptions)
  }
  // addPostponement(application: Application) {
  //   const url = `${this.apiUrl2}/postponements`;
  //   // @ts-ignore
  //   return this.http.post<Application>(url, application, httpOptions)
  //     .subscribe(response => {
  //       // You can access status:
  //       console.log(response);
  //     });
  // }

  // deleteNote(application: Application): Observable<Application> {
  //   const url = `${this.apiUrl}/politis/postponements/${application.id}`;
  //   return this.http.delete<Application>(url);
  // }

  // updateApplication(application: Application): Observable<Application> {
  //   const url = `${this.apiUrl2}/politis/postponements/${application.id}`;
  //   return this.http.put<Application>(url, application, httpOptions);
  //   console.log('service')
  // }
  updateApplicationPolitis(application: Application): Observable<Application> {
    const url = `${this.apiUrl2}/politis/postponements/${application.id}`;
    return this.http.put<Application>(url, application, httpOptions);
  }
  updateApplicationYpallilos(application: Application): Observable<Application> {
    let  url
    if (application.status == 1)
      url = `${this.apiUrl2}/ypallilos/postponements/valid/${application.id}`;
    else if (application.status == 2)
      url = `${this.apiUrl2}/ypallilos/postponements/notvalid/${application.id}`;
    console.log("service: " + application.status)
    return this.http.put<Application>(url, application, httpOptions);
  }
  updateApplicationOfficer(application: Application): Observable<Application> {
    let  url
    if (application.status == 3)
      url = `${this.apiUrl2}/officer/postponements/approve/${application.id}`;
    else if (application.status == 4)
      url = `${this.apiUrl2}/officer/postponements/reject/${application.id}`;
    return this.http.put<Application>(url, application, httpOptions);
  }

  //bore api
  // getActivity(): Observable<Activity> {
  //   const url = 'http://www.boredapi.com/api/activity/'
  //   return this.http.get<Activity>(url);
  // }
}
