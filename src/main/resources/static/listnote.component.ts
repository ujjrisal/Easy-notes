import {Component, OnInit} from '@angular/core';
import {NoteService} from '../../shared-service/note.service';
import {Note} from '../../note';
import {Router} from '@angular/router';

@Component({
  selector: 'app-listnote',
  templateUrl: './listnote.component.html',
  styleUrls: ['./listnote.component.css']
})
export class ListnoteComponent implements OnInit {

  private notes: Note[];
  constructor(private _noteService: NoteService, private _router:Router) {}

  ngOnInit() {

    this._noteService.getNotes().subscribe((notes) => {
      console.log(notes);
      this.notes = notes;
    }, (error) => {
      console.log(error);
    })
  }

  deleteNote(note) {

    this._noteService.deleteNote(note.id).subscribe((data) => {
      this.notes.splice(this.notes.indexOf(note), 1);


    }, (error) => {
      console.log(error);
    });

  }
  
  updateNote(note){
    
    this._noteService.setter(note);
    this._router.navigate(['/op']);
    
  }
  
  newNote(){
    
    let note = new Note();
    this._noteService.setter(note);
    this._router.navigate(['/op']);
  }

}
