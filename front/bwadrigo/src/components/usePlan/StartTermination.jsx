import { Button } from "react-bootstrap";
import styles from "./UsePlan.css";

function StartTermination() {
  const goSurvey = () => {
    window.location.href = "/survey-termination";
  };

  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  return (
    <div>
      <h3 className="text-center Title">서비스 해지</h3>
      <hr />
      <div className="full-content">
        <div>
          <b style={{ fontSize: "20px", paddingLeft: "10px" }}>꼭 확인해주세요.</b>
        </div>
        <br />
        <ul className="notice">
          <li>
            월정액 요금제에 포함된 미사용 품목은 환불되지
            <br />
            않습니다.
          </li>
          <li>
            초과 사용 품목이 있는 경우, 해당 금액에 대한
            <br />
            결제가 진행됩니다.
          </li>
        </ul>
      </div>
      <div>
        <button className="btnPlan" onClick={goCancle}>
          서비스 유지하기
        </button>
        <button className="btnCancle" onClick={goSurvey}>
          해지하기
        </button>
      </div>
    </div>
  );
}

export default StartTermination;
