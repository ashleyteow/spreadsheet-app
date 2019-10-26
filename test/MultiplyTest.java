import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Multiply;
import edu.cs3500.spreadsheets.model.Operation;
import java.util.ArrayList;
import org.junit.Test;

public class MultiplyTest {

  @Test
  public void operate() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(new Cell(new Coord(1,1), "87"));
    cells.add(new Cell(new Coord(2,1), "3"));
    Multiply prod = new Multiply(cells);
    prod.operate();
    assertEquals(261.0,prod.getProduct(), 0.0001);
  }
}