import { useNavigate, useParams } from "react-router-dom";
import { Card, Button, ListGroup, Table } from "react-bootstrap";
import MonthPlan from "../../month_plan_data.json";
import { useCallback, useState } from "react";
import { async } from "q";
import axios from "axios";
import styles from "./Plans.css";
function WashImg({ applyplan }) {
  if (applyplan == 1) {
    return <img className="detailsImg" src={require("../../img/plans/normal.png")} />;
  } else if (applyplan == 2) {
    return <img className="detailsImg" src={require("../../img/plans/shirts.png")} />;
  } else if (applyplan == 3) {
    return <img className="detailsImg" src={require("../../img/plans/fulldry.png")} />;
  } else if (applyplan == 4) {
    return <img className="detailsImg" src={require("../../img/plans/tshirts.png")} />;
  } else if (applyplan == 5) {
    return <img className="detailsImg" src={require("../../img/plans/allclothes.png")} />;
  } else if (applyplan == 6) {
    return <img className="detailsImg" src={require("../../img/plans/all.png")} />;
  } else if (applyplan == 7) {
    return <img className="detailsImg" src={require("../../img/plans/bedding.png")} />;
  }
}

function ApplyPlanDetail() {
  // 객체로 넘어가는게 아니라 id 값만 넘어가게 구현했습니다
  // detail 부분은 id값으로 axios로 가져오면 되지않을까요..ㅎㅎ......히희..

  const detail_id = useParams();
  const [data, setDetail] = useState(MonthPlan[detail_id.applyid - 1]);
  // console.log(MonthPlan[detail_id.applyid-1])
  const navigate = useNavigate();
  const applyService = useCallback(async (e) => {
    let Authorization = localStorage.getItem("authorization");
    let RefreshToekn = localStorage.getItem("refreshtoken");
    await axios({
      method: "post",
      url: "/v1/api/plan",
      data: {
        monthPlanId: detail_id.applyid,
      },
      headers: {
        Authorization: Authorization,
        RefreshToken: RefreshToekn,
      },
    })
      .then((res) => {
        console.log(res);
        alert("요금제 신청이 성공했습니다.");
        window.location.href = "/";
      })
      .catch((e) => {
        if (e.response.status == 401) {
          alert("로그인 후 이용가능합니다.");
          navigate("/login");
        }
        if (e.response.status == 400) {
          if (window.confirm("이미 사용중인 요금제가 있습니다. 변경신청을 하시겠습니까?")) {
            axios({
              method: "put",
              url: "/v1/api/plan",
              data: {
                monthPlanId: detail_id.applyid,
              },
            }).then((res) => {
              alert("변경요청이 완료 되었습니다.");
              navigate("/");
            });
          }
        }
      });
  });
  return (
    <div>
      <h3 className="text-center Title">{data.month_plan_name} 요금제</h3>
      <br />
      <div className="full-content">
        <div className="allImg">
          <WashImg applyplan={data.month_plan_id} />
        </div>
        <br />
        <div className="detailsPrice">
          <b>{data.month_plan_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")} 원</b>
          <span style={{ fontSize: "10px" }}> / 4주</span>
        </div>
        <br />
        <div className="detailsCount">
          <ListGroup variant="flush">
            <div style={{ fontSize: "20px", marginBottom: "10px" }}>
              <b>상품구성</b>
            </div>
            <Table style={{ textAlign: "center", fontSize: "12px" }}>
              <thead>
                <tr>
                  {data.month_plan_wash_count !== 0 ? <th>생활빨래</th> : <th style={{ color: "#cfcfcf" }}>생활빨래</th>}
                  {data.month_plan_shirt_count !== 0 ? <th>와이셔츠</th> : <th style={{ color: "#cfcfcf" }}>와이셔츠</th>}
                  {data.month_plan_cleaning_count !== 0 ? <th>개별클리닝</th> : <th style={{ color: "#cfcfcf" }}>개별클리닝</th>}
                  {data.month_plan_bedding_count !== 0 ? <th>이불</th> : <th style={{ color: "#cfcfcf" }}>이불</th>}
                  {data.month_plan_delivery_count !== 0 ? <th>수거배송</th> : <th style={{ color: "#cfcfcf" }}>수거배송</th>}
                </tr>
              </thead>
              <tbody>
                <tr>
                  {data.month_plan_wash_count !== 0 ? <td>{data.month_plan_wash_count}개</td> : <td style={{ color: "#cfcfcf" }}>0개</td>}
                  {data.month_plan_shirt_count !== 0 ? <td>{data.month_plan_shirt_count}장</td> : <td style={{ color: "#cfcfcf" }}>0장</td>}
                  {data.month_plan_cleaning_count !== 0 ? <td>{data.month_plan_cleaning_count}장</td> : <td style={{ color: "#cfcfcf" }}>0장</td>}
                  {data.month_plan_bedding_count !== 0 ? <td>{data.month_plan_bedding_count}개</td> : <td style={{ color: "#cfcfcf" }}>0개</td>}
                  {data.month_plan_delivery_count !== 0 ? <td>{data.month_plan_delivery_count}회</td> : <td style={{ color: "#cfcfcf" }}>0회</td>}
                </tr>
              </tbody>
            </Table>
          </ListGroup>
        </div>
        <br />
        <div className="text-center">
          <button className="btnApply" onClick={applyService}>
            신청하기
          </button>
        </div>
      </div>
    </div>
  );
}

export default ApplyPlanDetail;
