import { httpService } from "./http-service";

class PostService {
  async getPosts() {
    const res = await httpService.get("/registration/posts");
    return res;
  }
}

export const postService = new PostService();
