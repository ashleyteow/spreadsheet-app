import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.TransformSListToArrayList;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests the transformation of an Sexp to an {@code ArrayList<Sexp>}.
 */
public class TransformSexpTest {

  @Test
  public void testFormulaTransform() {
    SList list = new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4)));
    TransformSListToArrayList transform = new TransformSListToArrayList(list);
    transform.transform();
    ArrayList<Sexp> expected = new ArrayList<Sexp>();
    expected.add(new SSymbol("PRODUCT"));
    expected.add(new SNumber(3));
    expected.add(new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4)));
    assertEquals(expected, transform.getList());
  }

  @Test
  public void testReferenceTransform() {
    SList list = new SList(new SSymbol("SUM"), new SSymbol("A1:A4"));
    TransformSListToArrayList transform = new TransformSListToArrayList(list);
    transform.transform();
    ArrayList<Sexp> expected = new ArrayList<Sexp>();
    expected.add(new SSymbol("A1"));
    expected.add(new SSymbol("A2"));
    expected.add(new SSymbol("A3"));
    expected.add(new SSymbol("A4"));
    assertEquals(expected, transform.getList());
  }

}