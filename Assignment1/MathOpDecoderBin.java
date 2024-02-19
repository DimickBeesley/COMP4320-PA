package Assignment1;

import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class MathOpDecoderBin implements MathOpDecoder {

  private String encoding;  // Character encoding

  private static String DEFAULT_ENCODING = "UTF-16BE";
  private static int MAX_OP_NAME_LEN = 255; //TODO: Why isn't this used???

  public MathOpDecoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public MathOpDecoderBin(String encoding) {
    this.encoding = encoding;
  }

  public MathOp decode(InputStream wire) throws IOException {
    DataInputStream src = new DataInputStream(wire);
    
    byte tml         = src.readByte();
    byte opCode      = src.readByte();
    int operand1     = src.readInt();
    int operand2     = src.readInt();
    short requestID  = src.readShort();
    
    //Deal with the opName
    byte opNameLength = src.readByte(); // Returns an unsigned byte as an int
    if (opNameLength == -1)
      throw new EOFException();
    byte[] stringBuf = new byte[opNameLength];
    src.readFully(stringBuf);
    String opName = new String(stringBuf, encoding);

    return new MathOp(tml, opCode, operand1, operand2,
      requestID, opNameLength, opName);
  }

  public MathOp decode(DatagramPacket p) throws IOException {
    ByteArrayInputStream payload =
      new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
    return decode(payload);
  }
}
