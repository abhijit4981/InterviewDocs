import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css']
})
export class AddNoteComponent implements OnInit {

  showTakeAnotePage:boolean=false;
  constructor() { }

  ngOnInit(): void {
  }
  onTotakingNote(){
    this.showTakeAnotePage=true;
  }

}
