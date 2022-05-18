import { useEffect, useState } from 'react';
import {Form, Button, Card} from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import "./Application.css";
// import getAxios from '../../Api';
import axios from 'axios';

function Application(){
    const [collectDto, setCheckValue] = useState([]);
    const [addrress, setAddress] = useState();
    const [isAddress, setIsAddress] = useState(false);

    //const axios = getAxios();
    useEffect(() => {
        try{
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
                    if(res.data.memberAddress){
                        setAddress(res.data.memberAddress);
                        setIsAddress(true);
                    }
                })
            // console.log(response)
            }   
            getMember();
        }
        catch(err){
            console.log(err);
        }
    },[]);
    
    const changeHandler = (checked, id) => {
        if(checked){
            setCheckValue([...collectDto, {collectType : id}]);
            console.log(id,"체크");
        }
        else{
            setCheckValue(collectDto.filter(e => !e.collectType.includes(id)));
            console.log(id,"체크 해제");
        }
    }

    const navigate = useNavigate();
    const submit = async () =>{
        let Authorization = localStorage.getItem("authorization")
        let RefreshToekn = localStorage.getItem("refreshtoken")
        let list = [];
        for(let val of collectDto){
            
            list.push(val.collectType);
        }
        console.log(list)
        try{
            await axios.post(
                "/v1/api/order",
                collectDto
                ,
                {
                headers : {
                    "Authorization" : Authorization,
                    "RefreshToken" : RefreshToekn 
                }    
            });
            navigate("/applicationResult");
        }
        catch(err){
            console.log(err);
        }
    }
    
    const editAddress=()=>{
        navigate("/myinfomodify")
    }

    return(
        <div>
            <br/>
            <h2>오늘밤 문앞의 세탁물을 수거하러 갑니다.</h2>
            <br />
            <h2>수거/배송 주소</h2>
            <Card className='address_box'>
                {isAddress?
                <div>
                    <h2>{addrress}</h2>
                    <Button variant='success' size='sm' className='button edit_address_btn' onClick={editAddress}>수정</Button>
                </div>
                :
                <div>
                    <h2>입력된 주소가 없습니다.</h2>
                    <Button variant='success' className='button'>주소 입력하러 가기!</Button>  
                </div>  
                }
            </Card>
            
            <br/>   
            <Card className='check_box'>    
                <h2>맡길 세탁물 선택하기</h2>
                <Form>
                    <Form.Check type='checkbox' id='wash' label='생활빨래' 
                    checked = {collectDto.filter((e)=>{return e.collectType.includes('wash')}).length>0 ? true : false} 
                    onChange={e => {
                        changeHandler(e.currentTarget.checked,e.currentTarget.id);
                        }} 
                    />
                    <Form.Check type='checkbox' id='bedding' label='이불' 
                    checked = {collectDto.filter((e)=>{return e.collectType.includes('bedding')}).length>0 ? true : false} 
                    onChange={e => {
                        changeHandler(e.currentTarget.checked,e.currentTarget.id);
                        }} 
                    /><Form.Check type='checkbox' id='shirts' label='와이셔츠' 
                    checked = {collectDto.filter((e)=>{return e.collectType.includes('shirts')}).length>0 ? true : false} 
                    onChange={e => {
                        changeHandler(e.currentTarget.checked,e.currentTarget.id);
                        }} 
                    /><Form.Check type='checkbox' id='cleaning' label='드라이 클리닝' 
                    checked = {collectDto.filter((e)=>{return e.collectType.includes('cleaning')}).length>0 ? true : false} 
                    onChange={e => {
                        changeHandler(e.currentTarget.checked,e.currentTarget.id);
                        }} 
                    />
                </Form>
            </Card>        
            <div className='d-grid gap-2'>
                <Button onClick={submit} variant='success' size='lg' className='mt-4 mb-4 button'>수거신청하기</Button>
            </div>
            
        </div>
    )
}

export default Application;