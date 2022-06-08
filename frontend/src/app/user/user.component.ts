import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

public Users: User[] | undefined;

  constructor(private UserService: UserService) {}
  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void {
    this.UserService.getUsers(1,10).subscribe(
      (response: User[]) => {
        this.Users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
 }
}
