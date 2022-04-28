// import Test from "./test/test";
// import Home from "./components/Home";
import Login from "./components/login";
import Signup from "./components/signup";
import Profile from "./components/profile";

import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route path="/" element={<Home />} />
        <Route path="/test" element={<Test />} /> */}
        <Route path="/" element={<Profile />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
      </Routes>
    </div>
  );
}

export default App;
