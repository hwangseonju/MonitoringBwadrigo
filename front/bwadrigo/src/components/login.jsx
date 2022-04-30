import axios from 'axios';
import React, { useState } from 'react';

import {
    Form,
    Button
} from "react-bootstrap"
import { useNavigate } from 'react-router-dom';

function Login() {
    const [input, setInput] = useState({
        memberEmail:"",
        memberPassword:""
    });
    const onChange = (e) => {
        const { value, name } = e.target;
        setInput({
          ...input,
          [name]: value,
        });
        console.log(input);
      };
      const navigate = useNavigate();
    const submit = () => {
        axios.post("/v1/api/member/login",input);
        navigate("/");
    }
    return (
        <div>
            <h2>로그인</h2>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control name='memberEmail' onChange={onChange} type="email" placeholder="Enter email" />
                <Form.Text className="text-muted">
                We'll never share your email with anyone else.
                </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control name='memberPassword' onChange={onChange} type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out" />
                </Form.Group>
                <Button onClick={submit} variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
            <div className="mt-3">
                          <Button
                           block
                           className="btn-round"
                           color="primary"
                           size="lg"
                           type="button"
                           href="/signup"
                          >
                          회원가입
                          </Button>
                        </div>
        </div>
        
    )
    
}

export default Login;
