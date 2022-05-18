import axios from 'axios';
import { useState, useEffect, useCallback } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap';

function MyInfoModify() {
    const [member, setMember] = useState({
        memberEmail:"",
        memberName:"",
        memberPassword:"",
        memberAddress:""
    });
    // const [memberPassword, setMemberPassword, setMemberAddress] = useState("")
    const [modifiedPassword, setModifiedPassword] = useState(null)
    const [memberPassword, setMemberPassword] = useState("")

    useEffect(()=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        let url = "/v1/api/member"
        async function getMember(){
            
            await axios({
                method : "get",
                url : url,
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }
            }).then((res) => {
                console.log(res)
                setMember(res.data)
            }).catch((err)=>{
                console.log(err);
            }
            )
            // console.log(response)
        }
        getMember();
        // const result = getMeber();
        // setMember(result);
    },[])
    const pwdChange = (e) => {
        console.log(e.target.value)
        setMemberPassword(e.target.value)
    }
    const addressChange = (e) => {
        setMember({
            memberEmail: member.memberEmail,
            memberName: member.memberName,
            memberPassword:"",
            memberAddress: e.target.value})
    }

    const modifyPwd = (e) => {
        setModifiedPassword(e.target.value)
    }
    

    const updateUser = useCallback(async(e)=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        await axios({
            method: "put",
            url : "/v1/api/member",
            data : {
                "memberEmail": member.memberEmail,
                "modifiedPassword": modifiedPassword,
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
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                },
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
            
            변경 비밀번호
            <FormControl
            aria-label="memberPasswordCheck"
            aria-describedby="basic-addon1"
            onChange={modifyPwd}
            value={modifiedPassword}
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
            placeholder={member.memberAddress}
            aria-label="memberAddress"
            aria-describedby="basic-addon1"
            onChange={addressChange}
            value={member.memberAddress}
            /><br/>
            
            <Button onClick={updateUser} variant="dark">변경사항 저장</Button>
            
        </div>
    )
}
export default MyInfoModify;