import Footer from "../Footer";
import {Card, Button} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
function PleaseLogin(){
    const navigate = useNavigate();
    const login = () => {
        navigate("/login")
    }
    return(
        
        <>
        <Card className="text-center">
            <Card.Header>로그인이 필요한 서비스입니다.</Card.Header>
            <Card.Body>
                <Card.Title>로그인을 해주세요.</Card.Title>
                <Card.Text>
                빨래없는 생활을 시작해보세요.
                </Card.Text>
                <Button variant="primary" onClick={login}>로그인 하러가기</Button>
            </Card.Body>
            {/* <Card.Footer className="text-muted">2 days ago</Card.Footer> */}
        </Card>
        
        </>

    )
}

export default PleaseLogin;