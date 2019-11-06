import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Multiply;
import edu.cs3500.spreadsheets.model.Operation;
import edu.cs3500.spreadsheets.model.Value;
import edu.cs3500.spreadsheets.model.ValueBlank;
import edu.cs3500.spreadsheets.model.ValueBoolean;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.ValueString;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests multiply/product functionality.
 */
public class MultiplyTest {
  Value blank;
  Value bool;
  Value str;
  Value num1;
  Value num2;
  ArrayList<Value> cells;

  @Before
  public void setup() {
    blank = new ValueBlank();
    bool = new ValueBoolean(false);
    str = new ValueString("ignore this string");
    num1 = new ValueDouble(6.0);
    num2 = new ValueDouble(7.0);
    cells = new ArrayList<>();
  }

  @Test
  public void testProductWithAllTypes() {
    Operation multiply = new Multiply();
    cells.add(num1);
    cells.add(num2);
    cells.add(blank);
    cells.add(bool);
    cells.add(str);

    Value computedVal = multiply.apply(cells);
    assertEquals(computedVal.toString(), "42.000000");
    assertEquals(true, computedVal instanceof ValueDouble);
  }

//  @Test
//  public void testProductWithOnlyNumbers() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(4));
//    sexp.add(new SNumber(3));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(12.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void testProductWithNumbersAndStrings() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SString("hello"));
//    sexp.add(new SNumber(3));
//    sexp.add(new SString("cake"));
//    sexp.add(new SNumber(6));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(18.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void testProductWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SString("hello"), new SNumber(3),
//        new SString("cake"), new SNumber(6)));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(18.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void test2ProductWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(2));
//    sexp.add(new SList(new SSymbol("SUM"), new SNumber(3), new SNumber(4), new SNumber(5)));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(24.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void test3ProductWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(2));
//    sexp.add(new SList(new SSymbol("PRODUCT"), new SNumber(3), new SNumber(4), new SNumber(5)));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(120.0, prod.getProduct(), 0.0001);
//  }
//
//
//  @Test
//  public void testProductWithSymbols() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SSymbol("SUM"));
//    sexp.add(new SNumber(3));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(3.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void testProductWithLists2() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SString("hello"), new SNumber(3),
//        new SString("cake"), new SNumber(6)));
//    sexp.add(new SNumber(2));
//    sexp.add(new SString("bread"));
//    Multiply prod = new Multiply(sexp);
//    prod.operate();
//    assertEquals(36.0, prod.getProduct(), 0.0001);
//  }

}