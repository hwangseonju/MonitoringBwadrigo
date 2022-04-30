import { useEffect, useState } from 'react';
import {Form, Button} from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
// import getAxios from '../../Api';
import axios from 'axios';

function Application(){
    const [checkValue, setCheckValue] = useState([]);
    const [addrress, setAddress] = useState();
    const [isAddress, setIsAddress] = useState(false);

    //const axios = getAxios();
    useEffect(() => {
        try{
            axios.get("/v1/api/member")
            .then((res)=>{
            console.log(res);
            if(res.data.memberAddress){
                setAddress(res.data.memberAddress);
                setIsAddress(true);
            }
        })
        }
        catch(err){
            console.log(err);
        }
    },[]);
    
    const changeHandler = (checked, id) => {
        if(checked){
            setCheckValue([...checkValue, {collecttype : id}]);
            console.log(id,"체크");
        }
        else{
            setCheckValue(checkValue.filter(e => !e.collecttype.includes(id)));
            console.log(id,"체크 해제");
        }
    }
    const test = () =>{
        console.log(checkValue);
    }
    const navigate = useNavigate();
    const submit = async () =>{
        try{
            await axios.post("/v1/api/order", checkValue);
            navigate("/applicationResult");
        }
        catch(err){
            console.log(err);
        }
    }

    return(
        <div>
            <h2>오늘밤 당신의 세탁물을 가지러 갑니다.</h2>
            <br />
            <h2>수거/배송 주소</h2>
            {isAddress?
            <div>
                <h2>{addrress}</h2>
                <Button variant='success'>주소 수정하러 가기!</Button>
            </div>
            :
            <div>
                <h2>입력된 주소가 없습니다.</h2>
                <Button variant='success'>주소 입력하러 가기!</Button>  
            </div>  
            }
            <p>요청사항</p>

            <h2>맡길 세탁물 선택하기</h2>
            <Form>
                <Form.Check type='checkbox' id='wash' label='생활빨래' 
                checked = {checkValue.filter((e)=>{return e.collecttype.includes('wash')}).length>0 ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                />
                <Form.Check type='checkbox' id='bedding' label='이불' 
                checked = {checkValue.filter((e)=>{return e.collecttype.includes('bedding')}).length>0 ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                /><Form.Check type='checkbox' id='shirts' label='와이셔츠' 
                checked = {checkValue.filter((e)=>{return e.collecttype.includes('shirts')}).length>0 ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                /><Form.Check type='checkbox' id='cleaning' label='드라이 클리닝' 
                checked = {checkValue.filter((e)=>{return e.collecttype.includes('cleaning')}).length>0 ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                />
            </Form>

            <Button onClick={submit} variant='success' size='lg'>수거신청하기</Button>
            <Button onClick={test}>test</Button>
        </div>
    )
}

export default Application;