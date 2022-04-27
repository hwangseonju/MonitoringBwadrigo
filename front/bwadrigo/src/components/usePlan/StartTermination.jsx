import React from "react";

function StartTermination() {
  const goSurvey = () => {
    window.location.href = "/survey-termination";
  };

  const goCancle = () => {
    window.location.href = "/member-plan";
  };

  return (
    <div>
      <h3>서비스 해지</h3>
      <div>
        <b>꼭 확인해주세요.</b>
      </div>
      <ul>
        <li>월정액 요금제에 포함된 미사용 품목은 환불되지않습니다.</li>
        <li>초과 사용 품목이 있는 경우, 해당 금액에 대한 결제가 진행됩니다.</li>
      </ul>
      <div>
        <button onClick={goCancle}>서비스 유지하기</button>
        <button onClick={goSurvey}>해지하기</button>
      </div>
    </div>
  );
}

export default StartTermination;
