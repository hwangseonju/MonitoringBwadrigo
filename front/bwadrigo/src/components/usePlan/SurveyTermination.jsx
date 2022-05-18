import axios from "axios";
import { useCallback, useState } from "react";
import { Button } from "react-bootstrap";
import surveyData from "../../surveyData.json";
import styles from "./UsePlan.css";

function SurveyTermination() {
  const goFinish = useCallback(async (e) => {
    let Authorization = localStorage.getItem("authorization");
    let RefreshToekn = localStorage.getItem("refreshtoken");
    await axios({
      method: "delete",
      url: "/v1/api/plan",
      data: reason,
      headers: {
        Authorization: Authorization,
        RefreshToken: RefreshToekn,
      },
    }).then((res) => {
      console.log(res);
      window.location.href = "/finish-termination";
    });
  });
  const [reason, setReason] = useState("");
  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  const changeReason = (e) => {
    console.log(e.target.value);
    setReason(e.target.value);
  };
  return (
    <div>
      <h3 className="text-center Title">서비스 해지</h3>
      <hr />
      <div className="full-content">
        <div>
          <b style={{ fontSize: "20px", paddingLeft: "10px" }}>서비스 해지 사유를 알려주세요.</b>
        </div>
        <br />
        <div style={{ paddingLeft: "20px" }}>
          {surveyData.reasons.map((item) => (
            <div key={item.id}>
              <label style={{ paddingBottom: "10px" }}>
                <input type="radio" name="survey" value={item.content} onChange={changeReason} />
                <span> {item.content}</span>
              </label>
            </div>
          ))}
        </div>
      </div>
      <div>
        <button className="btnPlan" onClick={goCancle}>
          해지 취소하기
        </button>
        <button className="btnCancle" onClick={goFinish}>
          즉시 해지하기
        </button>
      </div>
    </div>
  );
}

export default SurveyTermination;
