import { authService } from "./auth-service";

class HttpService {
  async get(path) {
    return this.request("get", path);
  }

  async post(path, body) {
    return this.request("post", path, body);
  }

  async delete(path, body) {
    return this.request("delete", path, body);
  }

  async put(path, body) {
    return this.request("put", path, body);
  }

  async request(method, path, body) {
    const userToken = "blagosite " + authService.storedToken;
    const response = await fetch(`${process.env.REACT_APP_SERVER_URL}${path}`, {
      method,
      mode: "cors",
      headers: {
        ...(userToken ? { Authorization: `Bearer ${userToken}` } : undefined),

        "Content-Type": "application/json",
      },
      body: body && JSON.stringify(body),
    });
    console.log(response);

    const responseBody = await response.json();

    if (response.status === 401) {
      throw new "AuthenticationError: "();
    }

    if (response.status < 200 || response.status >= 300) {
      throw new "ServerError: "();
    }

    return responseBody;
  }
}

export const httpService = new HttpService();
