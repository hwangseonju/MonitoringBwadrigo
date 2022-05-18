import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import ApplyPlan from './components/plans/ApplyPlan';
import LandryPlan from './components/plans/LaundryPlan'
import ApplyPlanDetail from './components/plans/ApplyPlanDetail';

import Login from "./components/login";
import Signup from "./components/signup";
import Profile from "./components/profile";
import Signupsuccess from "./components/signupsuccess";
import Home from "./components/Home";

import MemberPlan from "./components/usePlan/MemberPlan";
import StartTermination from "./components/usePlan/StartTermination";
import SurveyTermination from "./components/usePlan/SurveyTermination";
import FinishTermination from "./components/usePlan/FinishTermination";
import PlanTypeHeader from "./components/usePlan/PlanTypeHeader";

import Application from "./components/collection/Application";
import ApplicationDetail from "./components/collection/ApplicationDetail";
import ApplicationInfo from "./components/collection/ApplicationInfo";
import ApplicationResult from "./components/collection/ApplicationResult";

import MyInfo from './components/MyInfo';
import MyInfoModify from './components/MyInfoModify'

import PaymentHistory from "./components/payment/PaymentHistory";
import PaymentHistoryDetail from "./components/payment/PaymentHistoryDetail";

import PleaseLogin from "./components/exception/PleaseLogin";
import PleaseService from "./components/exception/PleaseService";
import UsePlan from "./components/UsePlan";
import "./App.css";


function App() {
  return (
    <div className="App">
      <Routes>

        <Route path="/applyplan" element={<ApplyPlan/>}/>
        <Route path="/laundryplan" element={<LandryPlan/>}/>
        <Route path="/applydetail/:applyid" element={<ApplyPlanDetail/>}/>
        <Route path="/profile" element={<Profile />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/signupsuccess" element={<Signupsuccess />} />
        <Route path="/" element={<ApplyPlan />} />
        <Route path="/member-plan" element={<MemberPlan />} />
        <Route path="/start-termination" element={<StartTermination />} />
        <Route path="/survey-termination" element={<SurveyTermination />} />
        <Route path="/finish-termination" element={<FinishTermination />} />
        <Route path="/plan-type-header" element={<PlanTypeHeader />} />

        <Route path="/application" element={<Application />} />
        <Route path="/applicationInfo" element={<ApplicationInfo />} />
        <Route path="/applicationDetail" element={<ApplicationDetail />} />
        <Route path="/applicationResult" element={<ApplicationResult />} />

        <Route path="/myInfo" element={<MyInfo />} />
        <Route path="/myinfomodify" element={<MyInfoModify/>}/>

        <Route path="/payHistory" element={<PaymentHistory />} />
        <Route path="/payHistoryDetail" element={<PaymentHistoryDetail />} />

        <Route path="/pleaseLogin" element={<PleaseLogin/>} />
        <Route path="/pleaseService" element={<PleaseService/>} />
        <Route path="/usePlan" element={<UsePlan/>} />

      </Routes>
    </div>
  );
}

export default App;
