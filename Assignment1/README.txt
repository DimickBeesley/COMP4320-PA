   --------------------
 /   WHAT IS DONE    /
--------------------

Verified that Python works on the Tux machines (version 2.7.3)

The server has been translated to Python

The server's and client's ability to communicate
has been tested

All of the Java files from the Friend example that
the client needs have been converted for the sake of
our assignment

   -----------------------
 /   WHAT NEEDS DOING   /
-----------------------

Test the java client as converted

Have java client log byte by byte (in hex) the encoded message

Have java client log byte by byte the response

Have client log round trip time

Have client prompt user for a new request
 
Define the Python server MathOp classes
    - message
    - response
 
make Python functions and test:
    - decode
    - encode
    - service
        - use decode on message
        - do math
        - make response
        - encode response
    - log in a useful way error code related things
 
DEBUG LIKE HELL

report including:
    - whether the thing works
    - directions to compile and execute the program
    - minimum, average, and maximum round trip times
    - tux machine screenshots showing:
        - working client
        - working server
        - one of our usernames
        - date (use the date command)


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