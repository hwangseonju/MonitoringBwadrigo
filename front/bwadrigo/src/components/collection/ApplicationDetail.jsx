import {Button} from 'react-bootstrap';

function ApplicationDetail(){
    return(
        <div>
            <h1>수거 신청 정보 ~~~~</h1>
            <Button href='/' variant='success' size='lg'>수거 취소하기</Button>
            <Button href='/applicationResult' variant='success' size='lg'>닫기</Button>
        </div>
        
    )
}

export default ApplicationDetail;