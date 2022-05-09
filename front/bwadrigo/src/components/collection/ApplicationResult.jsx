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
        axios({
            method: "get",
            url : "/v1/api/member"
        }).then((res) =>{
            console.log("loginCheck")
            console.log(res.data)
            axios({
                method: "get",
                url : "/v1/api/plan"
            }).then((res)=>{
                console.log("serviceCheck")
                console.log(res.data == "")
                if(res.data == ""){
                    setCheckApply(true);
                    window.location.href="/pleaseService"
                }else{
                    axios({
                        method : "get",
                        url : "/v1/api/order/collect/check"
                    }).then((res)=>{
                        console.log(res)
                        // setCollect(true)
                        if(res.status == 204){
                            // setCheckService(false)
                            window.location.href = "/applicationInfo"
                        }
                        else
                            setCollectDtoList(res.data)
                    })
                }
            })    
        }).catch((err)=>{
            // console.log(err.response.status)
            if(err.response.status == 401){
                // axios({
                //     method:"get",
                //     url : "/v1/api/member/refresh"
                // }).then(()=>{
                //     window.location.replace("/applicationResult")
                // })
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