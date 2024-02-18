TO RUN THE PYTHON SERVER:

PORT: 10010 <= integer <= 10200

$ python UDPServer.py <PORT>



TO RUN THE JAVA CLIENT:

compile it, then run with the same PORT as the server
SERVERNAME: your local IPAddress if you wanna run it locally (or you could do the tux thing)
MESSAGE: what you send to the server from the client

$java UDPClientTimeout.java <SERVERNAME> <MESSAGE> <PORT>