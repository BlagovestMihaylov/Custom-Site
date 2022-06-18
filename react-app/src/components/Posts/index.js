import React, { useEffect } from "react";
import { Container, Button } from "semantic-ui-react";
import { postService } from "../../services/post-service";

const Posts = () => {
  const [posts, setPosts] = React.useState();
  const [sort, setSort] = React.useState();

  useEffect(() => {
    const fetchPosts = async () => {
      const posts = await postService.getPosts();
      console.log("getting posts");
      setPosts(posts);
    };
    fetchPosts();
  }, [sort]);

  const handleSortByDate = () => {
    setSort("date");
  };

  return (
    <Container>
      <Button onClick={handleSortByDate}>Sort by date</Button>
      {posts
        ? posts.map((post, i) => {
            return (
              <div id={i}>
                <div>{post.title}</div>
                <div>{post.content}</div>
                <div>views: {post.views}</div>
              </div>
            );
          })
        : null}
    </Container>
  );
};

export default Posts;
