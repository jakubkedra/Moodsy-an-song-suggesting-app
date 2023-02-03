import Navbarbar from "./Navbarbar";
import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import ReactAudioPlayer from "react-audio-player";
import { CardGroup } from "react-bootstrap";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
function Display(props) {
  return (
    <div className="App" style={{ height: "100vh" }}>
      {/* <div className="row justify-content-sm-center h-550">
        <div className="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <CardGroup>
            <Card className="customCard" style={{ width: "19rem" }}>
              <Card.Img variant="top" src={props.object.url} />
              <Card.Body>
                <Card.Title>{props.object.name}</Card.Title>
                <Card.Text>{props.object.artistName}</Card.Text>
                <div className="bborder"></div><br/>
                <ReactAudioPlayer
                  className="odt"
                  src={props.object.preview_url}
                  autoPlay
                  controls
                /><br/><br/>
                <Button className="customButton fw-bold" href={props.object.uri} >Play on Spotify!</Button>
              </Card.Body>
            </Card>
            <Card className="customCard" style={{ width: "19rem" }}>
              <Card.Img variant="top" src={`data:image/png;base64, ${props.object.photo}`} />
              <Card.Body>
                <Card.Title>Mood: {props.object.strongestEmotion}</Card.Title>
                <div className="bborder"></div><br/>
              </Card.Body>
            </Card>
          </CardGroup>
        </div>
      </div> */}
      <div className="row justify-content-sm-center h-550">
        <Row className="g-4">
              <div key={props.object.name}>
                <Col className="row justify-content-sm-center h-550">
                  <Card className="customCard" style={{ width: "25rem" }}>
                    <Tabs
                      defaultActiveKey="home"
                      id="uncontrolled-tab-example"
                      className="mb-2"
                    >
                      <Tab eventKey="home" title="Song">
                        <Card.Img variant="top" src={props.object.url} />
                        <Card.Body>
                          <Card.Title>{props.object.name}</Card.Title>
                          <Card.Text>{props.object.artistName}</Card.Text>
                          <div className="bborder"></div>
                          <br />
                          <ReactAudioPlayer
                            className="odt"
                            src={props.object.preview_url}
                            autoPlay
                            controls
                          />
                          <br />
                          <br />
                          <Button
                            className="customButton fw-bold"
                            href={props.object.uri}
                          >
                            Play on Spotify!
                          </Button>
                        </Card.Body>
                      </Tab>
                      <Tab eventKey="profile" title="Mood Info">
                        <Card.Img
                          variant="top"
                          src={`data:image/png;base64, ${props.object.photo}`}
                        />
                        <Card.Body>
                          <Card.Title>Mood: {props.object.strongestEmotion}</Card.Title>
                          <div className="bborder"></div>
                          <br />
                        </Card.Body>
                      </Tab>
                    </Tabs>
                  </Card>
                </Col>
              </div>
        </Row>
      </div>
    </div>
  );
}

export default Display;
