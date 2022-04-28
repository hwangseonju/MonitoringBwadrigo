import { Tab, Tabs } from "react-bootstrap";
import { useState } from "react";

function PlanTypeHeader() {
  const [key, setKey] = useState("profile");

  return (
    <Tabs id="controlled-tab-example" activeKey={key} onSelect={(k) => setKey(k)} className="mb-3">
      <Tab eventKey="home" title="Home">
        <div>내용</div>
      </Tab>
      <Tab eventKey="profile" title="Profile">
        <Tab.Container>내용2</Tab.Container>
      </Tab>
      <Tab eventKey="contact" title="Contact" disabled></Tab>
    </Tabs>
  );
}

export default PlanTypeHeader;
