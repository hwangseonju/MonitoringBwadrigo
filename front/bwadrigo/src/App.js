import Home from "./components/Home";
import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import MemberPlan from "./components/usePlan/MemberPlan";
import StartTermination from "./components/usePlan/StartTermination";
import SurveyTermination from "./components/usePlan/SurveyTermination";
import FinishTermination from "./components/usePlan/FinishTermination";

import Application from './components/collection/Application';
import ApplicationDetail from './components/collection/ApplicationDetail';
import ApplicationInfo from './components/collection/ApplicationInfo';
import ApplicationResult from './components/collection/ApplicationResult';

import MyInfo from './components/MyInfo';
import MyInfoModify from './components/MyInfoModify'
import PaymentHistory from "./components/payment/PaymentHistory";
import PaymentHistoryDetail from "./components/payment/PaymentHistoryDetail"
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/member-plan" element={<MemberPlan />} />
        <Route path="/start-termination" element={<StartTermination />} />
        <Route path="/survey-termination" element={<SurveyTermination />} />
        <Route path="/finish-termination" element={<FinishTermination />} />

        <Route path="/application" element={<Application/>} />
        <Route path="/applicationInfo" element={<ApplicationInfo/>} />
        <Route path="/applicationDetail" element={<ApplicationDetail/>} />
        <Route path="/applicationResult" element={<ApplicationResult />} />
        
        <Route path="/myInfo" element={<MyInfo />} />
        <Route path="/myinfomodify" element={<MyInfoModify/>}/>
        <Route path="/payHistory" element={<PaymentHistory />} />
        <Route path="/payHistoryDetail" element={<PaymentHistoryDetail/>}/>
      </Routes>
    </div>
  );
}

export default App;
