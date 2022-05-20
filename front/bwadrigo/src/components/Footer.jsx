import {Nav, Image} from 'react-bootstrap';
import {useCallback, useEffect, useState} from 'react';
import "./Footer.css";

function Footer(){

    const [selectTab, setSelectTab] = useState(localStorage.getItem("tab") ? localStorage.getItem("tab") : "/");
    const [tabStatus, setTab] = useState({
        "homeTab" : selectTab == "/" ? true : false,
        "collectTab" : selectTab == "/applicationResult" ? true : false,
        "documentTab" : selectTab == "/usePlan" ? true : false,
        "myTab" : selectTab == "/profile" ? true : false
    });


    const [accessToken, setAccessToken] = useState()
    useEffect(()=>{
        let cookieList = document.cookie.split("; ");
        for(let str of cookieList){
            let kv = str.split("=");
            let key = kv[0];
            if(key == "accessToken"){
                setAccessToken(kv[1]);
                break;        
            }
    }
    },[])
    

    const onChange = async (e) => {
        const {name} = e.target
        setSelectTab(name);
        
        // accessToken = "asdkafgpjhqj9o-qjoiqepoje";
        // document.cookie = "accessToken="+accessToken; 
        console.log(accessToken)
        switch(name){
            case "/":
                // localStorage.removeItem("tab");
                console.log(name);
                window.location.replace("/");
                localStorage.setItem("tab", "/")

                break;
            case "/applicationResult":
                localStorage.setItem("tab", "/applicationResult")
                if(accessToken){
                    window.location.href = "/applicationResult";
                }
                else{
                    window.location.href = "/pleaseLogin";
                }
                    
                window.location.href = "/applicationResult";
                break;
            case "/usePlan":
                localStorage.setItem("tab", "/usePlan")
                window.location.href = "/usePlan";
                break;
            case "/profile":
                localStorage.setItem("tab", "/profile")
                window.location.href = "/profile"
                break;
        }
        console.log(document.cookie);
    }



    return(
        <div className='footer_container'>
            <Nav className="justify-content-center" variant="tabs" defaultActiveKey={selectTab}>
                <Nav.Item className='footer_item'>
                    <Nav.Link eventKey="/" name="/" onClick={onChange} >
                        <img className='footer_img' src={require(tabStatus.homeTab ? "../img/nav/home-select.png" : "../img/nav/home.png")} name="/" onClick={onChange}/>
                        <div className='footer_div' style={tabStatus.homeTab ? {color: "#1FB67A"} : {color: "gray"}} >홈</div>
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item className='footer_item'>
                    <Nav.Link eventKey="/applicationResult" name="/applicationResult" onClick={onChange}>
                        <img className='footer_img' src={require(tabStatus.collectTab ? "../img/nav/collect-select.png" : "../img/nav/collect.png")} name="/applicationResult" onClick={onChange}/>
                        <div className='footer_div' style={tabStatus.collectTab ? {color: "#1FB67A"} : {color: "gray"}}>수거요청</div>
                        </Nav.Link>
                </Nav.Item>
                <Nav.Item className='footer_item'>
                    <Nav.Link eventKey="/usePlan" name="/usePlan" onClick={onChange}>
                        <img className='footer_img' src={require(tabStatus.documentTab ? "../img/nav/document-select.png" : "../img/nav/document.png")} name="/usePlan" onClick={onChange}/>
                        <div className='footer_div' style={tabStatus.documentTab ? {color: "#1FB67A"} : {color: "gray"}}>이용내역</div>
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item className='footer_item'>
                    <Nav.Link eventKey="/profile" name="/profile" onClick={onChange}>
                        <img className='footer_img' src={require(tabStatus.myTab ? "../img/nav/my-select.png" : "../img/nav/my.png")} name="/profile" onClick={onChange}/>
                        <div className='footer_div' style={tabStatus.myTab ? {color: "#1FB67A"} : {color: "gray"}}>MY</div>
                    </Nav.Link>
                </Nav.Item>
            </Nav>
        </div>
    )

}

export default Footer;