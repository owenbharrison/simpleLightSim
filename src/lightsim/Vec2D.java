package lightsim;

import java.lang.Math;

public class Vec2D {
  public double x = 0;
  public double y = 0;
  
  public Vec2D(double x_, double y_) {
  	x = x_;
  	y = y_;
  }
  
  public void add(Vec2D v) {
  	x+=v.x;
  	y+=v.y;
  }
  
  public void sub(Vec2D v) {
  	x-=v.x;
  	y-=v.y;
  }
  
  public void mult(double d) {
  	x *= d;
  	y *= d;
  }
  
  public double mag() {
  	return Math.sqrt(x*x+y*y);
  }
  
  public void normalize() {
  	double d = mag();
  	x /= d;
  	y /= d;
  }
  
  public double heading() {
  	return Math.atan2(y, x)%(2*Math.PI);
  }
  
  public static Vec2D fromAngle(double angle) {
  	return new Vec2D(Math.cos(angle), Math.sin(angle));
  }
  
  public static Vec2D add(Vec2D a, Vec2D b) {
  	return new Vec2D(a.x+b.x, a.y+b.y);
  }
  
  public static Vec2D sub(Vec2D a, Vec2D b) {
  	return new Vec2D(a.x-b.x, a.y-b.y);
  }
  
  public static Vec2D mult(Vec2D v, double d) {
  	return new Vec2D(v.x*d, v.y*d);
  }
}
