//
// THIS COMPONENT IS CURRENTLY NOT USED
//
import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import { concat } from 'rxjs';
import { Application } from '../../enities/Application'

@Component({
  selector: 'app-add-note-form',
  templateUrl: './add-application-form.component.html',
  styleUrls: ['./add-application-form.component.css']
})
export class AddApplicationFormComponent implements OnInit {
  @Output() onAddNote = new EventEmitter();
  nametext!: String; //to bind to te ngModel in html
  commenttext!: String;
  reasonsopt!: String;

  constructor() { }
  //constructor(private dialogRef: MatDialog) { }

  ngOnInit(): void {
    //document.getElementById("overlay")!.style.display = "block";
  }

  onSubmit() {
    if (!this.nametext) {
      alert('Please add a note!');
      return;
    }

    var timestamp = 1301090400,
      date = new Date(),
      datevalues =
        date.getFullYear() + "/"+
        [date.getMonth()+1]+ "/"+
        date.getDay();
    // date.getHours()+ ":"+
    // date.getMinutes()+":"+
    // date.getSeconds();

    var timestamp2 = 1301090400,
      time = new Date(),
      timevalues =
        // date.getFullYear() + "/"+
        // [date.getMonth()+1]+ "/"+
        // date.getDay()+ "-"+
        date.getHours()+ ":"+
        date.getMinutes()+":"+
        date.getSeconds();

    console.log(datevalues, timevalues)

    const newApplication = {
      name: this.nametext,
      //reason: this.reasonsopt,
      date: datevalues,
      msg: this.commenttext,
    };

    this.onAddNote.emit(newApplication);

    this.nametext = '';
    this.commenttext = '';
  }


}
