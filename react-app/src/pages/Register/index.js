import React from "react";
import { Container, Form, Button } from "semantic-ui-react";
import { userService } from "../../services/user-service";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [username, setUsername] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [phoneNumber, setPhoneNumber] = React.useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handlePhoneChange = (e) => {
    setPhoneNumber(e.target.value);
  };

  const Register = async () => {
    const res = userService.register(username, email, password, phoneNumber);
    if (res) navigate("/login", { replace: true });
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
          <label>Email</label>
          <input
            placeholder="Username"
            value={email}
            onChange={handleEmailChange}
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
        <Form.Field>
          <label>Phone Number</label>
          <input
            placeholder="Username"
            value={phoneNumber}
            onChange={handlePhoneChange}
          />
        </Form.Field>
        <Button type="submit" onClick={Register}>
          Submit
        </Button>
      </Form>
    </Container>
  );
};

export default RegisterPage;
