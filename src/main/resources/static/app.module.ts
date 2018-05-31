import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';


import {AppComponent} from './app.component';
import {ListnoteComponent} from './components/listnote/listnote.component';
import {NoteformComponent} from './components/noteform/noteform.component';
import {NoteService} from './shared-service/note.service';
import {UserService} from './shared-service/user.service';

import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { LoginformComponent } from './components/loginform/loginform.component';


const appRoutes: Routes = [
  {path: '', component: ListnoteComponent},
  {path: 'op', component: NoteformComponent},
  {path: 'register', component:UserRegistrationComponent}

];


@NgModule({
  declarations: [
    AppComponent,
    ListnoteComponent,
    NoteformComponent,
    UserRegistrationComponent,
    LoginformComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [NoteService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {}
