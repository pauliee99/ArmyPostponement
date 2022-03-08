import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {Application} from '../../enities/Application';
//import {NOTES} from '../../hardcoded-notes';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import { MatDialog } from '@angular/material/dialog';
import { PopupFormUpdateComponent } from '../popup-form-update/popup-form-update.component';
import {DatePipe, formatDate} from "@angular/common";
import {Timestamp} from "rxjs";
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-application-item',
  templateUrl: './application-item.component.html',
  styleUrls: ['./application-item.component.css']
})
export class ApplicationItemComponent implements OnInit {
  @Input() application!: Application;
  @Output() onDeleteApplication: EventEmitter<Application> = new EventEmitter;
  @Output() onUpdateApplication: EventEmitter<Application> = new EventEmitter;
  @Output() onGetApplication: EventEmitter<Application> = new EventEmitter;
  faEye = faEye;

  constructor(private dialog : MatDialog, private app: AppComponent) { }

  ngOnInit(): void {
  }

  // onCancel(application: Application) {
  //   application.status=5;
  //   this.onUpdateApplication.emit(application);
  // }

  onUpdate(application: Application) {
    let role = this.app.role
    let dialogRef = this.dialog.open(PopupFormUpdateComponent, { data: { application, role } });
    dialogRef.afterClosed().subscribe((application) => {
      this.onUpdateApplication.emit(application.data);
      console.log("update application item: " + application.data.status)
    });
  }

  dateFormat(date: String){ //2022-02-11T22:00:00.000+00:00
     //let mydate = new Date('2022-02-11T22:00:00.000+00:00')
    return formatDate(date.valueOf(), 'dd-MM-yyyy hh:mm', 'en_US')
    // return mydate.toISOString().split('T').join(' ').split('Z').join(' ');
  }

  statusCode(status: number){
    if (this.application.status == 0)
      return "pending"
    else if (this.application.status == 1)
      return "validated"
    else if (this.application.status == 2)
      return "invalidated"
    else if (this.application.status == 3)
      return "approved"
    else if (this.application.status == 4)
      return "rejected"
    else if (this.application.status == 5)
      return "calceled"
    else
      return "some error"
  }

}
