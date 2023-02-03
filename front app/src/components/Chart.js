import "./loginCSS.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
ChartJS.register(ArcElement, Tooltip, Legend);

function Chart(props) {
  const data = {
    labels: Object.keys(props.emotionCount),
    datasets: [
      {
        label: "Poll",
        data: Object.values(props.emotionCount),
        backgroundColor: [
          "#c780e8",
          "#9d94ff",
          "#59adf6",
          "#08cad1",
          "#42d6a4",
          "#f8f38d",
          "#ffb480",
          "#ff6961"
        ],
        borderColor: [
          "#171717",
          "#171717",
          "#171717",
          "#171717",
          "#171717",
          "#171717",
          "#171717",
          "#171717",
        ],
        circumference: 180,
        rotation: 270,
        cutout: '70%'
      },
    ],
  };
  const options = {
    responsive: true,
    maintainAspectRatio: true,
    plugins: {
      legend: {
        display: true,
        position: "right",
        rtl: true,
        labels: {font: {
            size: 16
          },
        },
      },
    },
  };

  return (
    <div className="row justify-content-sm-center h-550">
      <Doughnut data={data} options={options} />
    </div>
  );
}

export default Chart;
