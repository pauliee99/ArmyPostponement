import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import { Application } from 'src/app/enities/Application';
import {FileUploadService} from "../../services/file-upload.service";

@Component({
  selector: 'app-popup-form',
  templateUrl: './popup-form.component.html',
  styleUrls: ['./popup-form.component.css']
})
export class PopupFormComponent implements OnInit {
  @Output() onAddNote = new EventEmitter();
  //nametext!: String; //to bind to te ngModel in html
  commenttext!: String;
  reasonsopt!: String;
  date!: String;
  id!: Number;
  file: File;

  constructor(private dialogRef: MatDialogRef<Application>) { }

  ngOnInit(): void {
  }


  onSubmit() {
    // var timestamp = 1301090400,
    //   date = new Date(),
    //   datevalues =
    //     date.getFullYear() + "/"+
    //     [date.getMonth()+1]+ "/"+
    //     date.getDay();
    // // date.getHours()+ ":"+
    // // date.getMinutes()+":"+
    // // date.getSeconds();
    //
    // var timestamp2 = 1301090400,
    //   time = new Date(),
    //   timevalues =
    //     // date.getFullYear() + "/"+
    //     // [date.getMonth()+1]+ "/"+
    //     // date.getDay()+ "-"+
    //     date.getHours()+ ":"+
    //     date.getMinutes()+":"+
    //     date.getSeconds();

    // console.log(datevalues, timevalues)

    const newNote = {
      //name: this.nametext,
      //id: null,
      //datexx: "2022-01-13T00:00:00.000+00:00",
      //time: "2022-01-13T22:22:22.000+00:00",
      //office: 2,
      //officeDescr: "Office3",
      //reason: 1,
      reasonDescr: "Studies",//this.reasonsopt,
      file: this.file,
      // status: 0,
      // comments: this.notetext,
      //user_in: "null",
      commentIn: this.commenttext,
      // userValid: null,
      // commentValid: null,
      // userApproved: null,
      // commentApproved: null
    };

    //this.onAddNote.emit(newNote);

    //this.nametext = '';
    this.commenttext = '';

    console.log(newNote);
    console.log(this.file)

    //return newNote;
    this.dialogRef.close({data:newNote});
  }

  // fileChange(event) {
  //    let fileList: FileList = event.target.files;
  //   if(fileList.length > 0) {
  //     let file: File = fileList[0];
  //     let formData:FormData = new FormData();
  //     formData.append('uploadFile', file, file.name);
  //     let headers = new Headers();
  //     /** In Angular 5, including the header Content-Type can invalidate your request */
  //     headers.append('Content-Type', 'multipart/form-data');
  //     headers.append('Accept', 'application/json');
  //     let options = new RequestOptions({ headers: headers });
  //     this.http.post(`${this.apiEndPoint}`, formData, options)
  //       .map(res => res.json())
  //       .catch(error => Observable.throw(error))
  //       .subscribe(
  //         data => console.log('success'),
  //         error => console.log(error)
  //       )
  //   }
  // }

}
