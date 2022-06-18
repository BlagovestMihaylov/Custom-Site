import logo from "./logo.svg";
import "./App.css";
import "semantic-ui-css/semantic.min.css";
import Header from "./components/Header";
import HomePage from "./pages/Home";
import PostPage from "./pages/Post";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Redirect,
} from "react-router-dom";
import RegisterPage from "./pages/Register";
import LoginPage from "./pages/Login";

function App() {
  return (
    <div className="App">
      <div>
        <Router>
          <Header />
          <Routes>
            <Route exact path="/post/:id" element={<PostPage />} />
            <Route exact path="/login" element={<LoginPage />} />
            <Route exact path="/register" element={<RegisterPage />} />
            <Route exact path="/" element={<HomePage />} />
          </Routes>
        </Router>
      </div>
    </div>
  );
}

export default App;
