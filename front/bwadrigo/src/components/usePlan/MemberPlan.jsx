import React from "react";

function MemberPlan() {
  //const title = "이용 중인 서비스";
  const goTermination = () => {
    window.location.href = "/start-termination";
  };
  return (
    <div>
      <div>
        <h3>이용 중인 서비스</h3>
        <div>
          <span>
            <b>신청 서비스 : </b>
          </span>
          <span>1회이용서비스</span>
        </div>
        <div>
          <span>
            <b>신청일 : </b>
          </span>
          <span>2022년 04월 26일(화)</span>
        </div>
        <div>
          <span>
            <b>이용상태 : </b>
          </span>
          <span>이용중</span>
        </div>
        <div>
          <span>
            <b>세탁요금 : </b>
          </span>
          <span>안심 정찰 가격표에 의해 요금 부과</span>
        </div>
        <div>
          <a href="@">안심 정찰 가격표 보기</a>
        </div>
      </div>
      <br />

      <div>
        <button>변경</button>
        <button onClick={goTermination}>해지</button>
      </div>

      <br />

      <div>
        <h3>신청 서비스</h3>
        <div>
          <span>
            <b>배송지 : </b>
          </span>
          <span>서울특별시 강남구 어쩌구</span>
        </div>
        <div>
          <span>
            <b>연락처 : </b>
          </span>
          <span>010-1234-1234</span>
        </div>
        <div>
          <span>
            <b>결제정보 : </b>
          </span>
          <span>국민</span>
        </div>
        <div>
          <span>
            <b>공동현관 출입 방법 : </b>
          </span>
          <span>공동현관 비밀번호</span>
        </div>
      </div>
    </div>
  );
}

export default MemberPlan;
