import { useNavigate, useParams } from "react-router-dom";
import { Card, Button, ListGroup, Table } from "react-bootstrap";
import MonthPlan from "../../month_plan_data.json";
import { useCallback, useState } from "react";
import { async } from "q";
import axios from "axios";
function ApplyPlanDetail() {
  // 객체로 넘어가는게 아니라 id 값만 넘어가게 구현했습니다
  // detail 부분은 id값으로 axios로 가져오면 되지않을까요..ㅎㅎ......히희..

  const detail_id = useParams();
  const [data, setDetail] = useState(MonthPlan[detail_id.applyid - 1]);
  // console.log(MonthPlan[detail_id.applyid-1])
  const navigate = useNavigate();
  const applyService = useCallback(async (e) => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    await axios({
      method: "post",
      url: "/v1/api/plan",
      data: {
        monthPlanId: detail_id.applyid,
      },
      headers : {
        "Authorization" : Authorization,
        "RefreshToken" : RefreshToekn 
    }
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
      {/* <div>{detail_id.applyid}</div> */}
      <Card className="text-center">
        <Card.Header> {data.month_plan_name} 요금제</Card.Header>
        <Card.Body>
          <Card.Title>가격 : {data.month_plan_price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")}\</Card.Title>
          <Card.Text>
            <ListGroup variant="flush">
              <div>상품구성</div>
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
          </Card.Text>
        </Card.Body>
        <Card.Footer>
          <Button variant="primary" onClick={applyService}>
            신청하기
          </Button>
        </Card.Footer>
      </Card>
    </div>
  );
}

export default ApplyPlanDetail;
