import { httpService } from "./http-service";
import { userService } from "./user-service";

class AuthService {
  userChangeHandler = null;

  set changeHandler(userChangeHandler) {
    this.userChangeHandler = userChangeHandler;
  }

  /* eslint-disable-next-line class-methods-use-this */
  get storedUser() {
    const userJson = localStorage.getItem("currentUser");
    const currentUser = userJson && JSON.parse(userJson);

    return currentUser ?? null;
  }

  /* eslint-disable-next-line class-methods-use-this */
  get storedToken() {
    const tokenJson = localStorage.getItem("currentToken");
    const currentToken = tokenJson && JSON.parse(tokenJson);

    return currentToken ?? null;
  }

  /* eslint-disable-next-line class-methods-use-this */
  get storedGoogleToken() {
    const googleTokenJson = localStorage.getItem("currentGoogleToken");
    const currentGoogleToken = googleTokenJson && JSON.parse(googleTokenJson);

    return currentGoogleToken ?? null;
  }

  setCurrentUser(user) {
    if (user) {
      localStorage.setItem("currentUser", JSON.stringify(user));
    } else {
      localStorage.removeItem("currentUser");
    }

    this.userChangeHandler?.(user);
  }

  /* eslint-disable-next-line class-methods-use-this */
  setCurrentToken(token) {
    if (token) {
      localStorage.setItem("currentToken", JSON.stringify(token));
    } else {
      localStorage.removeItem("currentToken");
    }
  }

  async login(username, password) {
    const userInfo = await userService.login(username, password);
    // If no error was thrown (i.e. the user info is valid and a token is generated)
    // save the token in local storage and remove the google token id\
    this.setCurrentToken(userInfo.token);
    this.setCurrentUser(userInfo);
  }

  logout() {
    this.setCurrentToken(null);
    this.setCurrentUser(null);
  }

  async updateUserNickname(nickname) {
    const user = this.storedUser;
    if (user) {
      user.nickname = nickname;
      this.setCurrentUser(user);
    }
  }
}

/* eslint-disable-next-line import/prefer-default-export */
export const authService = new AuthService();
