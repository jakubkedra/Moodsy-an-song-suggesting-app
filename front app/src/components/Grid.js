import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import Button from "react-bootstrap/Button";
import ReactAudioPlayer from "react-audio-player";

function Grid(props) {
  return (
    <div className="row justify-content-sm-center h-550">
        <Row className="g-4">
          {props.object.reverse().map((obj) => {
            return (
              <div
                className="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9"
                key={obj.name}
              >
                <Col className="row justify-content-sm-center h-550">
                  <Card className="customCard" style={{ width: "25rem" }}>
                    <Tabs
                      defaultActiveKey="home"
                      id="uncontrolled-tab-example"
                      className="mb-2"
                    >
                      <Tab eventKey="home" title="Song">
                        <Card.Img variant="top" src={obj.url} />
                        <Card.Body>
                          <Card.Title>{obj.name}</Card.Title>
                          <Card.Text>{obj.artistName}</Card.Text>
                          <div className="bborder"></div>
                          <br />
                          <ReactAudioPlayer
                            className="odt"
                            src={obj.preview_url}
                            autoPlay
                            controls
                          />
                          <br />
                          <br />
                          <Button
                            className="customButton fw-bold"
                            href={obj.uri}
                          >
                            Play on Spotify!
                          </Button>
                        </Card.Body>
                      </Tab>
                      <Tab eventKey="profile" title="Mood Info">
                        <Card.Img
                          variant="top"
                          src={`data:image/png;base64, ${obj.photo}`}
                        />
                        <Card.Body>
                          <Card.Title>Mood: {obj.strongestEmotion}</Card.Title>
                          <div className="bborder"></div>
                          <br />
                        </Card.Body>
                      </Tab>
                    </Tabs>
                  </Card>
                </Col>
              </div>
            );
          })}
        </Row>
      </div>
  );
}

export default Grid;