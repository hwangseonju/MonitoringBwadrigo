import { useState } from 'react';
import {Card,Button} from 'react-bootstrap';
import MonthPlan from "../../month_plan_data.json";
function ApplyPlan({applyplan}) {
  //data 의 리스트를 뿌려줌미다 applyplan은 커스텀 컴포넌트만들고 사실상 뿌려주는 부분은 applyplanList에요
  //data 배열에 월정액 리스트를 axios로 가져오고, return 두개중에 첫번쨰거의 applyplan.title, applyplan,text바꾸시면 될듯..합니다..!
  
    return (
      <div>
        <Card style={{width: '18rem'}}>
          <Card.Body>
            <Card.Title>{applyplan.month_plan_name} 요금제</Card.Title>
            <Card.Text>({applyplan.month_plan_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}원)</Card.Text>
            <Button variant="success" onClick={()=>{
              localStorage.setItem("selectMontPlan",applyplan.month_plan_id)
              document.location.href=`/applydetail/${applyplan.month_plan_id}`;
            }}>go</Button>
          </Card.Body>  
        </Card>
        <br/>
      </div>
      
    )
  }
  
function ApplyPlanList(){
  localStorage.setItem("tab", "/")
  const [monthPlan, setMonthPlan] = useState(
    MonthPlan
  )
  
  return(
    <div>
      {/* <h3> 월정액 서비스 </h3> */}
      <Button href="/laundryPlan">가격표</Button>
      <h5> 안심 정찰 가격보다 할인된 가격으로 이용하세요 </h5>
      {monthPlan.map(applyplan =>(
        <ApplyPlan applyplan={applyplan} key={applyplan.id}/>
      ))}
    </div>
  );
}
  export default ApplyPlanList;