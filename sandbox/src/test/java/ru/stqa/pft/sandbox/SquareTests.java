package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test

  public void testPoint() {
    Point p1 = new Point(2, 2);
    Point p2 = new Point(2, -3);

    Point p3 = new Point(0, 4);
    Point p4 = new Point(-4, 0);

    Assert.assertEquals( p1.distance(p2), 5.0);
    Assert.assertEquals( p3.distance(p4), 	5.656854249492381);
  }
}
