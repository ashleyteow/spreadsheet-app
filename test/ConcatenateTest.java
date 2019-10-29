import static org.junit.Assert.*;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Concatenate;
import java.util.ArrayList;
import org.junit.Test;

public class ConcatenateTest {

  @Test
  public void testConcatWithStrings() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(new Cell(new Coord(1,1), "hello"));
    cells.add(new Cell(new Coord(2,1), "i"));
    cells.add(new Cell(new Coord(2,1), "am"));
    cells.add(new Cell(new Coord(2,1), "ashley"));
    Concatenate concat = new Concatenate(cells);
    concat.operate();
    assertEquals("helloiamashley", concat.getStr());
  }

  @Test
  public void testConcatWithNumbers() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(new Cell(new Coord(1,1), "3"));
    cells.add(new Cell(new Coord(2,1), "4"));
    cells.add(new Cell(new Coord(2,1), "6"));
    cells.add(new Cell(new Coord(2,1), "9"));
    Concatenate concat = new Concatenate(cells);
    concat.operate();
    assertEquals("3469", concat.getStr());
  }

  @Test
  public void testSumWithNumbersAndStrings() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(new Cell(new Coord(1,1), "hello"));
    cells.add(new Cell(new Coord(2,1), "3"));
    cells.add(new Cell(new Coord(2,1), "cake"));
    cells.add(new Cell(new Coord(4, 2), "6"));
    Concatenate concat = new Concatenate(cells);
    concat.operate();
    assertEquals("hello3cake6", concat.getStr());
  }

}