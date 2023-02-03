import Navbarbar from "./Navbarbar";
import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import { useEffect, useState } from "react";
import Grid from "./Grid";
import Chart from "./Chart";
import { Doughnut } from "react-chartjs-2";

function History() {
  const url = "/api/getMatchedTracks";
  const [object, setObject] = useState([]);
  const [arrayOfEmotions, setArrayOfEmotions] = useState([]);
  const [emotionCount, setEmotionCount] = useState({});
  
  useEffect(() => {
    axios({
      method: "get",
      url: url,
      headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
      },
      withCredentials: true,
    })
      .then((response) => {
        setObject(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  useEffect(() => {
    console.log(object);
    let emotionsEmptyArray = [];
    for (var i = 0; i < object.length; i++) {
      emotionsEmptyArray.push(object[i].strongestEmotion);
    }
    setArrayOfEmotions(emotionsEmptyArray);
  }, [object]);

  useEffect(() => {
    const counts = {};
    arrayOfEmotions.forEach(function (x) {
      counts[x] = (counts[x] || 0) + 1;
    });
    console.log(counts);
    setEmotionCount(counts);
  }, [arrayOfEmotions]);

  return (
    <div className="App">
      <Navbarbar />
      <Chart emotionCount={emotionCount}/>
      <Grid object={object} />
    </div>
  );
}

export default History;
