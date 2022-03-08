import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ApplicationServiceService} from "./services/application-service.service";
import {Application} from "./enities/Application";
import {Router} from "@angular/router";
import {AuthenticationService} from "./services/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ArmyPostponement';
  applications: Application[] = [];
  @Input() onGetRole: EventEmitter<string> = new EventEmitter;
  role

  constructor(public authendicationService: AuthenticationService,
              private route:Router) { }

  ngOnInit(): void{
    this.route.navigate(['']);
  }

  roleSelect(){
    this.onGetRole.emit(this.role)
  }

}
