import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests addition/sum functionality.
 */
public class SumTest {

  @Test
  public void testSumWithString() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SString("3"));
    sexp.add(new SString("4"));
    sexp.add(new SString("6"));
    Sum s = new Sum(sexp);
    s.operate();
    assertEquals(0, s.getSum(),0.0001);
  }

  @Test
  public void testSumWithOnlyNumbers() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(2.5));
    sexp.add(new SNumber(6));
    sexp.add(new SNumber(8));
    sexp.add(new SNumber(1.1));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(17.6, sum.getSum(), 0.0001);
  }

  @Test
  public void testSumWithNumbersAndStrings() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(2.5));
    sexp.add(new SNumber(6));
    sexp.add(new SNumber(8));
    sexp.add(new SString("3"));
    sexp.add(new SString("4"));
    sexp.add(new SString("6"));
    sexp.add(new SNumber(1.1));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(17.6, sum.getSum(), 0.0001);
  }

  @Test
  public void test4SumWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(1));
    sexp.add(new SNumber(2));
    sexp.add(new SNumber(4));
    sexp.add(new SList(new SSymbol("PRODUCT"), new SNumber(4), new SNumber(6)));
    sexp.add(new SList(new SSymbol("CONCAT"), new SString("OOD")));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(31.0, sum.getSum(), 0.0001);
  }

  @Test
  public void testSumWithBooleans() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(2.5));
    sexp.add(new SNumber(6));
    sexp.add(new SBoolean(true));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(8.5, sum.getSum(), 0.0001);
  }

  @Test
  public void testSumWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SNumber(2.5), new SNumber(9)));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(11.5, sum.getSum(), 0.0001);
  }

  @Test
  public void testSumWithSymbols() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SSymbol("PRODUCT"));
    sexp.add(new SNumber(6));
    sexp.add(new SNumber(2));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(8, sum.getSum(), 0.0001);
  }

  @Test
  public void testSumWithLists2() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SNumber(2.5), new SNumber(9)));
    sexp.add(new SNumber(2));
    sexp.add(new SNumber(5));
    sexp.add(new SString("hello"));
    sexp.add(new SSymbol("SUM"));
    sexp.add(new SBoolean(true));
    Sum sum = new Sum(sexp);
    sum.operate();
    assertEquals(18.5, sum.getSum(), 0.0001);
  }

}