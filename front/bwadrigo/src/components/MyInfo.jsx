import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';

function MyInfo() {
    const [member, setMember] = useState({
    "memberId":"1",
    "memberEmail":"test@test.com",
    "memberPassword":"",
    "memberName":"test",
    "memberPhone":"010-1233-1223",
    "memberAddress":"강남구 역삼동 2",
    "memberGender":"",
    "memberAge":"",
    "memberStatus":"",
    "userRole":"",
});
    return (
        <div>
            <h3><Button href='/' variant="light">-</Button>내 정보</h3>
            <hr></hr>
            <h4>기본정보<Button href='/' variant="light">정보수정</Button></h4>
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