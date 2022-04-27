import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';

function MyInfo() {
    return (
        <div>
            <h3><Button href='/' variant="light">-</Button>내 정보</h3>
            <hr></hr>
            <h4>기본정보<Button href='/' variant="light">정보수정</Button></h4> <br/>
            이메일<br/>
            이름<br/>
            휴대전화<br/>
            <Link to="/">회원탈퇴</Link>
        </div>
    )
    
}
export default MyInfo;