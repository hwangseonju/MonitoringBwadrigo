import { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';
import axios from 'axios';
import myinfomodify from "./MyInfoModify";
import "./MyInfo.css";

function MyInfo() {
    const goChange = () => {
        window.location.href = "/MyInfoModify";
    };
    const [member,setMember] = useState({
    memberEmail:"",
    memberName:"",
    memberPhone:"",
    memberAddress:""
})
    
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


    const deleteMember = useCallback(async() => {
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")

        await axios({
            method: "delete",
            url : "/v1/api/member",
            headers : {
                "Authorization" : Authorization,
                "RefreshToken" : RefreshToekn 
            }
        }).then((res) => {
            localStorage.removeItem("memberName")
            localStorage.removeItem("authorization");
            localStorage.removeItem("refreshtoken");
            window.location.href = "/"
        })
    })
    
    return (
        <div>
            
            <h3 className='text-center'>내 정보</h3>
            <hr />
            <h4><div className='Sub-title'>기본정보</div></h4>
            {/* <div style="width:200px; height:150px; border:1px solid red; float:left;">dd</div> */}
            <div className='Text-bold'>이메일 </div>
            <div className='Text-common'>{member.memberEmail}</div>
            <br/>
            <div className='Text-bold'>이름 </div>
            <div className='Text-common'>{member.memberName}</div>
            <br/>
            <div className='Text-bold'>휴대전화</div>
            <div className='Text-common'>{member.memberPhone}</div>
            <br/>
            <button onClick={goChange} className="Modi-btn">정보수정</button>
            <hr />
            <h4><div className='Sub-title'>배송 정보</div></h4>
            <div className='Text-bold'>배송지</div>
            <div className='Text-common'>{member.memberAddress}</div>
            <br/>
            <button onClick={deleteMember} className="Del-btn">회원탈퇴</button>
        </div>
    )
    
    
}

export default MyInfo;