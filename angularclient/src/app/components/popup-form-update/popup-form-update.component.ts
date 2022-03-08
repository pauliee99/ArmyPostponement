import { Component, EventEmitter, Inject, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Application } from 'src/app/enities/Application';
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-popup-form-update',
  templateUrl: './popup-form-update.component.html',
  styleUrls: ['./popup-form-update.component.css']
})
export class PopupFormUpdateComponent implements OnInit {
  @Input() onUpdateNote = new EventEmitter();
  id: number = this.data.application.id
  date: string = this.dateFormat(this.data.application.date)
  reason: String = this.data.application.reasonDescr;
  office: String = this.data.application.office.description;
  file: String = this.data.application.file;
  userIn: String = this.data.application.userIn.username;
  commentIn: String = this.data.application.commentIn;
  userValid: String = this.data.application.userValid;
  commentValid: String = this.data.application.commentValid;
  userApproved: String = this.data.application.userApproved;
  commentApproved: String = this.data.application.commentApproved;
  status: String = this.statusCode()//this.data.application.status;
  commenttext: String// = this.data.application.commentIn; //to bind to te ngModel in html
  role = this.data.role

  application!: Application;
  // id!: number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
   private dialogRef: MatDialogRef<Application>,
   ) { }

  ngOnInit(): void {
  }

  // onSubmit() {
  //   const updateApplication = {
  //     id: this.data.application.id,
  //     commentIn: this.commenttext,
  //     status: 5
  //   };
  //   this.dialogRef.close({data:updateApplication});
  // }
  onCancel() {
    const updateApplication = {
      id: this.data.application.id,
      commentIn: this.commenttext,
      status: 5
    };
    this.dialogRef.close({data:updateApplication});
  }
  onValid(){
    const updateApplication = {
      id: this.data.application.id,
      commentValid: this.commenttext,
      status: 1
    };
    this.dialogRef.close({data:updateApplication});
  }
  onNotValid(){
    const updateApplication = {
      id: this.data.application.id,
      commentValid: this.commenttext,
      status: 2
    };
    this.dialogRef.close({data:updateApplication});
  }
  onApprove(){
    const updateApplication = {
      id: this.data.application.id,
      commentApproved: this.commenttext,
      status: 3
    };
    this.dialogRef.close({data:updateApplication});
  }
  onReject(){
    const updateApplication = {
      id: this.data.application.id,
      commentApproved: this.commenttext,
      status: 4
    };
    this.dialogRef.close({data:updateApplication});
  }

  statusCode(){
    if (this.data.application.status == 0)
      return "pending"
    else if (this.data.application.status == 1)
      return "validated"
    else if (this.data.application.status == 2)
      return "invalidated"
    else if (this.data.application.status == 3)
      return "approved"
    else if (this.data.application.status == 4)
      return "rejected"
    else if (this.data.application.status == 5)
      return "calceled"
    else
      return "some error"
  }

  // roleCheck(){
  //   if (this.app.role == "ROLE_POLITIS"){
  //     return true
  //   }
  //   else return false
  // }

  dateFormat(date: String){
    return formatDate(date.valueOf(), 'dd-MM-yyyy hh:mm', 'en_US')
  }

}
