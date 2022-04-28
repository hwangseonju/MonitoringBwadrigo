import React, {useState, useEffect} from 'react'

function Signup() {

      // 유효성 체크에 관한 메시지, 컬러
      
    const [emailMsg, setEmailMsg] = useState("");
    const [emailMsgColor, setEmailMsgColor] = useState({ color: "black" });
    const [pwMsg, setPwMsg] = useState("");
    const [pwMsgColor, setPwMsgColor] = useState({ color: "black" });
    const [pwCheckMsg, setPwCheckMsg] = useState("");
    const [pwCheckMsgColor, setPwCheckMsgColor] = useState({ color: "black" });
    const [nicknameMsg, setNicknameMsg] = useState("");
    const [nicknameMsgColor, setNicknameMsgColor] = useState({ color: "black" });


    const [checkEmail, setCheckEmail] = useState(false);
    const [checkPw, setCheckPw] = useState(false);
    

    const [inputs, setInputs] = useState({
        memberemail: "",
        memberPassword: "",
        passwordConfirmation: "",
        memberName: "",
        memberPhone: ""
    });

    const {memberemail, memberPassword, passwordConfirmation, memberName, memberPhone} = inputs;

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
      if (email.length >= 1) {
        if (!emailRegex.test(email)) {
          setEmailMsg("올바른 이메일 형식이 아닙니다.");
          setEmailMsgColor({ color: "red" });
        } else {
          // 이메일 중복검사
          axios({
            method: "get",
            url: "/v1/api/member/check/" + email,
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
  }, [email]);

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

  })


  return (
    <div class="loginregister">
      <form>
      <div><input name="email" type="email" placeholder="이메일" value={memberEmail} onChange={onEmailHandler} class="loginregister__input"/></div>
          <div><input name="password" type="password" placeholder="비밀번호" value={memberpassword} onChange={onPasswordHandler} class="loginregister__input"/></div>
          <div><input name="confirmPassword" type="password" placeholder="비밀번호 확인" value={confirmPassword} onChange={onConfirmPasswordHandler} class="loginregister__input"/></div>
          <div><input name="name" type="text" placeholder="이름" value={memberName} onChange={onNameHandler} class="loginregister__input"/></div>
          <div><input name="phone" type="text" placeholder="전화번호" value={memberPhone} onChange={onPhoneHandler} class="loginregister__input"/></div>
          <div><button type="submit" onSubmit={onSubmit} class="loginregister__button">계정 생성하기</button></div>
      </form>
    </div>
  );
}

export default Signup;