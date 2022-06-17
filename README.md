# Custom-Site


## Endpoints
### Login
```
/login 
```
login authentication with jtw token

### Registration
```
/registration
```
new user creation

### User @ /api/users
```
/all
```
all users
```
/{id}
```
getting user by id
```
/delete/{id}
```
delete user by id
```
/{id}/posts
```
see user posts
```
/follow/{myId}&{otherId}
```
follow somebody
```
/unfollow/{myId}&{otherId}
```
unfollowsomebody
```
/{id}/followers
```
see user followers by id
```
/{id}/followings
```
see user following by id

### Posts @ /api/posts
```
/create
```
create a post
```
/{id}
```
find post by id
```
/{id}
```
delete post by id
```
/{id}/username
```
see username of the user that created the post
```
/{id}/image
```
see profile pic of the user that created the post
```
/{id}/tags
```
see post tags
```
/{id}/categories
```
see post categories
```
/add-cat/{post_id}/{cat_id}
```
add category
```
/add-tag/{post_id}/{tag_id}
```
add tag

### Comments @ /api/comments
```
/create
```
create a comment
```
/post/{postId}/comments
```
see post commnets
```
/comment/{commentId}/comments
```
see comment comments
### Like @/api/like
```
/comment
```
like a comment
```
/post
```
like a post

### Report @ /api/report
```
/comment
```
report a comment
```
/user
```
report a user
```
/post
```
report a post
```
post/{id}
```
get post reports
```
comment/{id}
```
get comment reports




