import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Concatenate;
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
 * Tests all public methods for a concatenate function.
 */
public class ConcatenateTest {
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
  public void testConcatWithAllTypes() {
    Value str1 = new ValueString("Hello ");
    Value str2 = new ValueString("World!");
    cells.add(str1);
    cells.add(str2);
    Operation concat = new Concatenate();

    Value computedVal = concat.apply(cells);
    assertEquals(computedVal.toString(), "Hello World!");
    assertEquals(true, computedVal instanceof ValueString);
  }
//
//  @Test
//  public void testConcatWithString() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SString("hello"));
//    sexp.add(new SString(" "));
//    sexp.add(new SString("i"));
//    sexp.add(new SString(" "));
//    sexp.add(new SString("am"));
//    sexp.add(new SString(" "));
//    sexp.add(new SString("ashley"));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("hello i am ashley", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithNumbers() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(3));
//    sexp.add(new SString(", "));
//    sexp.add(new SNumber(4));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("3.0, 4.0", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithMultipleFunctions() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SSymbol("CONCAT"),
//        new SList(new SSymbol("SUM"), new SNumber(1), new SNumber(5), new SNumber(7)),
//        new SList(new SSymbol("PRODUCT"), new SNumber(6), new SNumber(3)),
//        new SList(new SSymbol("CONCAT"), new SString("Hello "),
//            new SString("There"))));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("13.018.0Hello There", c.getStr());
//  }
//
//
//  @Test
//  public void testConcatWithNumbersAndStrings() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SString("I will be needing "));
//    sexp.add(new SNumber(4.5));
//    sexp.add(new SString(" grams of sugar."));
//
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("I will be needing 4.5 grams of sugar.", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithBooleans() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SString("2+2 = 4 : "));
//    sexp.add(new SBoolean(true));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("2+2 = 4 : TRUE", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithLists() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SString("2+2 = 4 : "), new SBoolean(true)));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("2+2 = 4 : TRUE", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithSymbols() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SSymbol("SYMBOL1 "));
//    sexp.add(new SSymbol("SYMBOL2"));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("SYMBOL1 SYMBOL2", c.getStr());
//  }
//
//  @Test
//  public void testConcatWithLists2() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SList(new SString("2+2 = 4 : "), new SBoolean(true)));
//    sexp.add(new SString("\n"));
//    sexp.add(new SList(new SString("3+4 = 6 : "), new SBoolean(false)));
//    Concatenate c = new Concatenate(sexp);
//    c.operate();
//    assertEquals("2+2 = 4 : TRUE\n3+4 = 6 : FALSE", c.getStr());
//  }
}