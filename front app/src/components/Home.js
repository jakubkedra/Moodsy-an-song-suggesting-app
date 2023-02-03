import Navbarbar from "./Navbarbar";
import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Upload from "./Upload";
import Info from "./Info";
function Home() {
  return (
    <div className="App" style={{ height: "100vh" }}>
      <Navbarbar/>
      {localStorage.getItem("accessToken") ? (
        <Upload/>
      ) : (<Info/>)}
    </div>
  );
}

export default Home;
