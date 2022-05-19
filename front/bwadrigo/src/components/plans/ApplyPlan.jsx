import { useState } from "react";
import { Card, Button, Carousel } from "react-bootstrap";
import MonthPlan from "../../month_plan_data.json";
import styles from "./Plans.css";
import "./ApplyPlan.css";

const goPlanList = () => {
  window.location.href = "/laundryPlan";
};

function WashImg({ applyplan }) {
  if (applyplan.month_plan_id == 1) {
    return <img className="clothes" src={require("../../img/plans/normal.png")} />;
  } else if (applyplan.month_plan_id == 2) {
    return <img className="clothes" src={require("../../img/plans/shirts.png")} />;
  } else if (applyplan.month_plan_id == 3) {
    return <img className="clothes" src={require("../../img/plans/dry.png")} />;
  } else if (applyplan.month_plan_id == 4) {
    return <img className="clothes" src={require("../../img/plans/tshirts.png")} />;
  } else if (applyplan.month_plan_id == 5) {
    return <img className="clothes" src={require("../../img/plans/clothes.png")} />;
  } else if (applyplan.month_plan_id == 6) {
    return <img className="clothes" src={require("../../img/plans/all.png")} />;
  } else if (applyplan.month_plan_id == 7) {
    return <img className="clothes" src={require("../../img/plans/bedding.png")} />;
  }
}

function ApplyPlan({ applyplan }) {
  //data 의 리스트를 뿌려줌미다 applyplan은 커스텀 컴포넌트만들고 사실상 뿌려주는 부분은 applyplanList에요
  //data 배열에 월정액 리스트를 axios로 가져오고, return 두개중에 첫번쨰거의 applyplan.title, applyplan,text바꾸시면 될듯..합니다..!

  return (
    <Card
      key={applyplan.month_plan_id}
      className="planBlock"
      onClick={() => {
        localStorage.setItem("selectMontPlan", applyplan.month_plan_id);
        document.location.href = `/applydetail/${applyplan.month_plan_id}`;
      }}
    >
      <Card.Text style={{ padding: "5px", float: "left", width: "100%" }}>
        <div className="left-box">
          <div className="left-box1">{applyplan.month_plan_name} 요금제</div>
          <br />
          <div className="left-box2">
            <b>{applyplan.month_plan_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}원</b>
          </div>
        </div>
        <WashImg applyplan={applyplan} />
      </Card.Text>
    </Card>
  );
}

function ApplyPlanList() {
  localStorage.setItem("tab", "/");
  const [monthPlan, setMonthPlan] = useState(MonthPlan);

  return (
    <div className="home-container">
      <div className="banner">
        <Carousel className="mainBanner">
          <Carousel.Item interval={1000} className="main1"></Carousel.Item>
          <Carousel.Item interval={1000} className="main2"></Carousel.Item>
          <Carousel.Item interval={1000} className="main3"></Carousel.Item>
        </Carousel>
        <div className="planTable">
          <button className="btnPrice" onClick={goPlanList}>
            <b>가격표</b>
          </button>
        </div>
      </div>

      <div>
        <br />
        <div style={{ paddingLeft: "10px" }}>
          <h3 style={{ fontSize: "20px" }}>월정액 서비스</h3>
          <span style={{ fontSize: "12px" }}>
            <b> 안심 정찰 가격</b>보다 할인된 가격으로 이용하세요.
          </span>
        </div>
        <br />
        {monthPlan.map((applyplan) => (
          <ApplyPlan applyplan={applyplan} />
        ))}
      </div>
    </div>
  );
}
export default ApplyPlanList;
