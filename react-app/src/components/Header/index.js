import React, { useEffect } from "react";
import { Grid, Button } from "semantic-ui-react";
import { useNavigate } from "react-router-dom";
import { authService } from "../../services/auth-service";

const Header = () => {
  let navigate = useNavigate();
  const [user, setUser] = React.useState();

  useEffect(() => {
    const user = authService.storedUser;
    setUser(user);
  }, []);

  const handleLogout = () => {
    authService.logout();
    navigate("/", { replace: true });
  };

  const handleLoginClick = () => {
    navigate("/login", { replace: true });
  };

  const handleRegisterClick = () => {
    navigate("/register", { replace: true });
  };
  console.log(user);

  return (
    <div columns={3}>
      <Grid style={{ height: "100px", paddingTop: "20px" }}>
        <Grid.Row>
          <Grid.Column width={4}>Logo</Grid.Column>
          <Grid.Column width={12} floated="right">
            {user ? (
              <div>
                You are logged in
                <Button onClick={handleLogout}>Logout</Button>
              </div>
            ) : (
              <>
                <Button
                  style={{ display: "inline-block" }}
                  onClick={handleLoginClick}
                >
                  Login
                </Button>
                <Button
                  style={{ display: "inline-block" }}
                  onClick={handleRegisterClick}
                >
                  Register
                </Button>
              </>
            )}
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <hr style={{ width: "94%" }} />
    </div>
  );
};

export default Header;
