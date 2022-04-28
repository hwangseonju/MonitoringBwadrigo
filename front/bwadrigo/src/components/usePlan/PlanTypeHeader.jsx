import { useState } from "react";
import { Card, Button, Accordion, Table, Row, Col, Container } from "react-bootstrap";

function PlanTypeHeader() {
  // 요금제 종류 리스트
  const [planList, setPlanList] = useState([
    {
      monthPlanId: 1,
      monthPlanName: "테스트요금",
      monthPlanPrice: 0,
      monthPlanBeddingCount: 0,
      monthPlanCleaningCount: 0,
      monthPlanDeliveryCount: 0,
      monthPlanWashCount: 0,
      monthPlanShirtCount: 0,
    },
  ]);

  // 회원이 사용중인 요금제
  const [usePlan, setUsePlan] = useState();

  // 변경할 요금제
  const [changeUsePlan, setChangeUsePlan] = useState();

  const changePlan = (planId) => {
    console.log(planId);
    setChangeUsePlan(planId);
  };

  return (
    <div>
      <h3>서비스 변경</h3>
      <div>
        <b>서비스</b>
        <Accordion alwaysOpen>
          {planList.map((item) => (
            <Accordion.Item key={item.monthPlanId} eventKey={item.monthPlanId}>
              <Accordion.Header>{item.monthPlanName}</Accordion.Header>
              <Accordion.Body>
                <div>
                  <Card className="text-center">
                    <Card.Header>
                      <Container>
                        <Row>
                          <Col>{item.monthPlanName}</Col>
                          <Col></Col>
                          <Col style={{ textAlign: "right" }}>{item.monthPlanPrice}원</Col>
                        </Row>
                      </Container>
                    </Card.Header>
                    <Card.Body>
                      <Table style={{ textAlign: "center", fontSize: "12px" }}>
                        <thead>
                          <tr>
                            {item.monthPlanWashCount !== 0 ? <th>생활빨래</th> : <th style={{ color: "#cfcfcf" }}>생활빨래</th>}
                            {item.monthPlanShirtCount !== 0 ? <th>와이셔츠</th> : <th style={{ color: "#cfcfcf" }}>와이셔츠</th>}
                            {item.monthPlanCleaningCount !== 0 ? <th>개별클리닝</th> : <th style={{ color: "#cfcfcf" }}>개별클리닝</th>}
                            {item.monthPlanBeddingCount !== 0 ? <th>이불</th> : <th style={{ color: "#cfcfcf" }}>이불</th>}
                            {item.monthPlanDeliveryCount !== 0 ? <th>수거배송</th> : <th style={{ color: "#cfcfcf" }}>수거배송</th>}
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            {item.monthPlanWashCount !== 0 ? <td>{item.monthPlanWashCount}개</td> : <td style={{ color: "#cfcfcf" }}>0개</td>}
                            {item.monthPlanShirtCount !== 0 ? <td>{item.monthPlanShirtCount}장</td> : <td style={{ color: "#cfcfcf" }}>0장</td>}
                            {item.monthPlanCleaningCount !== 0 ? <td>{item.monthPlanCleaningCount}장</td> : <td style={{ color: "#cfcfcf" }}>0장</td>}
                            {item.monthPlanBeddingCount !== 0 ? <td>{item.monthPlanBeddingCount}개</td> : <td style={{ color: "#cfcfcf" }}>0개</td>}
                            {item.monthPlanDeliveryCount !== 0 ? <td>{item.monthPlanDeliveryCount}회</td> : <td style={{ color: "#cfcfcf" }}>0회</td>}
                          </tr>
                        </tbody>
                      </Table>
                    </Card.Body>
                    <Card.Footer className="text-muted">
                      {usePlan === item.monthPlanId ? (
                        <Button style={{ color: "#cfcfcf" }} variant="light">
                          서비스 이용 중
                        </Button>
                      ) : (
                        <Button variant="primary" onClick={() => changePlan(item.monthPlanId)}>
                          변경하기
                        </Button>
                      )}
                    </Card.Footer>
                  </Card>
                </div>
              </Accordion.Body>
            </Accordion.Item>
          ))}
        </Accordion>
      </div>
    </div>
  );
}

export default PlanTypeHeader;
