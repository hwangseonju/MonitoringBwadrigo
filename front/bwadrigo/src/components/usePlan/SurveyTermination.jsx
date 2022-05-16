import axios from "axios";
import { useCallback, useState } from "react";
import { Button } from "react-bootstrap";
import surveyData from "../../surveyData.json";

function SurveyTermination() {
  const goFinish = useCallback(async(e) => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    await axios({
      method : "delete",
      url : "/v1/api/plan",
      data : reason,
      headers : {
        "Authorization" : Authorization,
        "RefreshToken" : RefreshToekn 
      }
    }).then((res)=>{
      console.log(res)
      window.location.href = "/finish-termination";
    })
  });
  const [reason, setReason] = useState("")
  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  const changeReason = (e) => {
    console.log(e.target.value);
    setReason(e.target.value)
  };
  return (
    <div>
      <h3>서비스 해지</h3>
      <div>
        <b>서비스 해지 사유를 알려주세요.</b>
      </div>
      <div>
        {surveyData.reasons.map((item) => (
          <div key={item.id}>
            <input type="radio" name="survey" value={item.content} onChange={changeReason} />
            <span> {item.content}</span>
          </div>
        ))}
      </div>
      <div>
        <Button variant="primary" onClick={goCancle}>
          해지 취소하기
        </Button>
        <Button variant="light" onClick={goFinish}>
          즉시 해지하기
        </Button>
      </div>
    </div>
  );
}

export default SurveyTermination;
