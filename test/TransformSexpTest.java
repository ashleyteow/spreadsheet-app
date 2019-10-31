import edu.cs3500.spreadsheets.model.TransformSListToArrayList;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import org.junit.Test;

public class TransformSexpTest {

  @Test
  public void testFormulaTransfrom() {
    SList list = new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4)));
    TransformSListToArrayList transform = new TransformSListToArrayList(list);
    transform.transform();
    System.out.println(transform.getList());
  }

  @Test
  public void testReferenceTransform() {
    SList list = new SList(new SSymbol("SUM"), new SSymbol("A1:A4"));
    TransformSListToArrayList transform = new TransformSListToArrayList(list);
    transform.transform();
    System.out.println(transform.getList());
  }

}