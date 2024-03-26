import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;   // for IOException and Input/OutputStream


public class ServerTCP {
    public static void main(String args[]) throws Exception {
        
        if (args.length > 1) {
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }

        //Server Port for use in the server (Must be 10029 because we are group 19)
        int servPort = args.length == 1 ? Integer.parseInt(args[0]) : 10029;
        int num = 0;

        ServerSocket servSock = new ServerSocket(servPort);

        RequestDecoder decoder = new RequestDecoder();
        ResponseEncoder encoder = new ResponseEncoder();

        byte[] buffer = new byte[8];

        while (true) {
      
          Socket clientSocket = servSock.accept();

          InputStream in = clientSocket.getInputStream();
          OutputStream out = clientSocket.getOutputStream();

          while (true) {
              num = in.read(buffer);

              if (num == -1) {
                  break;
              }

              Request receivedRequest = decoder.decode(buffer);
              String requestHex = "";

              for (int i = 0; i < receivedRequest.tml; i++) {
                  String datum = "0" + Integer.toHexString(buffer[i]);
                  requestHex += "0x" + datum.substring(datum.length() - 2) + " ";
              }
              System.out.println(requestHex.trim());
              System.out.println(receivedRequest);

              byte errorCode = receivedRequest.tml == num ? (byte) 0 : (byte) 127;

              Response response = new Response(receivedRequest.id, errorCode, receivedRequest.compute());
              byte[] codedResponse = encoder.encode(response);

              out.write(codedResponse);
          }
  }
    }
}