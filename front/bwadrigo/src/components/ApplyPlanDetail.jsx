import { useParams } from "react-router-dom";
import {Card,Button,ListGroup} from 'react-bootstrap';
function ApplyPlanDetail(){
    // 객체로 넘어가는게 아니라 id 값만 넘어가게 구현했습니다
    // detail 부분은 id값으로 axios로 가져오면 되지않을까요..ㅎㅎ......히희..
    const detail_id = useParams();
    const data={
        "one" : "data is one",
        "two" : "data is two",
        "theree" : "data is three"
    }
    return (
        <div>
            <div>{detail_id.applyid}</div>
            <Card className="text-center">
                <Card.Header> <div>드라이 온리 (상품 title)</div> </Card.Header>
                <Card.Body>
                    <Card.Title>가격</Card.Title>
                    <Card.Text>
                        <ListGroup variant="flush">
                            <div>상품구성</div>
                            <ListGroup.Item>{data.one}</ListGroup.Item>
                            <ListGroup.Item>{data.two}</ListGroup.Item>
                            <ListGroup.Item>{data.theree}</ListGroup.Item>
                        </ListGroup>
                    </Card.Text>
                </Card.Body>
                <Card.Footer><Button variant="primary">신청하기</Button></Card.Footer>
            </Card>
        </div>
    )
}

export default ApplyPlanDetail;