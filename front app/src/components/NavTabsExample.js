import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';


function UncontrolledExample() {
  return (
    <Tabs
      defaultActiveKey="profile"
      id="uncontrolled-tab-example"
      className="mb-3"
    >
      <Tab eventKey="home" title="Home">
        sraka1
      </Tab>
      <Tab eventKey="profile" title="Profile">
        sraka 2
      </Tab>
      <Tab eventKey="contact" title="Contact">
        sraka 3
      </Tab>
    </Tabs>
  );
}

export default UncontrolledExample;