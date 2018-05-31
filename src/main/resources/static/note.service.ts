import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Note} from '../note'

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';


@Injectable()
export class NoteService {
  
  private baseUrl:string='http://localhost:8080/api';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});
  private note:Note;

  constructor(private _http:Http) { }
  
  getNotes(){
    
    return this._http.get(this.baseUrl+'/notes',this.options)
      .map((response:Response)=> response.json()).catch(this.errorHandler);
    
  }
  
  
  getNote(id:Number){
    
    return this._http.get(this.baseUrl+'/notes/' +id, this.options).
      map((response:Response)=>response.json).catch(this.errorHandler);
    
  }
  
  createNote(note:Note){
    return this._http.post(this.baseUrl+'/notes',JSON.stringify(note),this.options)
      .map((response:Response)=>response.json()).catch(this.errorHandler);
    
  }
  
  updateNote(note:Note){
    return this._http.put(this.baseUrl+'/notes/'+note.id,JSON.stringify(note),this.options)
      .map((response:Response)=>response.json()).catch(this.errorHandler);
    
  }
  
  
  
  deleteNote(id:Number){
    return this._http.delete(this.baseUrl+'/notes/'+id, this.options).
      map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  errorHandler(error:Response){
    return Observable.throw(error||"SERVER ERROR");
  }
  
  setter(note:Note){
    this.note=note;
  }
  
  getter(){
    return this.note;
  }

}
