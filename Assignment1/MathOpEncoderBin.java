package Assignment1;

import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class MathOpEncoderBin implements MathOpEncoder {

  private static String DEFAULT_ENCODING = "UTF-16BE";
  private static int MAX_OP_NAME_LEN = 255;


  private String encoding;  // Character encoding

  public MathOpEncoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public MathOpEncoderBin(String encoding) {
    this.encoding = encoding;
  }

  public byte[] encode(MathOp mathOp) throws Exception {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);

    out.writeInt(mathOp.tml);
    out.writeInt(mathOp.opCode);
    out.writeInt(mathOp.operand1);
    out.writeInt(mathOp.operand2);
    out.writeInt(mathOp.requestID);
    out.writeInt(mathOp.opNameLength);

    byte[] encodedLastname = mathOp.opName.getBytes(encoding);
    if (encodedLastname.length > MAX_OP_NAME_LEN)
      throw new IOException("mathOp lastname exceeds encoded length limit");
    out.writeByte(encodedLastname.length); // provides length of lastname
    out.write(encodedLastname);
    out.flush();
    return buf.toByteArray();
  }
}
