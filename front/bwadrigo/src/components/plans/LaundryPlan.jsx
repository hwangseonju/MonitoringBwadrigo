import {Accordion,Container,ListGroup, Row,Col,Badge,CloseButton} from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import "./LaundryPlan.css";
//요기는 피그마 맨위에 있는 세탁 가격표입니다 그냥전체보기로 해놧어용 상단에 type,detail, 가격써있고 누르면 설명이 나오게했습니다.!!!
//따로변경안할거면 data 배열에 axios로 가져오면 될거같아요..!
function LaundryPlan(){
    const data=
        [{"laundry_plan_type_kor":"생활빨래","laundry_plan_type_Eng":"wash","laundry_plan_details":"생활빨래3kg까지","laundry_plan_price":"8000원","laundry_plan_description":"생활빨래는 건조 어후 무게를 측정하여 무게 단위로 과금되며, 기본 금액 이후 초과하는 만큼 추가 과금됩니다.별도의 검수 없이 기계 새약 및 열품 건조 후, 다림질 없이 개어 보내드립니다.\n열풍 건조로 인해 소재에 따라 수축이 발생할 수 있으며, 고객 부주의로 인한 손상 시 보상이 불가하모니 물세탁 또는 열풍 건조가 불가능한 의류는 개별 클리닝으로 맡겨주셔야 합니다."},
        {"laundry_plan_type_kor":"개별클리닝","laundry_plan_type_Eng":"cleaning","laundry_plan_details":"캐시미어,알파카,라마,양모 코트","laundry_plan_price":"18000원","laundry_plan_description":"캐시미어, 알파카, 라마, 양모 소재 혼용을 30% 이상"},
        {"laundry_plan_type_kor":"개별클리닝","laundry_plan_type_Eng":"cleaning","laundry_plan_details":"프리미엄코트","laundry_plan_price":"22000원","laundry_plan_description":"[프리미엄 코트 브랜드 안내!\n프라다, 버버리, 보스, 펜디, 페라가모, 에르메스,발렌시아가,베르사체, 셀린느, 사넬,구찌, 알렉산더왕,막스마라,발렌티노,이자벨 마랑, 아크네스튜디오,크리스찬디올,지방시,생로랑,아르마니.돌체앤가바나,로샤스, 루이비통,쟈딕앤볼테르,로로피아나,랑방,마크제이콥스,마르니,겐조,비비안웨스트우드 등"},
        {"laundry_plan_type_kor":"개별클리닝","laundry_plan_type_Eng":"cleaning","laundry_plan_details":"인조가죽, 인조세무, 인조모피, 인조무스탕","laundry_plan_price":"15000원","laundry_plan_description":"해당 소재가 50% 이상으로 포함된 아우티"},
        {"laundry_plan_type_kor":"개별클리닝","laundry_plan_type_Eng":"cleaning","laundry_plan_details":"부분 천연 모피/가죽 코트, 점퍼","laundry_plan_price":"20000원","laundry_plan_description":"의류 일부에 모피, 가죽이 있는 경우\n카라 부터 밑단까지의 부분 모피, 내피 앞부 모피 등"},
        {"laundry_plan_type_kor":"개별클리닝","laundry_plan_type_Eng":"cleaning","laundry_plan_details":"기능성의류","laundry_plan_price":"9000원","laundry_plan_description":"등산용, 비람막이, 골프용 들 기능성 소재가 포함된 의류"},
        {"laundry_plan_type_kor":"이불","laundry_plan_type_Eng":"bedding","laundry_plan_details":"커버류, 이불패드","laundry_plan_price":"9000원","laundry_plan_description":"침대 커버, 매트리스 커버, 이불커버, 홑이불, 이불패드\n이불과 커버를 분리하여 세탁하므로 별도 과금됩니다."},
        {"laundry_plan_type_kor":"이불","laundry_plan_type_Eng":"bedding","laundry_plan_details":"일반 이불, 극세사 패드","laundry_plan_price":"12000원","laundry_plan_description":"홑이볼, 일반 이불, 극세사 패드\n이불과 커버를 분리하여 세탁하므로 별도 과금됩니다."},
        {"laundry_plan_type_kor":"이불","laundry_plan_type_Eng":"bedding","laundry_plan_details":"아동이불, 무릎 담요","laundry_plan_price":"7000원","laundry_plan_description":""},
        {"laundry_plan_type_kor":"배송비","laundry_plan_type_Eng":"delivery","laundry_plan_details":"배송비","laundry_plan_price":"3500원","laundry_plan_description":"수거 배송 횟수를 모두 사용하시는 경우 추가 이용하는 만큼 다음 결제일에 배송비가 추가 결제 됩니다."}];
    const navigate = useNavigate();
    const goBack=()=>{
        navigate("/");
    };
    return (
        <div className='laundry-plan-container'>
            <CloseButton className='close-btn' onClick={goBack} />
            <h1><Badge bg="success" className='plan-badge'>세탁 가격표</Badge></h1>
            <h5 className='plan-header'><b>세탁 안심 정찰 가격표</b></h5>
            <h5 className='plan-desc'> 안심정찰가격표 기준으로 세탁 완료 후 결제</h5>
     
            <br/>
            <br/>
            {data.map((rdata, index) =>(
                <span key={index}>
                    <Accordion>
                        <Accordion.Item className='item-box'>
                            <div>{rdata.laundry_plan_type_kor}</div>
                            <Accordion.Header>
                                <Container>
                                    <Row className="item-desc">
                                        <Col xs={7}>{rdata.laundry_plan_details}</Col>
                                        <Col></Col>
                                        <Col xs={4}>{rdata.laundry_plan_price} </Col>
                                    </Row>
                                </Container>
                            </Accordion.Header>
                            <Accordion.Body>
                                <ListGroup variant="flush">
                                    <ListGroup.Item>
                                        {rdata.laundry_plan_description}
                                    </ListGroup.Item>  
                                </ListGroup>
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </span>
            ))
            }
            
           
        </div>
    );
}

export default LaundryPlan;