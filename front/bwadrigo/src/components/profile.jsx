import axios from 'axios';
import { useCallback, useState, useEffect } from 'react';
import {Button} from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

function Profile() {
    localStorage.setItem("tab", "/profile")

    const [isLogin, setIsLogin] = useState(true);
    const navigate = useNavigate();
    // const memberName = localStorage.getItem("memberName");
    const [member, setMember] = useState({
        memberName : localStorage.getItem("memberName")
    });

    function setCookie(token){
        document.cookie = token + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }


    const logout = useCallback(
        
        async(e) =>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        await axios({
            method:"delete",
            url:"/v1/api/member/logout",
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
        }
        getMember();
    },[])

    return(
        <>
            <div>
                

            {member.memberName?
            <div>
                
                <h2>{member.memberName}님 런드리고와 함께 빨래없는 생활을 시작하세요.</h2>
                <Button variant="success" href="/payHistory">결제내역</Button>
                <Button variant="success" href="/myInfo">내정보</Button>
                <Button variant="success" href="/member-plan">이용중인 서비스</Button>
                <Button variant="danger" onClick={logout}>로그아웃</Button>
            </div>
            :
            <div>
                <h2> 런드리고와 함께 빨래없는 생활을 시작하세요.</h2>
                 <Button variant="success" href="/login">로그인</Button>{' '}
            </div>  
            }
            </div>
            
        </>
        
    )
}

export default Profile;