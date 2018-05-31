import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {User} from '../user'

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
  
   private baseUrl:string='http://localhost:8080/api';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});
  private user:User;

  constructor(private _http:Http) { }
  
  
  createUser(user:User){
    return this._http.post(this.baseUrl+'/register',JSON.stringify(user),this.options)
      .map((response:Response)=>response.json()).catch(this.errorHandler);
    
  }
  
  errorHandler(error:Response){
    return Observable.throw(error||"SERVER ERROR");
  }
  
  setter(user:User){
    this.user=user;
  }
  
  getter(){
    return this.user;
  }

}
