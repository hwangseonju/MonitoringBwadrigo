import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Row, Col, Container } from "react-bootstrap";
import styles from "./UsePlan.css";
import { GrDocumentVerified, GrDocumentMissing } from "react-icons/gr";

function MemberPlan() {
  //const title = "이용 중인 서비스";
  const goTermination = () => {
    window.location.href = "/start-termination";
  };

  const goChange = () => {
    window.location.href = "/plan-type-header";
  };

  // 회원이 사용중인 요금제
  const [applyList, setApplyList] = useState({
    applyId: 1,
    applyWashCount: 0,
    applyBeddingCount: 0,
    applyDeliveryCount: 0,
    applyCleaningCount: 0,
    applyShirtCount: 0,
    applyDate: "2022-04-22",
    applyChange: 0,
    monthPlanId: 2,
  });

  // 회원 이용 기간
  const [applyStartDate, setApplyStartDate] = useState();
  const [applyEndDate, setApplyEndDate] = useState();

  // 회원 이용 기간 계산
  const startDate = (applyDate) => {
    let startYY = parseInt(applyDate.substring(0, 4));
    let startMM = parseInt(applyDate.substring(5, 7));
    let startDD = parseInt(applyDate.substring(8, 10));
    let start = startYY + "년 " + startMM + "월 " + startDD + "일";
    // setApplyStartDate(start);
    return start;
  };

  const endDate = (applyDate) => {
    let startYY = parseInt(applyDate.substring(0, 4));
    let startMM = parseInt(applyDate.substring(5, 7));
    let startDD = parseInt(applyDate.substring(8, 10));

    let month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    startDD = startDD + 28;
    if (startDD > month[startMM]) {
      startDD = startDD - month[startMM];
      startMM = startMM + 1;

      if (startMM > 12) {
        startMM = 1;
        startYY = startYY + 1;
      }
    }
    let end = startYY + "년 " + startMM + "월 " + startDD + "일";
    // setApplyEndDate(end);
    return end;
  };

  // 요금제 종류 리스트
  const [plan, setPlan] = useState([
    {
      monthPlanId: 3,
      monthPlanName: "테스트요금",
      monthPlanPrice: 0,
      monthPlanBeddingCount: 0,
      monthPlanCleaningCount: 0,
      monthPlanDeliveryCount: 0,
      monthPlanWashCount: 0,
      monthPlanShirtCount: 0,
    },
  ]);

  // 회원 정보
  const [memberList, setMemberList] = useState({
    memberEmail: "test@test.com",
    memberPhone: "000-000-000",
    memberAddress: "광주 어디",
  });
  useEffect(() => {
    let Authorization = localStorage.getItem("authorization");
    let RefreshToekn = localStorage.getItem("refreshtoken");
    let url = "/v1/api/member";
    async function getMember() {
      await axios({
        method: "get",
        url: url,
        headers: {
          Authorization: Authorization,
          RefreshToken: RefreshToekn,
        },
      })
        .then((res) => {
          setMemberList(res.data)
          async function getPlan(){
            await axios({
                method: "get",
                url : "/v1/api/plan",
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }
            }).then((res)=>{
              setApplyList(res.data)
                }
            ).catch((err)=>{
                window.location.href="/pleaseService"
            })
        }
        getPlan();
        })
        .catch((err) => {
          console.log(err);
          window.location.href = "/profile";
        });
      // console.log(response)
    }
    getMember();
  }, []);

  return (
    <div>
      <div>
        <div>
          <h3 className="text-center Title">이용 중인 서비스</h3>
        </div>
        <hr />
        <br />
        <div>
          <Container key={applyList.applyId} className="usePlan">
            <Row>
              <Col>
                <b>신청 서비스</b>
              </Col>
              {applyList.monthPlanId > 1 ? (
                <Col xs={8} style={{ textAlign: "right" }}>
                  월정액서비스
                </Col>
              ) : (
                <Col xs={8} style={{ textAlign: "right" }}>
                  1회이용서비스
                </Col>
              )}
            </Row>
            <Row>
              <Col>
                <b>신청일</b>
              </Col>
              <Col xs={8} style={{ textAlign: "right" }}>
                {startDate(applyList.applyDate)}
              </Col>
            </Row>
            <Row>
              <Col>
                <b>이용상태</b>
              </Col>
              {applyList.length === 0 ? (
                <Col xs={8} style={{ textAlign: "right" }}>
                  -
                </Col>
              ) : (
                <Col xs={8} style={{ textAlign: "right" }}>
                  이용중
                </Col>
              )}
            </Row>
            {applyList.monthPlanId > 1 && (
              <div>
                <Row>
                  <Col>
                    <b>이용기간</b>
                  </Col>
                  <Col xs={9} style={{ textAlign: "right" }}>
                    {startDate(applyList.applyDate)} ~ {endDate(applyList.applyDate)}
                  </Col>
                </Row>
                <hr />
                {/* {plan.map((plan) => ( */}
                <div key={plan.monthPlanId}>
                  {plan.monthPlanId === applyList.monthPlanId && (
                    <div>
                      <Row>
                        <Col>
                          <b>요금제</b>
                        </Col>
                        <Col xs={8} style={{ textAlign: "right" }}>
                          {plan.monthPlanName}
                        </Col>
                      </Row>
                      <Row>
                        <Col>
                          <b>요금제 금액</b>
                        </Col>
                        <Col xs={8} style={{ textAlign: "right" }}>
                          {plan.monthPlanPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}원
                        </Col>
                      </Row>
                      <br />
                      <Row style={{ fontSize: "13px", paddingBottom: "0px" }}>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px" }}>
                          {applyList.applyWashCount !== 0 ? (
                            <Row>
                              <Col>생활빨래</Col>
                              <Col xs={5} style={{ textAlign: "right" }}>
                                {applyList.applyWashCount}개
                              </Col>
                            </Row>
                          ) : (
                            <Row style={{ color: "#cfcfcf" }}>
                              <Col>생활빨래</Col>
                              <Col xs={5} style={{ textAlign: "right" }}>
                                {applyList.applyWashCount}개
                              </Col>
                            </Row>
                          )}
                        </Col>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px" }}>
                          {applyList.applyShirtCount !== 0 ? (
                            <Row>
                              <Col>와이셔츠</Col>
                              <Col xs={5} style={{ textAlign: "right" }}>
                                {applyList.applyShirtCount}장
                              </Col>
                            </Row>
                          ) : (
                            <Row style={{ color: "#cfcfcf" }}>
                              <Col>와이셔츠</Col>
                              <Col xs={5} style={{ textAlign: "right" }}>
                                {applyList.applyShirtCount}장
                              </Col>
                            </Row>
                          )}
                        </Col>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px" }}>
                          {applyList.applyCleaningCount !== 0 ? (
                            <Row>
                              <Col>개별클리닝</Col>
                              <Col xs={4} style={{ textAlign: "right" }}>
                                {applyList.applyCleaningCount}장
                              </Col>
                            </Row>
                          ) : (
                            <Row style={{ color: "#cfcfcf" }}>
                              <Col>개별클리닝</Col>
                              <Col xs={4} style={{ textAlign: "right" }}>
                                {applyList.applyCleaningCount}장
                              </Col>
                            </Row>
                          )}
                        </Col>
                      </Row>
                      <Row style={{ fontSize: "13px" }}>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px" }}>
                          {applyList.applyBeddingCount !== 0 ? (
                            <Row>
                              <Col>이불</Col>
                              <Col xs={4} style={{ textAlign: "right" }}>
                                {applyList.applyBeddingCount}개
                              </Col>
                            </Row>
                          ) : (
                            <Row style={{ color: "#cfcfcf" }}>
                              <Col>이불</Col>
                              <Col xs={4} style={{ textAlign: "right" }}>
                                {applyList.applyBeddingCount}개
                              </Col>
                            </Row>
                          )}
                        </Col>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px", fontSize: "13px" }}>
                          {applyList.applyDeliveryCount !== 0 ? (
                            <Row>
                              <Col>수거배송</Col>
                              <Col xs={4} style={{ textAlign: "right", fontSize: "13px" }}>
                                {applyList.applyDeliveryCount}회
                              </Col>
                            </Row>
                          ) : (
                            <Row style={{ color: "#cfcfcf" }}>
                              <Col>수거배송</Col>
                              <Col xs={4} style={{ textAlign: "right", fontSize: "13px" }}>
                                {applyList.applyDeliveryCount}회
                              </Col>
                            </Row>
                          )}
                        </Col>
                        <Col style={{ border: "1px solid #b5b1b1", padding: "10px" }}></Col>
                      </Row>
                      <Row>
                        <Col style={{ textAlign: "right" }}>{/* <a href="@">월정액 초과 이용 가격표 보기</a> */}</Col>
                      </Row>
                    </div>
                  )}
                </div>
                {/* ))} */}
              </div>
            )}
            {applyList.monthPlanId === 1 && (
              <div>
                <Row>
                  <Col>
                    <b>세탁요금</b>
                  </Col>
                  <Col xs={8} style={{ textAlign: "right" }}>
                    안심 정찰 가격표에 의해 요금 부과
                  </Col>
                </Row>
                <Row>
                  <Col style={{ textAlign: "right" }}>
                    <a href="/laundryPlan">안심 정찰 가격표 보기</a>
                  </Col>
                </Row>
              </div>
            )}
          </Container>
        </div>
      </div>
      <br />
      <div>
        <Container key={applyList.applyId}>
          <Row className="text-center">
            <Col style={{ border: "1px solid #b5b1b1", padding: "15px" }} onClick={goChange}>
              <GrDocumentVerified size="30px" />
              <br />
              변경
            </Col>
            <Col style={{ border: "1px solid #b5b1b1", padding: "15px" }} onClick={goTermination}>
              <GrDocumentMissing size="30px" />
              <br />
              해지
            </Col>
          </Row>
        </Container>
      </div>

      <br />
      <br />

      <div>
        <b style={{ fontSize: "20px", paddingLeft: "10px" }}>신청 서비스</b>
        <br />
        <br />
        <Container key={memberList.memberEmail} className="usePlan">
          <Row>
            <Col>
              <b>배송지</b>
            </Col>
            <Col xs={8}>{memberList.memberAddress}</Col>
          </Row>
          <Row>
            <Col>
              <b>연락처</b>
            </Col>
            <Col xs={8}>{memberList.memberPhone}</Col>
          </Row>
        </Container>
      </div>
    </div>
  );
}

export default MemberPlan;
