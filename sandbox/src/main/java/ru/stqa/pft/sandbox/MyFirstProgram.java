package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {


    Point p1 = new Point(-2, 2);
    Point p2 = new Point(2, -3);

    System.out.println("Вычислено с помощью функции " + distance(p1, p2));

    System.out.println("Вычислено с помощью метода " + p1.distance(p2));
  }

  public static double distance(Point p1, Point p2) {
    int dx = p2.x - p1.x;
    int dy = p2.y - p1.y;
    return Math.sqrt(dx * dx + dy * dy);
  }


}

