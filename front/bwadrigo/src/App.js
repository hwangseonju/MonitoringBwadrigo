import Home from './components/Home';
import Application from './components/collection/Application';
import ApplicationDetail from './components/collection/ApplicationDetail';
import ApplicationInfo from './components/collection/ApplicationInfo';
import ApplicationResult from './components/collection/ApplicationResult';
import {Route, Routes} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/application" element={<Application/>} />
        <Route path="/applicationInfo" element={<ApplicationInfo/>} />
        <Route path="/applicationDetail" element={<ApplicationDetail/>} />
        <Route path="/applicationResult" element={<ApplicationResult/>} />
      </Routes>
    </div>
  );
}

export default App;
