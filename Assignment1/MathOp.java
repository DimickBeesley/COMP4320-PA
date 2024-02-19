/*
* This is the Friend.java file repurposed for our needs
*/

package Assignment1;

public class MathOp {

    public byte tml;            // total message length (bytes)
    public byte opCode;         // which math operation
    public int operand1;
    public int operand2; 
    public short requestID;
    public byte opNameLength;   // length in bytes of opName
    public String opName;      // english word for the operation (e.g. division, addition...)
    

  public MathOp(byte tml, byte opCode, int operand1, int operand2, 
        short requestID, byte opNameLength, String opName)  {
      this.tml          = tml;
      this.opCode       = opCode;
      this.operand1     = operand1;
      this.operand2     = operand2;
      this.requestID    = requestID;
      this.opNameLength = opNameLength;
      this.opName       = opName;
  }

  public String toString() {
    final String EOLN = java.lang.System.getProperty("line.separator");
    String out = "tml = " + tml + EOLN +
                   "opCode = " + opCode + EOLN +
                   "operand1 = " + operand1 + EOLN +
                   "operand2 = " + operand2 + EOLN +
                   "requestID = " + requestID + EOLN +
                   "opNameLength = " + operand2 + EOLN +
                   "opName = " + operand2;

    return out;
  }
}
