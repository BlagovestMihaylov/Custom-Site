import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from './post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private Http: HttpClient) {}

  public getPosts(pageNum: number, pageSize: number): Observable<Post[]> {
    return this.Http.get<Post[]>(
      `${this.apiServerUrl}/posts?page=${pageNum}&pageSize=${pageSize}`
    );
  }
  public addPosts(Post: Post): Observable<Post> {
    return this.Http.post<Post>(`${this.apiServerUrl}/post/add`, Post);
  }
  public updatePosts(Post: Post): Observable<Post> {
    return this.Http.put<Post>(`${this.apiServerUrl}/post/update`, Post);
  }
  public deletePosts(PostId: number): Observable<void> {
    return this.Http.delete<void>(`${this.apiServerUrl}/post/delete/${PostId}`);
  }
}
