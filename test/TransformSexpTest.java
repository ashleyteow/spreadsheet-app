import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.TransformSexp;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import org.junit.Test;

public class TransformSexpTest {

  @Test
  public void getList() {
    SList list = new SList(new SSymbol("PRODUCT"),
        new SNumber(3), new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4)));
    TransformSexp transform = new TransformSexp(list);
    transform.transform();
    System.out.println(transform.getList());
  }
}