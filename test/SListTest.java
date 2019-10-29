import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Multiply;
import edu.cs3500.spreadsheets.model.Operation;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;
import org.junit.Test;

public class SListTest {

  @Test
  public void testSList() {
    SNumber num1 = new SNumber(2);
    SNumber num2 = new SNumber(3);
    SNumber num3 = new SNumber(4);
    SNumber num4 = new SNumber(5);
    Sexp list = new SList(num1, num2, num3);
    Multiply o = new Multiply(list);
    System.out.println(list.accept(o));

  }

}