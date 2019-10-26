import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.WorksheetReader;
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

//    Sexp s = Parser.parse("\"hello\"");
    Sexp s = Parser.parse("#comment \"hello\"");
    System.out.println(s.getClass());
//    assertEquals(true, s instanceof SString);
  }



}
