package lightsim;

import processing.core.*;

public class Ray {
	public PApplet papp;
	
  public Vec2D start;
  public Vec2D end;
  public Mirror mirrorToNotCollide = null;
  
  public Ray(PApplet pa, double x, double y, double dx, double dy, Mirror m) {
  	papp = pa;
  	
  	start = new Vec2D(x, y);
  	end = Vec2D.add(Vec2D.mult(new Vec2D(dx, dy), Main.MAX_RAY_SIZE), start);
  	mirrorToNotCollide = m;
  }
  
  public Vec2D checkMirror(Mirror m) {
  	double den = (m.p0.x-m.p1.x)*(start.y-end.y)-(m.p0.y-m.p1.y)*(start.x-end.x);
  	double t = ((m.p0.x-start.x)*(start.y-end.y)-(m.p0.y-start.y)*(start.x-end.x))/den;
  	double u = ((m.p1.x-m.p0.x)*(m.p0.y-start.y)-(m.p1.y-m.p0.y)*(m.p0.x-start.x))/den;
    if(m!=mirrorToNotCollide&&t>0&&t<1&&u>0&&u<1){
      return new Vec2D(m.p0.x+t*(m.p1.x-m.p0.x), m.p0.y+t*(m.p1.y-m.p0.y));
    }
  	return null;
  }
}
