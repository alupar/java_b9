package ru.stqa.pft.sandbox;

public class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  double distance(int х, int у) {
    int dx = this.x - х;
    int dy = this.y - у;
    return Math.sqrt(dx * dx + dy * dy);
  }

  double distance(Point p) {
    return distance(p.x, p.y);
  }


}
