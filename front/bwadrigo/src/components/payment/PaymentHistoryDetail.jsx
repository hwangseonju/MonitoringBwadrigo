import {Table} from 'react-bootstrap';

function PaymentHistoryDetail() {
    return (
        <div>
            <h3>"사용자이름"님, <br/>
                서비스 이용 결제 금액은<br />
                "가격"원입니다.</h3>
            결제일<br/>
            이용 서비스 <br/>
            <Table responsive="sm">
            <thead>
                <tr>
                <td>이용항목</td>
                <td>수량</td>
                <td>정찰가격</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>생활빨래</td>
                    <td>3</td>
                    <td>원</td>
                </tr>
                <tr>
                    <td>와이셔츠</td>
                    <td>1</td>
                    <td>원</td>
                </tr>
                <tr>
                    <td>드라이클리닝</td>
                    <td>1</td>
                    <td>원</td>
                </tr>
                <tr>
                    <td>이불</td>
                    <td>0</td>
                    <td>원</td>
                </tr>
                <tr>
                    <td>수거배송</td>
                    <td>3</td>
                    <td>원</td>
                </tr>
                <tr>
                    <td>결제 금액</td>
                    <td></td>
                    <td>원</td>
                </tr>
            </tbody>
            </Table>
        </div>
    )
}
export default PaymentHistoryDetail