import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {useNavigate} from 'react-router-dom';
import { Button, Card, Form } from "react-bootstrap";
import { useState } from "react";
import axios from "axios";
import Navbarbar from "./Navbarbar";

function Login() {
  
  const navigate = useNavigate();
  const url="/login";
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  
  function handle(e) {
    const newUser = { ...user };
    newUser[e.target.id] = e.target.value;
    setUser(newUser);
    console.log(newUser);
  }

  const login = (e) => {
    e.preventDefault();
    axios({
      method: "post",
      url: url,
      data: user,
      headers: { "Content-Type": "multipart/form-data" },
      withCredentials: true
    })
      .then(response => {
        localStorage.setItem('accessToken', response.data.access_token);
        navigate('/home');
      })
      .catch(error => {
        console.error(error);
        alert("Wrong password or email!")
      }); 
  }

  return (
    <div className="App" style={{ height: "100vh" }}>
      <Navbarbar/>
      <div className="row justify-content-sm-center h-550">
        <div className="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <br />
          <br />
          <br />
          <Card className="customCard">
            <Card.Body className="p-5">
              <Card.Title className="text-white fs-4 text-center fw-bold mb-4 ">
                Moodsy
              </Card.Title>
              <Card.Title className="text-white fs-2 text-center fw-bold mb-4 ">
                Log in to continue.
              </Card.Title>
              <div className="about-border"></div>
              <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                <input
                onChange={(e) => handle(e)}
                id="email"
                value={user.email}
                type="text"
                placeholder="Your email.."
                required
              ></input>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                <input
                onChange={(e) => handle(e)}
                id="password"
                value={user.password}
                type="password"
                placeholder="Your password.."
                required
              ></input>
                </Form.Group>
                <br />
                <div className="col text-center">
                  <Button className="customButton fw-bold" type="submit" onClick={(e)=>login(e)}>
                    Log in
                  </Button>
                </div>
              </Form>
            </Card.Body>
            <Card.Footer className="text-center text-white py-3 border-0">
              Don't have an account?{" "}
              <a className="toRegister" href="http://localhost:3000/register">
                Register
              </a>
            </Card.Footer>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default Login;
