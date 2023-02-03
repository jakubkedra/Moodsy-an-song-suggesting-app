import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {useNavigate} from 'react-router-dom';
import { Button, Card, Form } from "react-bootstrap";
import { useState } from "react";
import axios from "axios";

import Navbarbar from "./Navbarbar";

function Register() {

  const navigate = useNavigate();
  const url="/registration";
  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
  });
  function handle(e) {
    const newUser = { ...user };
    newUser[e.target.id] = e.target.value;
    setUser(newUser);
  }
 
  function validateEmail(){
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9]+)*$/;
    if(user.email.match(emailRegex)){
      return true;
    }else{
      alert("Invalid email!");
      return false;
    }
  }

  function validateUsername(){
    const usernameRegex = /^[a-zA-Z0-9_]+$/;
    if(user.username.match(usernameRegex)){
      return true;
    }else{
      alert("Invalid username!");
      return false;
    }
  }

  function validatePassword(){
    const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=])[A-Za-z0-9!@#$%^&*()_+-=]{8,}$/;
    if(user.password.match(passwordRegex)){
      return true;
    }else{
      alert("Invalid password!");
      return false;
    }
  }

  function register(e){
    e.preventDefault();
    if(validateEmail()===true && validateUsername()===true && validatePassword()===true){
      axios.post(url, {
        username: user.username,
        email: user.email,
        password: user.password
      }).then(res => {
        console.log(res.data);
        navigate('/home');
      })
    }
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
                Register.
              </Card.Title>
              <div className="about-border"></div>
              <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <input
                    onChange={(e) => handle(e)}
                    id="username"
                    value={user.username}
                    type="text"
                    placeholder="Your username.."
                    required
                  ></input>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <input
                    onChange={(e) => handle(e)}
                    id="email"
                    value={user.email}
                    type="email"
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
                  <Button className="customButton fw-bold" type="submit" onClick={(e)=>register(e)}>
                    Register
                  </Button>
                </div>
              </Form>
            </Card.Body>
            <Card.Footer className="text-center text-white py-3 border-0">
              Already have an account?{" "}
              <a className="toRegister" href="http://localhost:3000/login">
                Login
              </a>
            </Card.Footer>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default Register;
