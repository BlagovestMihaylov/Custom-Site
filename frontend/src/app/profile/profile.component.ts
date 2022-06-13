import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Post } from '../post';
import { ProfileService } from '../profile.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profilePost.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  public user: User | undefined ;
  public posts: Post[] | undefined;
  public followers: User[] | undefined;
  public followings: User[] | undefined;

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.getUser(2);
    this.getUserPosts(2);
    this.getUserFollowers(2);
    this.getUserFollowings(2);
  }

  public getUser(id: number): void {
    this.profileService.getUser(id).subscribe(
      (responnse: User) => {
        this.user = responnse;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserPosts(id: number): void{
    this.profileService.getUserPosts(id).subscribe(
      (responnse: Post[]) => {
        this.posts = responnse;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserFollowers(id:number) : void {
    this.profileService.getUserFollowers(id).subscribe(
      (responnse: User[]) => {
        this.followers = responnse;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserFollowings(id: number) : void {
    this.profileService.getUserFollowings(id).subscribe(
      (responnse: User[]) => {
        this.followings = responnse;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
