   -----------------------
 /   WHAT NEEDS DOING   /
-----------------------

For me to make the server, I think the Client needs to get figured out
Client needs:
    - conversion from Friend files to MathOp files
    - making sure they work at the base level


  ------------------------
 / HOW TO RUN THE STUFF /
-----------------------

TO RUN THE PYTHON SERVER:

PORT: 10010 <= integer <= 10200

$ python UDPServer.py <PORT>



TO RUN THE JAVA CLIENT:

compile it, then run with the same PORT as the server
SERVERNAME: your local IPAddress if you wanna run it locally (or you could do the tux thing which we will have to do at some point)
MESSAGE: what you send to the server from the client

$java UDPClientTimeout.java <SERVERNAME> <MESSAGE> <PORT>