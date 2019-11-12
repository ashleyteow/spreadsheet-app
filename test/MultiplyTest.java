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

}