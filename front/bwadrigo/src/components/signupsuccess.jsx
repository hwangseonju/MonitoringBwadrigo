import { Button } from 'react-bootstrap';
import { useState } from 'react';



function Signupsuccess() {
    const [memberName, setMemberName] = useState("안카자");

    return(

        <div>
            <h2>반갑습니다, {memberName}님 회원가입이 정상적으로 처리되었습니다.</h2>

            <Button href="/profile">확인</Button>
        </div>
        
    )
}


export default Signupsuccess;