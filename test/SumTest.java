import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Operation;
import edu.cs3500.spreadsheets.model.Sum;
import edu.cs3500.spreadsheets.model.Value;
import edu.cs3500.spreadsheets.model.ValueBlank;
import edu.cs3500.spreadsheets.model.ValueBoolean;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.ValueString;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests addition/sum functionality.
 */
public class SumTest {
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
    num1 = new ValueDouble(7.0);
    num2 = new ValueDouble(1.0);
    cells = new ArrayList<>();
  }

  @Test
  public void testSumTestWithAllTypes() {
    Operation sum = new Sum();
    cells.add(num1);
    cells.add(num2);
    cells.add(blank);
    cells.add(bool);
    cells.add(str);

    Value computedVal = sum.apply(cells);
    assertEquals("7.000000", computedVal.toString());
    assertEquals(true, computedVal instanceof ValueDouble);
  }

  @Test
  public void testSumWithString() {
    Value str1 = new ValueString("3");
    Value str2 = new ValueString("4");
    Value str3 = new ValueString("6");

    Operation sum = new Sum();
    cells.add(str1);
    cells.add(str2);
    cells.add(str3);

    Value computedVal = sum.apply(cells);
    assertEquals("13.000000" ,computedVal.toString());
    assertEquals(true, computedVal instanceof ValueDouble);
  }

  @Test
  public void test2SumWithString() {
    Value str1 = new ValueString("3");
    Value str2 = new ValueString("4");
    Value str3 = new ValueString("ignore this text");

    ArrayList<Value> cells = new ArrayList<>();
    Operation sum = new Sum();
    cells.add(str1);
    cells.add(str2);
    cells.add(str3);

    Value computedVal = sum.apply(cells);
    assertEquals("7.000000" ,computedVal.toString());
    assertEquals(true, computedVal instanceof ValueDouble);
  }

//  @Test
//  public void testSumWithOnlyNumbers() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(2.5));
//    sexp.add(new SNumber(6));
//    sexp.add(new SNumber(8));
//    sexp.add(new SNumber(1.1));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(17.6, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithNumbersAndStrings() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(2.5));
//    sexp.add(new SNumber(6));
//    sexp.add(new SNumber(8));
//    sexp.add(new SString("3"));
//    sexp.add(new SString("4"));
//    sexp.add(new SString("6"));
//    sexp.add(new SNumber(1.1));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(17.6, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void test4SumWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(1));
//    sexp.add(new SNumber(2));
//    sexp.add(new SNumber(4));
//    sexp.add(new SList(new SSymbol("PRODUCT"), new SNumber(4), new SNumber(6)));
//    sexp.add(new SList(new SSymbol("CONCAT"), new SString("OOD")));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(31.0, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithBooleans() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(2.5));
//    sexp.add(new SNumber(6));
//    sexp.add(new SBoolean(true));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(8.5, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SNumber(2.5), new SNumber(9)));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(11.5, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithSymbols() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SSymbol("PRODUCT"));
//    sexp.add(new SNumber(6));
//    sexp.add(new SNumber(2));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(8, sum.getSum(), 0.0001);
//  }
//
//  @Test
//  public void testSumWithLists2() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SNumber(2.5), new SNumber(9)));
//    sexp.add(new SNumber(2));
//    sexp.add(new SNumber(5));
//    sexp.add(new SString("hello"));
//    sexp.add(new SSymbol("SUM"));
//    sexp.add(new SBoolean(true));
//    Sum sum = new Sum(sexp);
//    sum.operate();
//    assertEquals(18.5, sum.getSum(), 0.0001);
//  }

}