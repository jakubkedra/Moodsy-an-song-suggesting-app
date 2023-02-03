import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {useNavigate} from 'react-router-dom';
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
function Navbarbar() {
  
    const navigate = useNavigate();
    const logout = () => {
      window.localStorage.removeItem("accessToken");
      window.localStorage.removeItem("token");
      navigate("/home");
      window.location.reload(false);
    };

  return (
    <div className="App" style={{ height: "10vh" }}>
      {!localStorage.getItem("accessToken") ? (
        <Navbar className="navbar">
          <Container>
            <Navbar.Brand className="navbar fw-bold" href="http://localhost:3000/home">
              Moodsy
            </Navbar.Brand>
            <Nav className="me-auto">
              <Nav.Link className="navbar" href="http://localhost:3000/login">
                Login
              </Nav.Link>
              <Nav.Link
                className="navbar"
                href="http://localhost:3000/register"
              >
                Register
              </Nav.Link>
            </Nav>
          </Container>
        </Navbar>
      ) : (
        <Navbar className="navbar">
                      <Container>
                          <Navbar.Brand className="navbar fw-bold" href="#home">
                              Moodsy
                          </Navbar.Brand>
                          <Nav className="me-auto">
                              <Nav.Link className="navbar" href="http://localhost:3000/history">
                                  History
                              </Nav.Link>
                              <Nav.Link className="navbar" onClick={logout}>
                                  Logout
                              </Nav.Link>
                          </Nav>
                      </Container>
                  </Navbar>
      )}
    </div>
  );
}

export default Navbarbar;

