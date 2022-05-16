import axios from 'axios';
import { async } from 'q';
import { useCallback, useEffect, useState } from 'react';
import {Button} from 'react-bootstrap';

function ApplicationResult(){

    const memberName = useState(localStorage.getItem("memberName"))
    const [checkLogin, setCheckLogin] = useState(false)
    const [checkApply, setCheckApply] = useState(false)
    const [checkServcie, setCheckService] = useState(false)
    const [collectDtoList, setCollectDtoList] = useState(
        [
            {   
                collectId : 0,
                collecttype : "wash",
                collectRequestDate : "2022-05-03 09:26:30.000000",
                collectWithdrawDate : null
            }
        ]
    )
    useEffect(()=>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        async function getResult(){
            axios({
                method: "get",
                url : "/v1/api/member",
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }
            }).then((res) =>{
                console.log("loginCheck")
                console.log(res.data)
                axios({
                    method: "get",
                    url : "/v1/api/plan",
                    headers : {
                        "Authorization" : Authorization,
                        "RefreshToken" : RefreshToekn 
                    }
                }).then((res)=>{
                    console.log("serviceCheck")
                    console.log(res.data == "")
                    if(res.data == ""){
                        setCheckApply(true);
                        window.location.href="/pleaseService"
                    }else{
                        axios({
                            method : "get",
                            url : "/v1/api/order/collect/check",
                            headers : {
                                "Authorization" : Authorization,
                                "RefreshToken" : RefreshToekn 
                            }
                        }).then((res)=>{
                            console.log(res)
                            if(res.status == 204){
                                window.location.href = "/applicationInfo"
                            }
                            else
                                setCollectDtoList(res.data)
                        })
                    }
                })    
            }).catch((err)=>{
                if(err.response.status == 401){
                    localStorage.removeItem("memberName")
                    localStorage.removeItem("authorization");
                    localStorage.removeItem("refreshtoken");
                    window.location.replace("/pleaseLogin")
                }else{
                    axios({
                        method: "delete",
                        url:"/v1/api/member/logout"
                    }).then(()=>{
                        window.location.href="/pleaseLogin"
                        
                    })
                }
            })
        }
        getResult();
    },[])
        
   
    return(
        <div>
            <h1>주문 접수 완료</h1>
            <Button href='/applicationDetail' variant='success'>자세히 보기</Button>
            <Button href='/' variant='success' size='lg'>취소</Button>
        </div>
    )
}

export default ApplicationResult;