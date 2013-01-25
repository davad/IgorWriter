/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spectrometertest;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author David Landry <dlandry@byu.net>
 */
public class IgorWriter {
  public static void write (String filename, String[] names,
          ArrayList<ArrayList> waves) throws IOException {

    String name = null;
    ArrayList<Double[]> data = null;

    int height, width = 0;

    FileWriter outFile = new FileWriter(filename);
    PrintWriter out = new PrintWriter(outFile);
    out.println("IGOR");

    if (names.length != waves.size()) {
      throw new IOException("Wave names and data must be the same size");
    }
    for (int i = 0; i < names.length; i++) {
      name = names[i];
      data = waves.get(i);
      height = data.size();
      width  = data.get(0).length;

    
      out.printf("WAVES/D/N=(%d,%d)\t%s", height, width, name);
      out.println();
      out.println("BEGIN");

      // loop for printing the data:
      for (Double[] line : data) {
        for (Double item : line) {
          out.printf("\t%5.3f", item);
        }
        out.println();
      }

      out.println("END");
      out.println();
    }
    out.close();
  }
}
