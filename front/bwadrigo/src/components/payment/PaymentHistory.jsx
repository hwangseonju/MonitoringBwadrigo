import {Button} from 'react-bootstrap';

function PaymentHistory() {
    return(
    <div>
        <h3><Button href='/' variant="light">-</Button>결제 내역</h3>
        <hr></hr>
            <Button variant="outline-secondary">1개월
            </Button> | <Button variant="outline-secondary">3개월
            </Button> | <Button variant="outline-secondary">6개월</Button>
            <hr></hr>
            일자 (ex: 2021년 11월 26일 (금)) <br/>
            서비스 이름 (ex: [월정액]드라이온리)
    </div>
    )
}
export default PaymentHistory