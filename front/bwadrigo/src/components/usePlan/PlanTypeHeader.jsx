import axios from "axios";
import { useCallback, useEffect, useState } from "react";
import { Card, Button, Accordion, Table, Row, Col, Container } from "react-bootstrap";
import styles from "./UsePlan.css";
import { RiShirtLine } from "react-icons/ri";
import { FaTruck } from "react-icons/fa";
import { BiBlanket } from "react-icons/bi";
import { GiHanger } from "react-icons/gi";
import { CgSmartHomeWashMachine } from "react-icons/cg";

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
  const [usePlan, setUsePlan] = useState(localStorage.getItem("applyeMontPlan"));

  // 변경할 요금제
  const [changeUsePlan, setChangeUsePlan] = useState();

  const changePlan = useCallback(async (planId) => {
    setChangeUsePlan(planId);
    let Authorization = localStorage.getItem("authorization");
    let RefreshToekn = localStorage.getItem("refreshtoken");
    await axios({
      method: "put",
      url: "/v1/api/plan",
      data: {
        monthPlanId: planId,
      },
      headers: {
        Authorization: Authorization,
        RefreshToken: RefreshToekn,
      },
    }).then((res) => {
      alert("요금제 변경 요청이 정상적으로 이루어졌습니다.\n현재 사용중인 요금제 마감일 이후 요금제가 변경됩니다.");
      window.location.href = "/member-plan";
    });
  });

  useEffect(() => {
    let Authorization = localStorage.getItem("authorization");
    let RefreshToekn = localStorage.getItem("refreshtoken");
    async function getPlan(){
      await axios({
        method: "get",
        url: "/v1/api/plan",
        headers: {
          Authorization: Authorization,
          RefreshToken: RefreshToekn,
        }
      }).then((res) => {
        setUsePlan(res.data.monthPlanId)
      })
    }
    getPlan();
    axios.get("/v1/api/plan/month/list").then((res) => {
      console.log(res);
      setPlanList(res.data);
    });
  }, []);

  return (
    <div>
      <h3 className="text-center Title">서비스 변경</h3>
      <hr />
      <div>
        <b style={{ fontSize: "20px", paddingLeft: "10px" }}>요금제 서비스</b>
        <br />
        <br />
        <div style={{ paddingLeft: "10px", paddingBottom: "5px" }}>
          <b>어떤 요금제로 변경하시겠습니까?</b>
        </div>
        <div style={{ paddingLeft: "10px" }}>다음 결제일에 선택하신 요금제로 변경됩니다.</div>
        <br />
        <hr />
        <br />
        <div style={{ paddingLeft: "10px" }}>
          <b>원하는 요금제를 선택하세요.</b>
        </div>
        <br />
        <Accordion alwaysOpen>
          {planList.map((item) => (
            <Accordion.Item key={item.monthPlanId} eventKey={item.monthPlanId}>
              <Accordion.Header>{item.monthPlanName}</Accordion.Header>
              <Accordion.Body>
                <div>
                  <Card className="text-center">
                    <Card.Header style={{ backgroundColor: "#EEEEEE" }}>
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
                            {item.monthPlanWashCount !== 0 ? (
                              <th>
                                <CgSmartHomeWashMachine size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                생활빨래
                              </th>
                            ) : (
                              <th style={{ color: "#cfcfcf" }}>
                                <CgSmartHomeWashMachine size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                생활빨래
                              </th>
                            )}
                            {item.monthPlanShirtCount !== 0 ? (
                              <th>
                                <RiShirtLine size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                와이셔츠
                              </th>
                            ) : (
                              <th style={{ color: "#cfcfcf" }}>
                                <RiShirtLine size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                와이셔츠
                              </th>
                            )}
                            {item.monthPlanCleaningCount !== 0 ? (
                              <th>
                                <GiHanger size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                개별클리닝
                              </th>
                            ) : (
                              <th style={{ color: "#cfcfcf" }}>
                                <GiHanger size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                개별클리닝
                              </th>
                            )}
                            {item.monthPlanBeddingCount !== 0 ? (
                              <th>
                                <BiBlanket size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                이불
                              </th>
                            ) : (
                              <th style={{ color: "#cfcfcf" }}>
                                <BiBlanket size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                이불
                              </th>
                            )}
                            {item.monthPlanDeliveryCount !== 0 ? (
                              <th>
                                <FaTruck size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                수거배송
                              </th>
                            ) : (
                              <th style={{ color: "#cfcfcf" }}>
                                <FaTruck size="35px" style={{ paddingBottom: "3px" }} />
                                <br />
                                수거배송
                              </th>
                            )}
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
                    <Card.Footer className="text-muted" style={{ backgroundColor: "#ffffff" }}>
                      {usePlan == item.monthPlanId ? (
                        <button className="btnCancle" disabled>
                          서비스 이용 중
                        </button>
                      ) : (
                        <button className="btnPlan" onClick={() => changePlan(item.monthPlanId)}>
                          변경하기
                        </button>
                      )}
                    </Card.Footer>
                  </Card>
                </div>
              </Accordion.Body>
            </Accordion.Item>
          ))}
        </Accordion>
      </div>
      <div className="checkNotice">
        <br />
        <div>확인해주세요</div>
        <div>
          <ul className="notice" style={{ fontSize: "13px" }}>
            <li>
              서비스 변경 예약 시, 다음 결제일부터 변경된 요금제
              <br />
              적용됩니다. (사용 내역, 결제 금액 등)
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default PlanTypeHeader;
