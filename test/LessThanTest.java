import static org.junit.Assert.assertEquals;

import edu.cs3500.spreadsheets.model.LessThan;
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
 * Tests less than functionality.
 */
public class LessThanTest {
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
  public void testLessThanWithAllTypes() {
    Operation lt = new LessThan();
    cells.add(num1);
    cells.add(num2);
    cells.add(blank);
    cells.add(bool);
    cells.add(str);

    Value computedVal = lt.apply(cells);
    assertEquals(true, computedVal instanceof ValueBoolean);
    assertEquals(computedVal.toString(), "false");
  }

//  @Test
//  public void testLessThanNumbers() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SNumber(3));
//    sexp.add(new SNumber(4));
//    LessThan lt = new LessThan(sexp);
//    lt.operate();
//    assertEquals(false, lt.getResult());
//  }
//
//  @Test
//  public void testLessThanBooleans() {
//    ArrayList<Sexp> sexp = new ArrayList<Sexp>();
//    sexp.add(new SBoolean(true));
//    sexp.add(new SBoolean(false));
//    LessThan lt = new LessThan(sexp);
//    lt.operate();
//    assertEquals(false, lt.getResult());
//  }

}