import {Card,Button} from 'react-bootstrap';
function ApplyPlan({applyplan}) {
  //data 의 리스트를 뿌려줌미다 applyplan은 커스텀 컴포넌트만들고 사실상 뿌려주는 부분은 applyplanList에요
  //data 배열에 월정액 리스트를 axios로 가져오고, return 두개중에 첫번쨰거의 applyplan.title, applyplan,text바꾸시면 될듯..합니다..!
    return (
      <div>
        <Card style={{width: '18rem'}}>
          <Card.Body>
            <Card.Title>{applyplan.title}</Card.Title>
            <Card.Text>({applyplan.text})</Card.Text>
            <Button variant="success" onClick={()=>{
              document.location.href=`/applydetail/${applyplan.id}`;
            }}>go</Button>
          </Card.Body>  
        </Card>
        <br/>
      </div>
      
    )
  }
  
function ApplyPlanList(){
  const data = [
    {
      id:1,
      title: "First Title",
      text: "First Text",
    },
    {
      id:2,
      title: "second Title",
      text: "second Text",
    },
    {
      id:3,
      title: "third Title",
      text: "third Text",
    }
  ];
  return(
    <div>
      <h3> 월정액 서비스 </h3>
      <h5> 안심 정찰 가격보다 할인된 가격으로 이용하세요 </h5>
      {data.map(applyplan =>(
        <ApplyPlan applyplan={applyplan} key={applyplan.id}/>
      ))}
    </div>
  );
}
  export default ApplyPlanList;