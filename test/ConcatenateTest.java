import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.Concatenate;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Tests all public methods for a concatenate function.
 */
public class ConcatenateTest {

  @Test
  public void testConcatWithString() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SString("hello"));
    sexp.add(new SString(" "));
    sexp.add(new SString("i"));
    sexp.add(new SString(" "));
    sexp.add(new SString("am"));
    sexp.add(new SString(" "));
    sexp.add(new SString("ashley"));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("hello i am ashley", c.getStr());
  }

  @Test
  public void testConcatWithNumbers() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SNumber(3));
    sexp.add(new SString(", "));
    sexp.add(new SNumber(4));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("3.0, 4.0", c.getStr());
  }

  @Test
  public void testConcatWithMultipleFunctions() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SSymbol("CONCAT"),
        new SList(new SSymbol("SUM"), new SNumber(1), new SNumber(5), new SNumber(7)),
        new SList(new SSymbol("PRODUCT"), new SNumber(6), new SNumber(3)),
        new SList(new SSymbol("CONCAT"), new SString("Hello "),
            new SString("There"))));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("13.018.0Hello There", c.getStr());
  }


  @Test
  public void testConcatWithNumbersAndStrings() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SString("I will be needing "));
    sexp.add(new SNumber(4.5));
    sexp.add(new SString(" grams of sugar."));

    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("I will be needing 4.5 grams of sugar.", c.getStr());
  }

  @Test
  public void testConcatWithBooleans() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SString("2+2 = 4 : "));
    sexp.add(new SBoolean(true));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("2+2 = 4 : TRUE", c.getStr());
  }

  @Test
  public void testConcatWithLists() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SString("2+2 = 4 : "), new SBoolean(true)));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("2+2 = 4 : TRUE", c.getStr());
  }

  @Test
  public void testConcatWithSymbols() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SSymbol("SYMBOL1 "));
    sexp.add(new SSymbol("SYMBOL2"));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("SYMBOL1 SYMBOL2", c.getStr());
  }

  @Test
  public void testConcatWithLists2() {
    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
    sexp.add(new SList(new SString("2+2 = 4 : "), new SBoolean(true)));
    sexp.add(new SString("\n"));
    sexp.add(new SList(new SString("3+4 = 6 : "), new SBoolean(false)));
    Concatenate c = new Concatenate(sexp);
    c.operate();
    assertEquals("2+2 = 4 : TRUE\n3+4 = 6 : FALSE", c.getStr());
  }
}