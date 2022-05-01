import {Nav, Image} from 'react-bootstrap';
import {useCallback, useState} from 'react';

function Footer(){

    const [selectTab, setSelectTab] = useState(localStorage.getItem("tab") ? localStorage.getItem("tab") : "/");
    const [tabStatus, setTab] = useState({
        "homeTab" : selectTab == "/" ? true : false,
        "collectTab" : selectTab == "/collect" ? true : false,
        "documentTab" : selectTab == "/document" ? true : false,
        "myTab" : selectTab == "/profile" ? true : false
    });


    const onChange = (e) => {
        const {name} = e.target
        setSelectTab(name);
        let cookieList = document.cookie.split("; ");
        let accessToken;
        for(let str of cookieList){
            let kv = str.split("=");
            let key = kv[0];
            if(key == "accessToken"){
                accessToken = kv[1];        
            }
        }
        switch(name){
            case "/":
                // localStorage.removeItem("tab");
                console.log(name);
                window.location.replace("/");
                break;
            case "/collect":
                localStorage.setItem("tab", "/collect")
                if(accessToken){
                    window.location.href = "/collect";
                }
                else{
                    window.location.href = "/pleaseLogin";
                }
                break;
            case "/document":
                localStorage.setItem("tab", "/document")
                if(accessToken){
                    window.location.href = "/document";
                }
                else{
                    window.location.href = "/pleaseLogin";
                }
                break;
            case "/profile":
                window.location.href = "/profile"
                break;
        }
        // let accessToken = "asdkafgpjhqj9o-qjoiqepoje";
        // document.cookie = "refreshToken="+accessToken; 
        console.log(document.cookie);
    }



    return(
        <div>
            <Nav className="justify-content-center" variant="tabs" defaultActiveKey={selectTab}>
                <Nav.Item>
                    <Nav.Link eventKey="/" name="/" onClick={onChange} >
                        <img src={require(tabStatus.homeTab ? "../img/nav/home-select.png" : "../img/nav/home.png")} name="/" onClick={onChange}/>
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/collect" name="/collect" onClick={onChange}>
                        <img src={require(tabStatus.collectTab ? "../img/nav/collect-select.png" : "../img/nav/collect.png")} name="/collect" onClick={onChange}/>
                        </Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/document" name="/document" onClick={onChange}>
                        <img src={require(tabStatus.documentTab ? "../img/nav/document-select.png" : "../img/nav/document.png")} name="/document" onClick={onChange}/>
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="/profile" name="/profile" onClick={onChange}>
                        <img src={require(tabStatus.myTab ? "../img/nav/my-select.png" : "../img/nav/my.png")} name="/profile" onClick={onChange}/>
                    </Nav.Link>
                </Nav.Item>
            </Nav>
        </div>
    )

}

export default Footer;