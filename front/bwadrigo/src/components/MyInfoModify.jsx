import { useState, useEffect } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap';

function MyInfoModify() {
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
            <h3><Button href='/' variant="light">-</Button>내 정보 수정</h3>
            <h4>정보수정</h4>
            
            이메일
            <FormControl
            placeholder={member.memberEmail}
            aria-label="memberEmail"
            aria-describedby="basic-addon1"
            /><br />
            
            비밀번호
            <FormControl
            aria-label="memberPassword"
            aria-describedby="basic-addon1"
            /><br />
            
            비밀번호 확인
            <FormControl
            aria-label="memberPasswordCheck"
            aria-describedby="basic-addon1"
            /><br />
            
            이름
            <FormControl
            placeholder={member.memberName}
            aria-label="memberName"
            aria-describedby="basic-addon1"
            /><br />
            
            배송지<br />
            <FormControl
            placeholder={member.memberAddress}
            aria-label="memberAddress"
            aria-describedby="basic-addon1"
            /><br/>
            
            <Button href='/' variant="dark">변경사항 저장</Button>
            
        </div>
    )
}
export default MyInfoModify;