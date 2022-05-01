import { useState } from 'react';
import {Button} from "react-bootstrap";
import Footer from "./Footer.jsx";

function Profile() {

    const [isLogin, setIsLogin] = useState(false);
    localStorage.setItem("tab","/profile")

    return(
        <>
            <div>
                <h2> 런드리고와 함께 빨래없는 생활을 시작하세요.</h2>

                {isLogin?
            <div>
                
                <Button>이름받기</Button>
            </div>
            :
            <div>
                 <Button variant="success" href="/login">로그인</Button>{' '}
            </div>  
            }
            </div>
            
            <Footer/>
        </>
        
    )
}

export default Profile;