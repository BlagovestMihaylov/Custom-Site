import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private Http: HttpClient) {}

  public getUsers(pageNum:number, pageSize: number ): Observable<User[]> {
    return this.Http.get<User[]>(`${this.apiServerUrl}/Users?page=${pageNum}&pageSize=${pageSize}`);
  }
  public addUsers(User: User): Observable<User> {
    return this.Http.post<User>(
      `${this.apiServerUrl}/particpant/add`,
      User
    );
  }
  public updateUsers(User: User): Observable<User> {
    return this.Http.put<User>(
      `${this.apiServerUrl}/particpant/update`,
      User
    );
  }
  public deleteUsers(UserId: number): Observable<void> {
    return this.Http.delete<void>(
      `${this.apiServerUrl}/particpant/delete/${UserId}`
    );
  }
}
