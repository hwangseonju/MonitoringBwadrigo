import { useState } from 'react';
import {Button} from "react-bootstrap";

function Profile() {

    const [isLogin, setIsLogin] = useState(false);


    return(
        <>
            <div>
                

            {isLogin?
            <div>
                
                <h2>?님 런드리고와 함께 빨래없는 생활을 시작하세요.</h2>
                <Button variant="success" href="/">결제내역</Button>
                <Button variant="success" href="/">내정보</Button>
                <Button variant="success" href="/">이용중인 서비스</Button>
            </div>
            :
            <div>
                <h2> 런드리고와 함께 빨래없는 생활을 시작하세요.</h2>
                 <Button variant="success" href="/login">로그인</Button>{' '}
            </div>  
            }
            </div>

        </>
        
    )
}

export default Profile;