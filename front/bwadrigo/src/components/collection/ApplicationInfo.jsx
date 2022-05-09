import axios from 'axios';
import { async } from 'q';
import { useCallback, useState } from 'react';
import {Button, Row, Col, Container} from 'react-bootstrap';

function ApplicationInfo(){
    const memberName = useState(localStorage.getItem("memberName"))

    
    if(!localStorage.getItem("memberName")){
    
        window.location.href = "/pleaseLogin"

    }
    return(
        <Container>
            <Row><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /></Row>
            <Row>
                <Col></Col>
                <Col>
                    <Button style={{borderRadius : "50%", font : "10px"}} href='/application' variant='success' size='lg'>수거신청</Button>
                </Col>
                <Col></Col>
            </Row>
        </Container>
    )
    
}

export default ApplicationInfo;