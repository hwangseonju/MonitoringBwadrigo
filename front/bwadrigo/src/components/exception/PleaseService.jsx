import Footer from "../Footer";
import {Card, Button} from "react-bootstrap";
function PleaseLogin(){

    localStorage.setItem("tab","/profile")

    return(

        <>
        <Card className="text-center">
            <Card.Header>서비스 가입이 필요합니다.</Card.Header>
            <Card.Body>
                <Card.Title>가입된 서비스가 없습니다.</Card.Title>
                <Card.Text>
                빨래없는 생활을 시작해보세요.
                </Card.Text>
                <Button variant="primary">서비스 둘러보기</Button>
            </Card.Body>
            {/* <Card.Footer className="text-muted">2 days ago</Card.Footer> */}
            <Footer/>
        </Card>
        </>

    )
}

export default PleaseLogin;