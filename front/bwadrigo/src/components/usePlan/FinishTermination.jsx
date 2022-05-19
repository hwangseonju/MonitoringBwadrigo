import { Button } from "react-bootstrap";
import styles from "./UsePlan.css";

function FinishTermination() {
  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  return (
    <div>
      <h3 className="text-center Title">서비스 해지 완료</h3>
      <hr />
      <div className="full-content">
        <div style={{ fontSize: "20px", paddingLeft: "10px" }}>
          <b>
            서비스 해지가
            <br />
            완료되었습니다.
          </b>
        </div>
        <br />
        <div style={{ paddingLeft: "10px" }}>언제든지 다시 서비스를 이용하실 수 있도록</div>
        <div style={{ paddingLeft: "10px" }}>고객 경험 향상을 위해 노력하겠습니다.</div>
      </div>
      <div className="text-center">
        <button className="btnCheck" onClick={goCancle}>
          확인
        </button>
      </div>
    </div>
  );
}

export default FinishTermination;
