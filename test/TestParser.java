import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.Test;

public class TestParser {

  @Test
  public void testParser1() throws FileNotFoundException {
    Scanner rd = new Scanner(
        new File("/Users/gauri_dandi/Documents/Northeastern/2019-2020/CS 3500/"
            + "BeyondGoodProject/testParse1"));
    Sexp s = Parser.parse("3");
    assertEquals(true, s instanceof SNumber);
  }



}
