import React from "react";
import surveyData from "../../surveyData.json";

function SurveyTermination() {
  const goFinish = () => {
    window.location.href = "/finish-termination";
  };

  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  const changeReason = (e) => {
    console.log(e.target.value);
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
            <input type="radio" name="survey" value={item.id} onChange={changeReason} />
            <span> {item.content}</span>
          </div>
        ))}
      </div>
      <div>
        <button onClick={goCancle}>해지 취소하기</button>
        <button onClick={goFinish}>즉시 해지하기</button>
      </div>
    </div>
  );
}

export default SurveyTermination;
