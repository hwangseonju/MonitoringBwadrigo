import Test from "./test/test";
import Home from "./components/Home";
import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import MemberPlan from "./components/usePlan/MemberPlan";
import StartTermination from "./components/usePlan/StartTermination";
import SurveyTermination from "./components/usePlan/SurveyTermination";
import FinishTermination from "./components/usePlan/FinishTermination";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/test" element={<Test />} />
        <Route path="/member-plan" element={<MemberPlan />} />
        <Route path="/start-termination" element={<StartTermination />} />
        <Route path="/survey-termination" element={<SurveyTermination />} />
        <Route path="/finish-termination" element={<FinishTermination />} />
      </Routes>
    </div>
  );
}

export default App;
