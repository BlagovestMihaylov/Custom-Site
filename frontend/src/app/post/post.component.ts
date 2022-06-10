import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Post } from '../post';
import { PostService } from '../post.service';


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  public Posts: Post[] | undefined;

  constructor(private PostService: PostService) {}
  ngOnInit(): void {
    this.getPosts();
  }

  public getPosts(): void {
    this.PostService.getPosts(0,10).subscribe(
      (response: Post[]) => {
        this.Posts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
 }

}
