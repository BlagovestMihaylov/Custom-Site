import { httpService } from "./http-service";

class UserService {
  async register(
    username,
    email,
    password,
    phoneNumber,
    imageUrl = "https://i.ytimg.com/vi/1A7NzL4lMhc/maxresdefault.jpg"
  ) {
    const res = httpService.post("/registration", {
      username,
      password,
      email,
      phone_number: phoneNumber,
      image_url: imageUrl,
    });
    return res;
  }

  async login(username, password) {
    const res = httpService.post("/login", {
      username,
      password,
    });
    return res;
  }
}

export const userService = new UserService();
