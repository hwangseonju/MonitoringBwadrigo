import React, {useState, useEffect} from 'react'
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Form, Container, Row, Col, Button } from "react-bootstrap";
import "./signup.css";

function Signup() {

      // 유효성 체크에 관한 메시지, 컬러
      
    const [emailMsg, setEmailMsg] = useState("");
    const [emailMsgColor, setEmailMsgColor] = useState({ color: "black" });
    const [pwMsg, setPwMsg] = useState("");
    const [pwMsgColor, setPwMsgColor] = useState({ color: "black" });
    const [pwCheckMsg, setPwCheckMsg] = useState("");
    const [pwCheckMsgColor, setPwCheckMsgColor] = useState({ color: "black" });


    const [checkEmail, setCheckEmail] = useState(false);
    const [checkPw, setCheckPw] = useState(false);
    

    const [inputs, setInputs] = useState({
        memberEmail: "",
        memberPassword: "",
        passwordConfirmation: "",
        memberName: "",
        memberPhone: "",
        memberAddress: ""
    });

    const {memberEmail, memberPassword, passwordConfirmation, memberName, memberPhone, memberAddress} = inputs;

    const onChange = (e) => {
        const {value, name} = e.target;
        setInputs({
            ...inputs,
            [name]: value,
        });
    };
    

  useEffect(() => {
    setCheckEmail(false);
    setEmailMsg("");
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    const debounce = setTimeout(() => {
      if (memberEmail.length >= 1) {
        if (!emailRegex.test(memberEmail)) {
          setEmailMsg("올바른 이메일 형식이 아닙니다.");
          setEmailMsgColor({ color: "red" });
        } else {
          // 이메일 중복검사
          axios({
            method: "get",
            url: "/v1/api/member/check/" + memberEmail,
          })
            .then((res) => {
              setEmailMsg("사용하셔도 좋습니다.");
              setEmailMsgColor({ color: "blue" });
              setCheckEmail(true);
            })
            .catch((err) => {
              setEmailMsg("중복된 이메일입니다.");
              setEmailMsgColor({ color: "red" });
            });
        }
      }
    }, 700);
    return () => clearTimeout(debounce);
  }, [memberEmail]);

  useEffect(() => {
    setCheckPw(false);
    if (memberPassword.length === 0) {
      setPwMsg("");
      setPwCheckMsg("");
    }
    else if(passwordConfirmation.includes(" ")) {
      setPwMsg("공백을 포함할 수 없습니다.");
      setPwMsgColor({color: "red"});
    }
    else if(passwordConfirmation.length < 6) {
      setPwMsg("6자리 이상 입력해주세요.");
      setPwMsgColor({color:"red"});
      setPwCheckMsg("");
    }
    else{
      setPwMsg("사용하셔도 좋습니다.");
      setPwMsgColor({color:"blue"});
    }
    if (passwordConfirmation.length === 0) {
      setPwCheckMsg("");
    } else if (memberPassword === passwordConfirmation) {
      setPwCheckMsg("비밀번호가 일치합니다.");
      setPwCheckMsgColor({ color: "blue" });
      setCheckPw(true); // 유효성, 일치여부 확인 후 true
    } else {
      setPwCheckMsg("비밀번호가 일치하지 않습니다.");
      setPwCheckMsgColor({ color: "red" });
    }
  }, [memberPassword, passwordConfirmation]);

    // 회원가입 버튼 누를 시 실행
    const onSignup = () => {
      if (checkEmail && checkPw) {
        axios({
          method: "post",
          url: "/v1/api/member/signup",
          data: inputs,
        })
          .then((res) => {
            // console.log(res);
            alert("회원가입을 환영합니다.")
            window.location.href = "/login"
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        alert("다시 입력해주세요.");
      }
    };
  
    const onKeyPress = (e) => {
      if (e.key === "Enter") {
        onSignup();
      }
    };

    


  return (
    <div>
      <Container fluid="sm" style={{ width: "90%", maxWidth: "500px" }}>
      <br />
      <h1 className='signup_h'>회원가입</h1>
      <hr></hr>
      <br />
      <br />
      <h3>런드리고와 함께</h3>
      <h3>빨래 없는 생활을 시작해 볼까요?</h3>
      <Form>
        <Form.Group as={Row} className="mt-5 justify-content-center">
          <Col>
            <Form.Label>이메일</Form.Label>
            <Form.Control
              className="mb-3"
              type="email"
              placeholder="이메일"
              name="memberEmail"
              onChange={onChange}
              value={memberEmail}
              maxLength={30}
            ></Form.Control>
            <span style={emailMsgColor}>{emailMsg}</span>
            <Form.Label>비밀번호</Form.Label>
            <Form.Control
              className="mb-3"
              type="password"
              placeholder="비밀번호"
              name="memberPassword"
              onChange={onChange}
              value={memberPassword}
              maxLength={15}
            ></Form.Control>
            <span style={pwMsgColor}>{pwMsg}</span>
            <Form.Label>비밀번호 확인</Form.Label>
            <Form.Control
              className="mb-3"
              type="password"
              placeholder="비밀번호 확인"
              name="passwordConfirmation"
              onChange={onChange}
              value={passwordConfirmation}
              maxLength={15}
            ></Form.Control>
            <span style={pwCheckMsgColor}>{pwCheckMsg}</span>
            <Form.Label>이름</Form.Label>
            <Form.Control
              className="mb-3"
              placeholder="이름"
              name="memberName"
              onChange={onChange}
              value={memberName}
              maxLength={15}
            ></Form.Control>
            <Form.Label>전화번호</Form.Label>
            <Form.Control
              className="mb-3"
              placeholder="전화번호"
              name="memberPhone"
              onChange={onChange}
              value={memberPhone}
              maxLength={15}
              onKeyPress={onKeyPress}
            ></Form.Control>
            <Form.Label>주소</Form.Label>
            <Form.Control
              className="mb-3"
              placeholder="주소"
              name="memberAddress"
              onChange={onChange}
              value={memberAddress}
              maxLength={100}
              onKeyPress={onKeyPress}
            ></Form.Control>
            <div className="d-grid gap-2">
              <Button className='sigup_btn' onClick={onSignup} variant="secondary">
                회원가입
              </Button>
            </div>
          </Col>
        </Form.Group>
      </Form>
    </Container>

    </div>
  );
}

export default Signup;