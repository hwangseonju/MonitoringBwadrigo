import axios from 'axios';
import { useState, useEffect, useCallback } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap';

function MyInfoModify() {
    const [member, setMember] = useState({
        "memberId":"1",
        "memberEmail":"test@test.com",
        "memberName":"test",
        "memberPhone":"010-1233-1223",
        "memberAddress":"강남구 역삼동 2",
        "memberGender":"",
        "memberAge":"",
        "memberStatus":"",
        "userRole":"",
    });
    const [memberPassword, setMemberPassword] = useState("")
    useEffect(()=>{
        axios.get(
        "/v1/api/member"
        )
        .then((res) =>{
        
            let memberDto = res.data
            setMember(memberDto);

        })

    },[])
    const pwdChange = (e) => {
        console.log(e.target.value)
        setMemberPassword(e.target.value)
    }
    const updateUser = useCallback(async(e)=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        await axios({
            method: "put",
            url : "/v1/api/member",
            data : {
                "memberEmail": member.memberEmail,
                "memberName": member.memberName,
                "memberPhone":member.memberPhone,
                "memberAddress": member.memberAddress,
                "memberPassword" : memberPassword
            },
            headers : {
                "Authorization" : Authorization,
                "RefreshToken" : RefreshToekn 
            }
        }).then((res) => {
            alert("회원정보 변경에 성공했습니다.")
            axios({
                method:"delete",
                url:"/v1/api/member/logout"
                
            }).then((res) => {
                localStorage.removeItem("memberName")
                window.location.href = "/login"
            })
        })
    })
    return (
        <div>
            <h3><Button href='/' variant="light">-</Button>내 정보 수정</h3>
            <h4>정보수정</h4>
            
            이메일
            <FormControl
            value={member.memberEmail}
            aria-label="memberEmail"
            aria-describedby="basic-addon1"
            disabled
            /><br />
            
            비밀번호
            <FormControl
            aria-label="memberPassword"
            aria-describedby="basic-addon1"
            onChange={pwdChange}
            value={memberPassword}
            /><br />
            
            비밀번호 확인
            <FormControl
            aria-label="memberPasswordCheck"
            aria-describedby="basic-addon1"
            /><br />
            
            이름
            <FormControl
            value={member.memberName}
            aria-label="memberName"
            aria-describedby="basic-addon1"
            disabled
            /><br />
            
            배송지<br />
            <FormControl
            value={member.memberAddress}
            aria-label="memberAddress"
            aria-describedby="basic-addon1"
            /><br/>
            
            <Button onClick={updateUser} variant="dark">변경사항 저장</Button>
            
        </div>
    )
}
export default MyInfoModify;