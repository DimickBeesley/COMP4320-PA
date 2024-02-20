import socket
import sys

ECHOMAX = 255  # Maximum size of echo datagram

def main():
    if len(sys.argv) != 2:
        print(f"Usage: {sys.argv[0]} <Port>")
        sys.exit(1)

    servPort = int(sys.argv[1])

    try:
        # Create a UDP socket
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.bind(('0.0.0.0', servPort))

        print("UDP echo server is ready to receive data")

        while True:
            # Receive message from client
            data, address = sock.recvfrom(ECHOMAX)
            print(f"Handling client at {address[0]} on port {address[1]}")

            # Send the same message back to client
            sock.sendto(data, address)

    except socket.error as e:
        print(f"Error: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()