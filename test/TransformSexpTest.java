import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.TransformSexp;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import javax.xml.crypto.dsig.Transform;
import org.junit.Test;

public class TransformSexpTest {

  @Test
  public void testFormulaTransfrom() {
    SList list = new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4)));
    TransformSexp transform = new TransformSexp(list);
    transform.transform();
    System.out.println(transform.getList());
  }

  @Test
  public void testReferenceTransform() {
    SList list = new SList(new SSymbol("SUM"), new SSymbol("A1:A4"));
    TransformSexp transform = new TransformSexp(list);
    transform.transform();
    System.out.println(transform.getList());
  }

}