import { Component, OnInit, Output, Input, EventEmitter, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { PopupFormComponent } from '../popup-form/popup-form.component';

@Component({
  selector: 'app-add-btn',
  templateUrl: './add-btn.component.html',
  styleUrls: ['./add-btn.component.css']
})
export class AddBtnComponent implements OnInit {
  @Output() onAddNote = new EventEmitter();
  @Input() formClick = new EventEmitter();


  //constructor() { }
  constructor(private dialog : MatDialog){}

  ngOnInit(): void {
  }

  // onClick(){
  //   this.btnClick.emit();
  //   document.getElementById("overlay")!.style.display = "block";
  // }

  onClick(){ // to open the dialog
    let dialogRef = this.dialog.open(PopupFormComponent);
    dialogRef.afterClosed().subscribe(note => {
      this.onAddNote.emit(note.data);
      console.log(note.data);
    });
  }

}
