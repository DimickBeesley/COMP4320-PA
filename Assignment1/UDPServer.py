import socket
import sys
import struct

ECHOMAX = 255  # Maximum size of echo datagram

# Mathop class definition
class MathOp():
    
    # Initialization of the MathOp class
    def __init__(self, tml, op_code, op1, op2, requestID, op_name_length, op_name):
    
        if op_name > ECHOMAX:
            print("Operation Name is too large")

        self.tml = tml
        self.op_code = op_code
        self.op1 = op1
        self.op2 = op2
        self.requestID = requestID
        self.op_name_length = op_name_length
        self.op_name = op_name
        return

# Response class definition
class Response():
   
    # Initialization of the Response class
    def __init__(self, tml, result, error_code, request_ID):
        self.tml = tml
        self.result = result
        self.error_code = error_code
        self.request_ID = request_ID
        return



def process_request(datagram_packet):
    decode()

    out = encode(datagram_packet)
    return out

def decode(byte_array):
    return

def encode(packet):
    return packet

'''
def main():
    if len(sys.argv) != 2:
        print(f"Usage: {sys.argv[0]} <Port>")
        sys.exit(1)

    servPort = int(sys.argv[1])

    try:
        # Create a UDP socket
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        
        # Bind the socket to the port
        sock.bind(('0.0.0.0', servPort))
        
        print("UDP echo mathOp is ready to receive data")

        while True:
            # Receive message from client
            data, address = sock.recvfrom(ECHOMAX)
            print(f"Handling client at {address[0]} on port {address[1]}")

            # Process message
            process_datagram_packet(data)

            # Send response back to client
            sock.sendto(data, address)

    except socket.error as e:
        print(f"Error: {e}")
        sys.exit(1)'''


def main():
    message = [(11), 2, 4, 240, 2, 1]
    byte_message = b''.join(struct.pack('<i', i) for i in message)
    utf16be = byte_message.decode('utf-16be')
    back_to_array = utf16be.encode('utf-16be')

    print(back_to_array)



if __name__ == "__main__":
    main()