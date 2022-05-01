import Footer from "./Footer.jsx";

function Home(){
    console.log("홈이냐?");
    localStorage.setItem("tab", "/")
    return(
        <div>홈홈
            <Footer>

            </Footer>
        </div>
    )
}

export default Home;