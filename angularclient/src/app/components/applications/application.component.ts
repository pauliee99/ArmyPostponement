import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import { ApplicationServiceService } from 'src/app/services/application-service.service';
import {Application} from '../../enities/Application';
import {Router} from "@angular/router";
import {HttpHeaders, HttpResponse} from "@angular/common/http";
import {User} from "../../enities/User";
import {AuthenticationService} from "../../services/authentication.service";
import {AppComponent} from "../../app.component";
//import {NOTES} from '../../hardcoded-notes';

@Component({
  selector: 'app-applications',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit, OnChanges {
  respHeader: HttpHeaders//[] = []; //initialize
  applications: Application[]
  users: User
  @Input() onUpdateApplication: EventEmitter<Application> = new EventEmitter;
  @Input() onGetAuthority: EventEmitter<Application> = new EventEmitter;
  @Output() onLoggedIn: EventEmitter<Boolean> = new EventEmitter
  private router: Router
  public application
  public errorMsg

  constructor(private applicationService: ApplicationServiceService, 
    private app: AppComponent,
    private authendicationService: AuthenticationService) { }

  ngOnInit(): void {
    // this.getCurrentUserDetails()
    this.authCheck()
    // this.onLoggedIn.emit(false)
    // this.getPostponements()
  }

  ngOnChanges(changes: SimpleChanges) {
    this.authCheck()
  }

  // deleteNote(application: Application) {
  //   this.applicationService.deleteNote(application).subscribe(() => (this.applications = this.applications.filter((n) => n.id !== application.id)));
  // }

  getPostponements(){
    // get all applications
    // this.applicationService.getPostponementsYpallilos().subscribe(
    this.applicationService.getPostponements().subscribe(
      (applications) => (
        this.respHeader = applications.headers,
          this.applications=applications.body,
          this.users = applications.body[0].userIn,
          console.log(applications.body[0].userIn.username),
          console.log(this.users))
    ); //to use it as an observable
    this.sortData;
  }

  authCheck() {
    if (this.app.role == "ROLE_POLITIS"){
      console.log("here is politis")
      this.getPostponementsPolitis()
    } else if (this.app.role == "ROLE_OFFICER") {
      console.log("here is officer")
      this.getPostponementsOfficer()
    } else if (this.app.role == "ROLE_YPALLILOS") {
      console.log("here is ypallilos")
      this.getPostponementsYpallilos()
    } else {
      console.log("Some Ting Wrong... Maybe Check the authorities? :)")
    }
  }

  // addPostponement(application: Application) {
  //   // @ts-ignore
  //   this.applicationService.addPostponement(application).subscribe((application) => (this.applications.push(application)));
  // }
  addPostponement(application: Application) {
    this.applicationService.addPostponement(application).subscribe(
      (application) => {
        // @ts-ignore
        this.applications.push(application)
        this.ngOnInit();
      }),
      err => {
        console.log("Error")
      };
  }

  updateApplication(application:Application){
    //this.noteService.updateNote(note).subscribe((note) => (this.notes.push(note)));
    // this.applicationService.updateApplication(application).subscribe((application) =>
    //   (this.applications = this.applications.filter((n) => (n.id !== application.id, this.ngOnInit()))));
      // if this doesnt work tomorrow remove on init from them method above along with ()
    if (this.app.role == "ROLE_POLITIS"){
      this.applicationService.updateApplicationPolitis(application).subscribe((application) =>
        (this.applications = this.applications.filter((n) => (n.id !== application.id, this.ngOnInit()))));
    } else if (this.app.role == "ROLE_OFFICER") {
      this.applicationService.updateApplicationOfficer(application).subscribe((application) =>
        (this.applications = this.applications.filter((n) => (n.id !== application.id, this.ngOnInit()))));
    } else if (this.app.role == "ROLE_YPALLILOS") {
      this.applicationService.updateApplicationYpallilos(application).subscribe((application) =>
        (this.applications = this.applications.filter((n) => (n.id !== application.id, this.ngOnInit()))));
    } else {
      console.log("Some Ting Wrong... Maybe Check the authorities? :)")
    }
    this.ngOnInit();
  }

  get sortData() {
    return this.applicationService.getPostponements().subscribe(() => (this.applications.sort((a, b) => {
      return <any>new Date(b.date) - <any>new Date(a.date);
    })
    ));
  }

  getPostponementsPolitis(){
    // get all applications
    // this.applicationService.getPostponementsYpallilos().subscribe(
    this.applicationService.getPostponementsPilitis().subscribe(
      (applications) => (
        this.respHeader = applications.headers,
          this.applications=applications.body,
          this.users = applications.body[0].userIn,
          console.log(applications.body[0].userIn.username),
          console.log(this.users))
    ); //to use it as an observable
    this.sortData;
  }
  getPostponementsYpallilos(){
    // get all applications
    // this.applicationService.getPostponementsYpallilos().subscribe(
    this.applicationService.getPostponementsYpallilos().subscribe(
      (applications) => (
        this.respHeader = applications.headers,
          this.applications=applications.body,
          this.users = applications.body[0].userIn,
          console.log(applications.body[0].userIn.username),
          console.log(this.users))
    ); //to use it as an observable
    this.sortData;
  }
  getPostponementsOfficer(){
    // get all applications
    // this.applicationService.getPostponementsYpallilos().subscribe(
    this.applicationService.getPostponementsOfficer().subscribe(
      (applications) => (
        this.respHeader = applications.headers,
          this.applications=applications.body,
          this.users = applications.body[0].userIn,
          console.log(applications.body[0].userIn.username),
          console.log(this.users))
    ); //to use it as an observable
    this.sortData;
  }

  roleCheck(){
    if (this.app.role == "ROLE_POLITIS"){
      return true
    }
    else return false
  }

}


