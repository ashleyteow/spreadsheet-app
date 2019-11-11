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
}