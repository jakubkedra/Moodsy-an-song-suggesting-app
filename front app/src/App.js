import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Login from'./components/Login';
import Register from'./components/Register';
import Upload from "./components/Upload";
import History from "./components/History";
import Home from "./components/Home";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Display from "./components/Display";
import NavTabsExample from "./components/NavTabsExample";
function App() {
  return (
      <Router>
        <Routes>
          <Route path='/login' element={<Login/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/upload' element={<Upload/>}/>
          <Route path='/home' element={<Home/>}/>
          <Route path='/show' element={<Display/>}/>
          <Route path='/history' element={<History/>}/>
          <Route path='/test' element={<NavTabsExample/>}/>
        </Routes>
      </Router>
  );
}

export default App;
