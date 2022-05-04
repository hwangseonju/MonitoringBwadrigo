import { useState, useEffect } from 'react';
import {Button} from 'react-bootstrap';
import axios from 'axios';

function ApplicationDetail(){
    const [address, setAddress] = useState("test");

    useEffect(()=>{
        axios.get("/v1/api/member")
        .then((res)=>{
            console.log(res);
            if(res.data.memberAddress){
                setAddress(res.data.memberAddress);
            }
        })
    },[]);
    return(
        <div>
            <h1>수거 신청 정보</h1>
            <h2>수거/배송 주소</h2>
            <p>{address}</p>
            <Button href='/' variant='success' size='lg'>수거 취소하기</Button>
            <Button href='/applicationResult' variant='success' size='lg'>닫기</Button>
        </div>
        
    )
}

export default ApplicationDetail;