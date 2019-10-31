import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.LessThan;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests less than functionality.
 */
public class LessThanTest {

  @Test
  public void testLessThanNumbers() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(3));
    sexp.add(new SNumber(4));
    LessThan lt = new LessThan(sexp);
    lt.operate();
    assertEquals(false, lt.getResult());
  }

  @Test
  public void testLessThanBooleans() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SBoolean(true));
    sexp.add(new SBoolean(false));
    LessThan lt = new LessThan(sexp);
    lt.operate();
    assertEquals(false, lt.getResult());
  }

}