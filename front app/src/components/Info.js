import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Card } from "react-bootstrap";
import { Link } from "react-router-dom";
function Info() {
  return (
    <div>
      <div className="row justify-content-sm-center h-550">
        <div className="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
          <br />
          <br />
          <br />
          <Card className="customCard">
            <Card.Body className="p-5">
              <Card.Title className="text-white fs-2 text-center fw-bold mb-4 ">
                Moodsy
              </Card.Title>
              <div className="about-border"></div>
              <Card.Text className="text-center">
                An application that suggests music for you based on your
                emotions!
              </Card.Text>
              <div className="col text-center">
                <a href="/register">
                  <Button className="customButton fw-bold" type="submit">
                    Get started!
                  </Button>
                </a>
              </div>
            </Card.Body>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default Info;
