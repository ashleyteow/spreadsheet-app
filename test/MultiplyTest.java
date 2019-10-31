import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Multiply;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests multiply/product functionality.
 */
public class MultiplyTest {

  @Test
  public void testProductWithOnlyNumbers() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(4));
    sexp.add(new SNumber(3));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(12.0, prod.getProduct(), 0.0001);
  }

  @Test
  public void testProductWithNumbersAndStrings() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SString("hello"));
    sexp.add(new SNumber(3));
    sexp.add(new SString("cake"));
    sexp.add(new SNumber(6));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(18.0, prod.getProduct(), 0.0001);
  }

  @Test
  public void testProductWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SString("hello"), new SNumber(3),
        new SString("cake"), new SNumber(6)));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(18.0, prod.getProduct(), 0.0001);
  }

  @Test
  public void test2ProductWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(2));
    sexp.add(new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4), new SNumber(5)));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(24.0, prod.getProduct(), 0.0001);
  }

  @Test
  public void test3ProductWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(2));
    sexp.add(new SList(new SSymbol("PRODUCT"), new SNumber(3), new SNumber(4), new SNumber(5)));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(120.0, prod.getProduct(), 0.0001);
  }


  @Test
  public void testProductWithSymbols() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SSymbol("SUM"));
    sexp.add(new SNumber(3));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(3.0, prod.getProduct(), 0.0001);
  }

  @Test
  public void testProductWithLists2() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SString("hello"), new SNumber(3),
        new SString("cake"), new SNumber(6)));
    sexp.add(new SNumber(2));
    sexp.add(new SString("bread"));
    Multiply prod = new Multiply(sexp);
    prod.operate();
    assertEquals(36.0, prod.getProduct(), 0.0001);
  }

}