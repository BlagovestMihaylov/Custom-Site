import React from "react";
import { Container, Form, Button } from "semantic-ui-react";
import { authService } from "../../services/auth-service";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleLogin = async () => {
    const res = authService.login(username, password);
    navigate("/", { replace: true });
  };

  return (
    <Container>
      <Form>
        <Form.Field>
          <label>Username</label>
          <input
            placeholder="Username"
            value={username}
            onChange={handleUsernameChange}
          />
        </Form.Field>
        <Form.Field>
          <label>password</label>
          <input
            placeholder="Username"
            value={password}
            onChange={handlePasswordChange}
          />
        </Form.Field>
        <Button type="submit" onClick={handleLogin}>
          Submit
        </Button>
      </Form>
    </Container>
  );
};

export default LoginPage;
