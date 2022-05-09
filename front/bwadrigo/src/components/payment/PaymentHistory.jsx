import axios from 'axios';
import { useCallback, useEffect, useState } from 'react';
import {Button, Accordion, Card, Row, Col, Container} from 'react-bootstrap';

function PaymentHistory() {
    const [payDto, setPayDto] = useState([])
    
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
        axios({
            method:"get",
            url : "/v1/api/plan"
        }).then((res)=>{
            console.log(res.data)
            setApplyDto(res.data)                
            console.log(applyDto)
            axios({
                method:"get",
                url:`/v1/api/plan/month/${res.data.monthPlanId}`
            }).then((response)=>{
                // startDate()
                // endDate()
                setMonthPlanDto(response.data)
            })
        }).catch((err)=>{
            alert("신청하신 월정액이 없습니다.")
            window.location.replace("/")
        })
    },[])
    const [mySet, setMySet] = useState([])
    const select = useCallback(async(e) =>{
        console.log(e.target.name)
        await axios.get(
                    `/v1/api/order/bill/${e.target.name}`
                ).then((res) => {
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

    })
    const getDate = (payDate) =>{
        // payDate = payDate.split("T")[0];
        payDate = payDate.split("-")
        return payDate[0]+"년"+payDate[1]+"월"+payDate[2]+"월"
    }
    return(
    <div>
        <h3><Button href='/' variant="light">-</Button>결제 내역</h3>
        <hr></hr>
            <Button variant="outline-secondary" name="1" onClick={select}>1개월
            </Button> | <Button variant="outline-secondary" name="3" onClick={select}>3개월
            </Button> | <Button variant="outline-secondary" name="5" onClick={select}>6개월</Button>
            <hr></hr>
            
            <Accordion alwaysOpen>
                        {
                            mySet.map((key)=>(
                                <Accordion.Item key={key} eventKey={key}>
                                    <Accordion.Header>{getDate(key)}</Accordion.Header>
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
                                                            <Row>
                                                            <Col>{pay.laundryPlanDto.laundryPlanDetails}</Col>
                                                            <Col>{pay.payReuestCount}</Col>
                                                            <Col style={{ textAlign: "right" }}>{pay.payRequestCount*(applyDto.monthPlanId==1?pay.laundryPlanDto.laundryPlanPrice : pay.laundryPlanDto.laundryPlanPrice*0.8)}원</Col>
                                                            </Row>
                                                        ))
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
    </div>
    )
}
export default PaymentHistory