import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Post } from './post';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private Http: HttpClient) {}

  public getUserPosts(user_id: number): Observable<Post[]> {
    return this.Http.get<Post[]>(`${this.apiServerUrl}/users/${user_id}/posts`);
  }

  public getUser(user_id: number): Observable<User> {
    return this.Http.get<User>(`${this.apiServerUrl}/users/${user_id}`);
  }

  public getUserFollowers(user_id: number): Observable<User[]> {
    return this.Http.get<User[]>(
      `${this.apiServerUrl}/users/${user_id}/followers`
    );
  }
  public getUserFollowings(user_id: number): Observable<User[]> {
    return this.Http.get<User[]>(
      `${this.apiServerUrl}/users/${user_id}/followings`
    );
  }
}
