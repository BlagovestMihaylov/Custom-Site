import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { UserComponent } from './user/user.component';
import { PostComponent } from './post/post.component';
import { PostService } from './post.service';
import { ProfileComponent } from './profile/profile.component';
import { ProfileService } from './profile.service';



@NgModule({
  declarations: [AppComponent, UserComponent, PostComponent, ProfileComponent],
  imports: [HttpClientModule, BrowserModule, AppRoutingModule],
  providers: [UserService, PostService, ProfileService],
  bootstrap: [AppComponent],
})
export class AppModule {}
