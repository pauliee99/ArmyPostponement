import {Component, EventEmitter, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Input() onGetRole: EventEmitter<string> = new EventEmitter;

  constructor() { }

  ngOnInit(): void {
    console.log(this.onGetRole.subscribe())
  }

}
