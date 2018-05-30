import {Component, OnInit} from '@angular/core';
import {User} from '../../user'
import {Router} from '@angular/router';
import {UserService} from '../../shared-service/user.service'

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  constructor(private _userService:UserService, private _router:Router) {}

  private user: User;

  ngOnInit() {
    this.user = this._userService.getter();


  }

  processForm() {

    if (this.user.id == undefined) {
      this._userService.createUser(this.user).subscribe((user) => {

        console.log(user);
        this._router.navigate(['/']);
      }, (error) => {
        console.log(error);
      });
    }

  }

}
