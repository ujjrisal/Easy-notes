import { Component, OnInit } from '@angular/core';
import {Note} from '../../note'
import {Router} from '@angular/router';
import {NoteService} from '../../shared-service/note.service'

@Component({
  selector: 'app-noteform',
  templateUrl: './noteform.component.html',
  styleUrls: ['./noteform.component.css']
})
export class NoteformComponent implements OnInit {

  constructor(private _noteService:NoteService, private _router:Router) { }
  private note:Note;

  ngOnInit() {
    this.note = this._noteService.getter();
  }
  
  processForm(){
    if(this.note.id==undefined){
      this._noteService.createNote(this.note).subscribe((note)=>{
        
        console.log(note);
        this._router.navigate(['/']);
        },(error)=>{
          console.log(error);
        });
        }else{
      
      this._noteService.updateNote(this.note).subscribe((note)=>{
        
        console.log(note);
        this._router.navigate(['/']);
        },(error)=>{
          console.log(error);
        });
    }
      
    }
      
    

}
