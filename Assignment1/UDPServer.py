import socket
import sys
import struct

ECHOMAX = 255  # Maximum size of echo datagram


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
    numbers, op_name = decode(datagram_packet)

    op_code =   numbers[1]
    op1 =       numbers[2]
    op2 =       numbers[3]

    result = None

    if op_code == 0:
        result = op1 * op2
    if op_code == 1:
        result = op1 / op2
    if op_code == 2:
        result = op1 | op2
    if op_code == 3:
        result = op1 & op2
    if op_code == 4:
        result = op1 - op2
    if op_code == 5:
        result = op1 + op2

    out = encode(result)

    print(result)

    return out

def decode(byte_array):
    # all bytes before the string
    non_string_portion = byte_array[0:14]
    # length of the string
    string_length = byte_array[14]
    # portion at the end that is the size of the string (just the string)
    string_portion = byte_array[string_length:]

    # Define the format string for struct.unpack
    format_string = '>BBiihB'  # '>' for big-endian, 'B' for byte, 'i' for integer, 'h' for short

    # Unpack the byte array
    decoded_data = struct.unpack(non_string_portion, byte_array)

    return {"numbers": non_string_portion, "op-name": string_portion}

def encode(packet):
    return packet


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
            print("Handling client at " + address[0] + " on port " + address[1])

            # Process message
            response = decode(data)

            # Send response back to client
            sock.sendto(response, address)

    except socket.error as e:
        print("Error: " + e)
        sys.exit(1)



if __name__ == "__main__":
    main()