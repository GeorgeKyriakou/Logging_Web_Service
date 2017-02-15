
## Usage
This project was part of a course during the Computer Science programme at Malmö University, in Sweden. 
Node web server that registers all requests from an AXIS camera. The server checks the request IP, if it is from the camera, and then logs that specific timestamp. Else, if the IP of the request is from anyone else, it responds with all the past logged events. The requests from the camera can be triggered by a Motion Detection application, thus making the server a Motion Detection Logger.
On the client side, we develop a java client using Jersey framework, which reads the xml format of the server, filters the date and the time of the each log, and finally passes it to a data plotting class, to graphically display all the events. The chart class is made using the jfreechart library.






### Tools

Created with [Nodeclipse](https://github.com/Nodeclipse/nodeclipse-1)
 ([Eclipse Marketplace](http://marketplace.eclipse.org/content/nodeclipse), [site](http://www.nodeclipse.org))   

Nodeclipse is free open-source project that grows with your contributions.
# Logging_Web_Service
