import Test from './test/test';
import Home from './components/Home';
import {Route, Routes} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/test" element={<Test/>} />
      </Routes>
    </div>
  );
}

export default App;
