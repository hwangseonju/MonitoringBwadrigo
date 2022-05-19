import axios from 'axios';
import React, { useState } from 'react';
import "./login.css";

import {
    Form,
    Button,
    Container
} from "react-bootstrap"
import { useNavigate } from 'react-router-dom';

function Login() {
    localStorage.setItem("tab","/profile")
    const [input, setInput] = useState({
        memberEmail: localStorage.getItem("memberEmail") ? localStorage.getItem("memberEmail") : "",
        memberPassword:""
    });
    const [check, setCheck] = useState(
        localStorage.getItem("memberEmail") ? true : false
    );
    const checkChange = (e) => {
        const {checked} = e.target
        if(checked){
            localStorage.setItem("memberEmail",input.memberEmail)
            setCheck(true)
        }else{
            localStorage.removeItem("memberEmail")
            setCheck(false)
        }
        
    }
    const onChange = (e) => {
        const { value, name } = e.target;
        setInput({
          ...input,
          [name]: value,
        });
        // console.log(input);
      };
      const navigate = useNavigate();
    const submit = async() => {
        await axios({
            method : "post",
            url : "/v1/api/auth",
            data : input
        
        }).then((res) => {
            console.log(res)
            if(check){
                localStorage.setItem("memberEmail",input.memberEmail)
            }else{
                localStorage.removeItem("memberEmail")
            }
            localStorage.setItem("memberName",res.data);
            localStorage.setItem("authorization", res.headers.authorization)
            localStorage.setItem("refreshtoken", res.headers.refreshtoken)
            navigate(localStorage.getItem("tab"));
        }).catch(e => {
            alert("이메일 혹은 비밀번호를 확인 하세요.")
        })

        
    }
    const onKeyPress = (e) => {
        if (e.key === "Enter") {
            submit();
        }
    };
    return (
        <Container>
            <div>
                <div className='login_div'></div>
                <h3>로그인</h3>
                <div className='login_div'></div>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control name='memberEmail' onChange={onChange} type="email" placeholder="이메일을 입력해주세요" value={input.memberEmail}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control name='memberPassword' onChange={onChange} onKeyPress={onKeyPress} type="password" placeholder="비밀번호를 입력해주세요" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" checked={check} onChange={checkChange} label="이메일 저장" />
                </Form.Group>
                <div>
                    <Button className='login_btn' onClick={submit} type="submit">
                        <b>
                            로그인
                        </b>
                    </Button>
                    <Button
                        className="btn-round"
                        href="/signup"
                        >
                        <b>
                            회원가입
                        </b>
                    </Button>
                </div>
            </div>
        </Container>
        
    )
    
}

export default Login;
