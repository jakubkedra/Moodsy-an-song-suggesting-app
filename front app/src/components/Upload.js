import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";

import { Button, Card, Form } from "react-bootstrap";
import { useState } from "react";
import axios from "axios";
import React from 'react';

import Display from "./Display";
import Grid from "./Grid";
function Upload() {
  
  const url="/api/saveTrack";
  const [img, setImg] = useState('');
  const [genre, setGenre] = useState('');
  const [object, setObject] = useState([]);
  const [visible, setVisible] = useState(false);
  const handleImage=(e)=>{
    setImg(e.target.files[0]);
  }
  const handleGenre=(e)=>{
    if(e.target.value!="Select genre"){
      setGenre(e.target.value);
    }
  }

  const upload = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('genres', genre);
    formData.append('image', img);
    axios({
      method: "post",
      url: url,
      data: formData,
      headers: { "Content-Type": "multipart/form-data" , transformRequest: formData => formData, Authorization: `Bearer ${localStorage.getItem("accessToken")}`},
      withCredentials: true
    })
      .then(response => {
        setObject(response.data);
        console.log(object);
        setVisible(true);
      })
      .catch(error => {
        alert("No face was detected on the photo.");
        console.error(error);
      }); 
  }


  return (
    <div className="App" style={{ height: "100vh" }}>
      <div className="row justify-content-sm-center h-550">
        <div className="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <Card className="customCard">
            <Card.Body className="p-5">
              <Card.Title className="text-white fs-4 text-center fw-bold mb-4 ">
                 Moodsy
              </Card.Title>
              <Card.Title className="text-white fs-2 text-center fw-bold mb-4 ">
                Upload photo.
              </Card.Title>
              <div className="about-border"></div>
              <Form>
                <Form.Group controlId="formFile" className="mb-3">
                  <Form.Control type="file" accept="image/png, image/jpeg" onChange={(e) => handleImage(e)}/>
                </Form.Group>
                <Form.Select aria-label="Default select example" onChange={(e) => handleGenre(e)}>
                  <option>Select genre</option>
                  <option value="rock">rock</option>
                  <option value="pop">pop</option>
                  <option value="jazz">jazz</option>
                  <option value="ambient">ambient</option>
                  <option value="hip-hop">hip-hop</option>
                </Form.Select>
                <br />
                <div className="col text-center">
                  <Button className="customButton fw-bold" type="submit" onClick={(e)=>upload(e)}>
                    Get song!
                  </Button>
                </div>
              </Form>
            </Card.Body>
          </Card>
        </div>
      </div><br/>
      {visible===true ? (
        <Display object={object}/>
      ) : (<div></div>)}
    </div>
  );
}

export default Upload;
