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
}