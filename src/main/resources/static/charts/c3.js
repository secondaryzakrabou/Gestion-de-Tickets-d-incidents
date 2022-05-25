// Data

tempDataValues = ['Temperature', 11, 8, 15, 18, 19, 17, 19,11, 8, 15, 18, 19, 17, 20];
tempDataKeys = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'test', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', "aa"]


// updating charte
function addData(key, value){
	tempDataKeys.splice(0, 1);
	tempDataValues.splice(1, 1);
	tempDataKeys.push(key);
	tempDataValues.push(value);
	regenerate();
}


// Networking

let sse = new EventSource("/realtime/1")

sse.onmessage = function(e) {
  console.log(e.data);
  const obj = JSON.parse(e.data);
  addData(obj.date, obj.valeur);
}



// Charts
function regenerate(){
	c3.generate({
	    bindto: '#chart-area', // id of chart wrapper
	    data: {
	        columns: [
	            // each columns data
	            tempDataValues
	        ],
	        type: 'area', // default type of chart
	        colors: {
	            'data1': '#61bda1',
	            'data2': '#2c83b6',
	        },
	        names: {
	            // name of each serie
	            'data1': 'Maximum',
	            'data2': 'Minimum'
	        }
	    },
	    axis: {
	        x: {
	            type: 'category',
	            // name of each category
	            categories: tempDataKeys
	        },
	    },
	    legend: {
	        show: true, //hide legend
	    },
	    padding: {
	        bottom: 20,
	        top: 0
	    },
	});
}
 regenerate();
