import { render } from '@testing-library/react';
import { useState, useEffect } from 'react';
import { Table } from 'react-bootstrap';

function PaymentHistoryDetail() {
    const [paymentList, setPaymentList] = useState([
        {
        "payId": 2,
        "payRequestCount": "3",
        "payResponseDate": "0",
        "payPickDate": "0",
        "laundryPlanDto": {
            "laundryPlanId": "2",
            "laundryPlanType": "생활빨래",
            "laundryPlanDetails": "aa",
            "laundryPlanPrice": "3000",
            "laundryPlanDescription": ""
        }
        },
        {
            "payId": 3,
            "payRequestCount": "2",
            "payResponseDate": "0",
            "payPickDate": "0",
            "laundryPlanDto": {
                "laundryPlanId": "2",
                "laundryPlanType": "와이셔츠",
                "laundryPlanDetails": "bb",
                "laundryPlanPrice": "5000",
                "laundryPlanDescription": ""
            }
            },
    ]);
        return (
            <div>
                <h3>"사용자이름"님, <br />
                    서비스 이용 결제 금액은<br />
                    "가격"원입니다.</h3>
                결제일<br />
                이용 서비스 <br />
                
                <Table responsive="sm">
                    <thead>
                        <tr>
                            <td>이용항목</td>
                            <td>수량</td>
                            <td>정찰가격</td>
                        </tr>
                    </thead>
                    <tbody>
                    {paymentList.map((item) => (
                            <tr key="{item.payId}">
                            <td>{item.laundryPlanDto.laundryPlanType}</td>
                            <td>{item.payRequestCount}</td>
                            <td>{item.laundryPlanDto.laundryPlanPrice}</td>
                        </tr>
                        ))}
                    </tbody>
                    </Table>
                    
            </div>
        );
    }
export default PaymentHistoryDetail;