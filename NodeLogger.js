var connect = require('connect');
var http = require('http');

var x = 0;
var arr = [];
var n = 0;
var server = http.createServer(function(request, response) {
	var ip = request.headers['x-forwarded-for']
			|| request.connection.remoteAddress;
	var date = new Date().toJSON();
	response.writeHead(200, {
		"Content-Type" : "text/html"
	});

	// if ip address is from the camera register new event
	if (ip === "::ffff:192.168.20.246") {
		arr[x] = date;
		console.log(' Times ' + arr.length + ' | ip: ' + ip);
		x++;
		for (n = 0; n < arr.length; n++) {
			response.write(arr[n] + '</br>');
		}
	}
	// else if the ip is from anyone else, respond with all passed events
	else {
		for (n = 0; n <= arr.length - 1; n++) {
			response.write(arr[n] + '</br>');
			
		}
		console.log(ip+" , "+ arr.length);
	}
	response.end();

});
server.listen(3000);
