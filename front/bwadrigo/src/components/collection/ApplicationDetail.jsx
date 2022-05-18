import { useState, useEffect, useCallback } from "react";
import { Button, Card, ListGroup } from "react-bootstrap";
import "./ApplicationDetail.css";
import axios from "axios";

function ApplicationDetail() {
  const [address, setAddress] = useState("test");
  useEffect(() => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    async function getMember(){
    axios({
      method:"get",
      url: "/v1/api/member",
      headers : {
        "Authorization" : Authorization,
        "RefreshToken" : RefreshToekn 
      }
    }).then((res) => {
      console.log(res);
      if (res.data.memberAddress) {
        setAddress(res.data.memberAddress);
      }
    });
    }
    getMember();
  }, []);
  const [collectList, setCollectList] = useState([]);
  useEffect(() => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    async function collectCheck(){
      axios({
        method : "get",
        url : "/v1/api/order/collect/check",
        headers : {
          "Authorization" : Authorization,
          "RefreshToken" : RefreshToekn 
        }
      }).then((res) => {
        if (res.status == 204) {
          window.location.replace("/applicationResult");
        }
        console.log(res);
        let collectList = res.data;
        for (let i = 0; i < collectList.length; i++) {
          console.log(collectList[i].collecttype);
          switch (collectList[i].collecttype) {
            case "bedding":
              collectList[i].collecttype = "이불";
              break;
            case "wash":
              collectList[i].collecttype = "생활빨래";
              break;
            case "shirts":
              collectList[i].collecttype = "셔츠";
              break;
            case "cleaning":
              collectList[i].collecttype = "개별 클리닝";
              break;
          }
        }
        setCollectList(collectList);
      });
    }
    collectCheck();
  }, []);
  const collectWithdraw = useCallback(async (e) => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    console.log(e.target.name);
    await axios({
      method: "delete",
      url: `/v1/api/order/${e.target.name}`,
      headers : {
        "Authorization" : Authorization,
        "RefreshToken" : RefreshToekn 
      }
    }).then((res) => {
      alert("철회 신청 되어습니다.");
      window.location.reload();
    });
    // console.log(e.target.name)
  });
  const allWithdraw = useCallback(async (e) => {
    let Authorization = localStorage.getItem("authorization")
    let RefreshToekn = localStorage.getItem("refreshtoken")
    for (let collect of collectList) {
      await axios({
        method: "delete",
        url: `/v1/api/order/${collect.collectId}`,
        headers : {
          "Authorization" : Authorization,
          "RefreshToken" : RefreshToekn 
        }
      });
    }
    window.location.reload();
  });
  return (
    <div className="result_container">
      <h1>수거 신청 정보</h1>
      <Card className="laundry_list">
        {collectList.map((collect) => (
          <ListGroup key={collect.collectId} horizontal="xxl" as="ol">
            <ListGroup.Item as="li" className="d-flex justify-content-between align-items-start">
              <div className="ms-2 me-auto">
                <div className="fw-bold">세탁물 종류 : {collect.collectType}</div>
                수거 요청일: {collect.collectRequestDate.split("T")[0]}
              </div>
              <Button bg="primary" className="button" name={collect.collectId} onClick={collectWithdraw}>
                수거 철회
              </Button>
            </ListGroup.Item>
          </ListGroup>
        ))}
      </Card>
      <h2 className="mt-4">수거/배송 주소</h2>
      <p>{address}</p>
      <div className='d-grid gap-2'>
        <Button onClick={allWithdraw} variant="danger" size="lg" className="cancel_all_btn">
          수거 전체 철회
        </Button>
        <Button href="/applicationResult" variant="success" size="lg" className="close_btn">
          닫기
        </Button>
      </div>
    </div>
  );
}

export default ApplicationDetail;
