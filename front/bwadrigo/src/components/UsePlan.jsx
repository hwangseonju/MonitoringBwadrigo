import axios from "axios";
import { useEffect, useState } from "react";
import {Accordion, Card, ListGroup, Table,Container,Row,Col } from "react-bootstrap";

function UsePlan() {
    
    const [applyDto, setApplyDto] = useState({
        "apllyId" : 2,
        "applyWashCount" : 1,
        "applyBeddingCount" : 0,
        "applyShirtCount" : 1,
        "applyCleaningCount" : 1,
        "applyDeliveryCount" : 1,
        "applyDate" : '2022-04-28',
        "applyFinishDate" : "2022-05-26",
    })
    const [payDto, setPayDto] = useState([])
    const [mySet, setMySet] = useState([])
    const startDate = (applyDate) => {
        let date = applyDate.split("T")
        date = date[0].split("-");
        
        let startYY = date[0];
        let startMM = date[1];
        let startDD = date[2];
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
        return end;
    };
    const [monthPlanDto, setMonthPlanDto] = useState({
        "monthPlanId" : 2,
        "monthPlanName" : "올인원",
        "monthPlanWashCount" : 3,
        "monthPlanBeddingCount" : 0,
        "monthPlanShirtCount" : 20,
        "monthPlanCleaningCount" : 3,
        "monthPlanDeliveryCount" : 3,
    })
    useEffect(()=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        async function getPlan(){
            await axios({
                method:"get",
                url : "/v1/api/plan",
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }
            }).then((res)=>{
                console.log(res.data)
                setApplyDto(res.data)                
                console.log(applyDto)
                axios({
                    method:"get",
                    url:`/v1/api/plan/month/${res.data.monthPlanId}`,
                    headers : {
                        "Authorization" : Authorization,
                        "RefreshToken" : RefreshToekn 
                    }
                }).then((response)=>{
                    setMonthPlanDto(response.data)
                })
            }).catch((err)=>{
                alert("신청하신 월정액이 없습니다.")
                window.location.replace("/")
            })
        }
        getPlan();
    },[])
    useEffect(()=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        async function getOrder(){
            await axios({
                method:"get",
                url :"/v1/api/order/bill/1",
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }
            }).then((res) => {
                let list = res.data
                let payDtoMap = new Map();
                let set = new Set();
                for(let i=0; i<list.length; i++){
                    let key = list[i].payResponseDate.split("T")[0];
                    set.add(key);
                    if(payDtoMap.has(key)){
                        let payDtoList = payDtoMap.get(key);
                        payDtoList.push(list[i])
                        payDtoMap.set(key,payDtoList);
                    }else{
                        let payDtoList = [];
                        payDtoList.push(list[i])
                        payDtoMap.set(key,payDtoList);
                    }
                }
                setPayDto(payDtoMap)
                setMySet(Array.from(set))
            })
        }
    },[])
    return(
        <>
            <Card className="text-center">
                <Card.Header>
                <Table striped bordered hover variant="white">
                    <thead>
                        <tr>
                        <th colSpan={2}>{applyDto.monthPlanId == 1 ? "자유 이용": "월정액"} 서비스</th>
                        <th>&gt;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td colSpan={2}>{monthPlanDto.monthPlanName} | {startDate(applyDto.applyDate)}~{endDate(applyDto.applyDate)}</td>
                        <td></td>
                        </tr>
                    </tbody>
                </Table>
                </Card.Header>
                <Card className="text-center">
                <Table style={{ textAlign: "center", fontSize: "12px" }}>
                        <thead>
                          <tr>
                            {monthPlanDto.monthPlanWashCount !== 0 ? <th>생활빨래</th> : <th style={{ color: "#cfcfcf" }}>생활빨래</th>}
                            {monthPlanDto.monthPlanShirtCount !== 0 ? <th>와이셔츠</th> : <th style={{ color: "#cfcfcf" }}>와이셔츠</th>}
                            {monthPlanDto.monthPlanCleaningCount !== 0 ? <th>개별클리닝</th> : <th style={{ color: "#cfcfcf" }}>개별클리닝</th>}
                            {monthPlanDto.monthPlanBeddingCount !== 0 ? <th>이불</th> : <th style={{ color: "#cfcfcf" }}>이불</th>}
                            {monthPlanDto.monthPlanDeliveryCount !== 0 ? <th>수거배송</th> : <th style={{ color: "#cfcfcf" }}>수거배송</th>}
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            {monthPlanDto.monthPlanWashCount !== 0 ? <td>{applyDto.applyWashCount}/{monthPlanDto.monthPlanWashCount}</td> : <td style={{ color: "#cfcfcf" }}>0</td>}
                            {monthPlanDto.monthPlanShirtCount !== 0 ? <td>{applyDto.applyShirtCount}/{monthPlanDto.monthPlanShirtCount}</td> : <td style={{ color: "#cfcfcf" }}>0</td>}
                            {monthPlanDto.monthPlanCleaningCount !== 0 ? <td>{applyDto.applyCleaningCount}/{monthPlanDto.monthPlanCleaningCount}</td> : <td style={{ color: "#cfcfcf" }}>0</td>}
                            {monthPlanDto.monthPlanBeddingCount !== 0 ? <td>{applyDto.applyBeddingCount}/{monthPlanDto.monthPlanBeddingCount}</td> : <td style={{ color: "#cfcfcf" }}>0</td>}
                            {monthPlanDto.monthPlanDeliveryCount !== 0 ? <td>{applyDto.applyDeliveryCount}/{monthPlanDto.monthPlanDeliveryCount}</td> : <td style={{ color: "#cfcfcf" }}>0</td>}
                          </tr>
                        </tbody>
                      </Table>
                </Card>
                <Card.Body>
                    <Card.Title>이용 내역</Card.Title>
                    <Accordion alwaysOpen>
                        {
                            mySet.map((key)=>(
                                <Accordion.Item key={key} eventKey={key}>
                                    <Accordion.Header>{key}</Accordion.Header>
                                    <Accordion.Body>
                                        <div>
                                            <Card className="text-center">
                                                <Card.Header>
                                                    <Container>
                                                        <Row>
                                                            <Col>{key}</Col>
                                                            <Col></Col>
                                                            <Col>
                                                            </Col>
                                                            {/* <Col style={{ textAlign: "right" }}>{pay[0].payRequestCount*(applyDto.monthPlanId==1?pay.laundryPlanDto.laundryPlanPrice : pay.laundryPlanDto.laundryPlanPrice*0.8)}원</Col> */}
                                                        </Row>
                                                    </Container>
                                                </Card.Header>
                                                <Card.Body>
                                                    <Container>
                                                        {payDto.get(key).map((pay)=>(
                                                            pay.laundryPlanDto.laundryPlanTypeEng=="wash" ?
                                                            
                                                                <Row>
                                                                <Col>{pay.laundryPlanDto.laundryPlanDetails}</Col>
                                                                <Col>{(pay.payRequestCount-1)*0.5}kg 초과</Col>
                                                                <Col style={{ textAlign: "right" }}>{(applyDto.monthPlanId==1?pay.laundryPlanDto.laundryPlanPrice : pay.laundryPlanDto.laundryPlanPrice*0.8) + (pay.payRequestCount-1)*(applyDto.monthPlanId==1?1000 : 800)}원</Col>
                                                                </Row>
                                                            
                                                            :
                                                            
                                                            <Row>
                                                            <Col>{pay.laundryPlanDto.laundryPlanDetails}</Col>
                                                            <Col>{pay.payReuestCount}</Col>
                                                            <Col style={{ textAlign: "right" }}>{pay.payRequestCount*(applyDto.monthPlanId==1?pay.laundryPlanDto.laundryPlanPrice : pay.laundryPlanDto.laundryPlanPrice*0.8)}원</Col>
                                                            </Row>
                                                            
                                                        )
                                                        )
                                                        }                                                      
                                                    </Container>
                                                </Card.Body>
                                          
                                            </Card>
                                        </div>
                                     </Accordion.Body>
                                    
                                </Accordion.Item>
                            ))
                        }
                    </Accordion>
                </Card.Body>
            </Card>
        </>
    )
}

export default UsePlan;