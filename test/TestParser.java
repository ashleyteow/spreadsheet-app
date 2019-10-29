import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.io.FileNotFoundException;
import org.junit.Test;

public class TestParser {

  @Test
  public void testParser1() {

//    Sexp s = Parser.parse("\"hello\"");
    Sexp s = Parser.parse("#comment \"hello\"");
    System.out.println(s.getClass());
//    assertEquals(true, s instanceof SString);
  }



}
