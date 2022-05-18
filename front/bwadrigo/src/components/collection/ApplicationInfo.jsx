import axios from 'axios';
import { async } from 'q';
import { useCallback, useState } from 'react';
import {Card,Button, Row, Col, Container} from 'react-bootstrap';
import "./ApplicationInfo.css";

function ApplicationInfo(){
    const memberName = useState(localStorage.getItem("memberName"))

    
    if(!localStorage.getItem("memberName")){
    
        window.location.href = "/pleaseLogin"

    }
    return(
        <div className='application_container'>
            <Card className='result'>
                <h2><b>오늘 밤 빨래하기</b></h2>
                <div className='desc'>밤 10시 전 수거 신청하면 <br/>하루만에 문 앞으로 배송완료!</div>
                <Button href='/application' variant='success' className='button application_btn'>수거신청</Button>
            </Card>  
        </div>
    )
    
}

export default ApplicationInfo;