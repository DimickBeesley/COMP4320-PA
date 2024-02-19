package Assignment1;

import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class MathOpEncoderBin implements MathOpEncoder {

  // I skipped the const file that he had to just put it in here. It felt unnecessary in this context.
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

    // Encode Integers
    out.writeInt(mathOp.tml);
    out.writeInt(mathOp.opCode);
    out.writeInt(mathOp.operand1);
    out.writeInt(mathOp.operand2);
    out.writeInt(mathOp.requestID);
    out.writeInt(mathOp.opNameLength);

    // Encode String 
    byte[] encodedOpname = mathOp.opName.getBytes(encoding);
    if (encodedOpname.length > MAX_OP_NAME_LEN)
      throw new IOException("mathOp operation name exceeds encoded length limit");
    out.writeByte(encodedOpname.length); // provides length of operation name
    out.write(encodedOpname);
    out.flush();
    return buf.toByteArray();
  }
}
