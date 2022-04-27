import { useState } from 'react';
import {Form, Button} from 'react-bootstrap';

function Application(){
    const [checkValue, setCheckValue] = useState([]);
    const changeHandler = (checked, id) => {
        if(checked){
            setCheckValue([...checkValue, id]);
            console.log(id,"체크");
        }
        else{
            setCheckValue(checkValue.filter(button => button!==id));
            console.log(id,"체크 해제");
        }
    }
    const test = () =>{
        console.log(checkValue);
    }
    return(
        <div>
            <h1>오늘밤 당신의 세탁물을 가지러 가겠다.</h1>
            <h2>수거/배송 주소</h2>
            <p>주소 ~~~</p>
            <p>요청사항</p>

            <h2>맡길 세탁물 선택하기</h2>
            <Form>
                <Form.Check type='checkbox' id='wash' label='생활빨래' 
                checked = {checkValue.includes('wash') ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,'wash');
                    }} 
                />
                <Form.Check type='checkbox' id='bedding' label='이불' 
                checked = {checkValue.includes('bedding') ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                /><Form.Check type='checkbox' id='shirts' label='와이셔츠' 
                checked = {checkValue.includes('shirts') ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                /><Form.Check type='checkbox' id='cleaning' label='드라이 클리닝' 
                checked = {checkValue.includes('cleaning') ? true : false} 
                onChange={e => {
                    changeHandler(e.currentTarget.checked,e.currentTarget.id);
                    }} 
                />
            </Form>

            <Button href='/applicationResult' variant='success' size='lg'>수거신청하기</Button>
            <Button onClick={test}>test</Button>
        </div>
    )
}

export default Application;