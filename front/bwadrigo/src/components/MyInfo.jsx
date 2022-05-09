import { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';
import axios from 'axios';

function MyInfo() {
    const [member,setMember] = useState({
    memberEmail:"",
    memberName:"",
    memberPhone:"",
    memberAddress:""
})
    useEffect(()=>{
        axios.get(
        "/v1/api/member"
        )
        .then((res) =>{
        
            let memberDto = res.data
            setMember(memberDto);
        })

    },[])
    
    return (
        <div>
            <h3><Button href='/' variant="light">-</Button>내 정보</h3>
            <hr></hr>
            <h4>기본정보<Button href='/myinfomodify' variant="light">정보수정</Button></h4>
            이메일 {member.memberEmail }<br/>
            이름 {member.memberName}<br/>
            휴대전화{ member.memberPhone}<br />
            <hr></hr>
            <h4>배송 정보</h4>
            배송지 {member.memberAddress}<br />
            <Link to="/">회원탈퇴</Link>
        </div>
    )
    
}
export default MyInfo;