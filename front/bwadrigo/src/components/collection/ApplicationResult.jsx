import {Button} from 'react-bootstrap';

function ApplicationResult(){
    return(
        <div>
            <h1>주문 접수 완료</h1>
            <Button href='/applicationDetail' variant='success'>자세히 보기</Button>
            <Button href='/' variant='success' size='lg'>취소</Button>
        </div>
    )
}

export default ApplicationResult;