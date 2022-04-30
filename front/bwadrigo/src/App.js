import Test from './test/test';
import Home from './components/Home';
import ApplyPlan from './components/ApplyPlan';
import LandryPlan from './components/LaundryPlan'
import ApplyPlanDetail from './components/ApplyPlanDetail';
import {Route, Routes} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/test" element={<Test/>} />
        <Route path="/applyplan" element={<ApplyPlan/>}/>
        <Route path="/laundryplan" element={<LandryPlan/>}/>
        <Route path="/applydetail/:applyid" element={<ApplyPlanDetail/>}/>
      </Routes>
    </div>
  );
}

export default App;
