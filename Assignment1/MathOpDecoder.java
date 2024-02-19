package Assignment1;

import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface MathOpDecoder {
  MathOp decode(InputStream source) throws IOException;
  MathOp decode(DatagramPacket packet) throws IOException;
}
