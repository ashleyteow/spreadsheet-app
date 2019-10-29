//import static org.junit.Assert.*;
//
//import edu.cs3500.spreadsheets.model.Cell;
//import edu.cs3500.spreadsheets.model.Coord;
//import edu.cs3500.spreadsheets.model.Multiply;
//
//import java.util.ArrayList;
//import org.junit.Test;
//
///**
// * Tests multiply/product functionality.
// */
//public class MultiplyTest {
//
//  @Test
//  public void testProductWithOnlyNumbers() {
//    ArrayList<Cell> cells = new ArrayList<Cell>();
//    cells.add(new Cell(new Coord(1,1), "87"));
//    cells.add(new Cell(new Coord(2,1), "3"));
//    Multiply prod = new Multiply(cells);
//    prod.operate();
//    assertEquals(261.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test
//  public void testProductWithNumbersAndStrings() {
//    ArrayList<Cell> cells = new ArrayList<Cell>();
//    cells.add(new Cell(new Coord(1,1), "hello"));
//    cells.add(new Cell(new Coord(2,1), "3"));
//    cells.add(new Cell(new Coord(2,1), "cake"));
//    cells.add(new Cell(new Coord(4, 2), "6"));
//    Multiply prod = new Multiply(cells);
//    prod.operate();
//    assertEquals(18.0, prod.getProduct(), 0.0001);
//  }
//
//  @Test (expected = IllegalArgumentException.class)
//  public void testProductWithInvalidSexp() {
//    ArrayList<Cell> cells = new ArrayList<>();
//    cells.add(new Cell(new Coord(2, 4), "\"|hello"));
//    cells.add(new Cell(new Coord(4, 5), "5"));
//    cells.add(new Cell(new Coord(5, 6), "5"));
//    Multiply prod = new Multiply(cells);
//    prod.operate();
//  }
//
//}