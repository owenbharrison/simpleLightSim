package lightsim;

import processing.core.*;

public class Mirror {
  public PApplet papp;
  
  public Vec2D p0, p1;
  
  public Mirror(PApplet pa, double x0, double y0, double x1, double y1) {
  	papp = pa;
  	p0 = new Vec2D(x0, y0);
  	p1 = new Vec2D(x1, y1);
  }
  
  public void show() {
  	papp.push();
  	papp.stroke(190);
  	papp.strokeWeight(4);
  	papp.line((float)p0.x, (float)p0.y, (float)p1.x, (float)p1.y);
  	papp.pop();
  }
  
  public Vec2D reflect(Vec2D rayPt, Vec2D intersectPt) {
  	Vec2D ext = Vec2D.add(Vec2D.sub(intersectPt, rayPt), intersectPt);
  	double normalAngle = Vec2D.sub(p0, p1).heading()+Math.PI/2;
    if(onLeftSide(rayPt)==onLeftSide(Vec2D.add(Vec2D.fromAngle(normalAngle), p0)))normalAngle+=Math.PI;
    Vec2D v = Vec2D.add(Vec2D.fromAngle(normalAngle), ext);
    double t = ((p0.x-ext.x)*(ext.y-v.y)-(p0.y-ext.y)*(ext.x-v.x))/((p0.x-p1.x)*(ext.y-v.y)-(p0.y-p1.y)*(ext.x-v.x));
    Vec2D ip = new Vec2D(p0.x+t*(p1.x-p0.x), p0.y+t*(p1.y-p0.y));
    Vec2D ext2 = Vec2D.add(Vec2D.sub(ip, ext), ip);
  	Vec2D val = Vec2D.sub(ext2, intersectPt);
  	val.normalize();
  	return val;
  }
  
  public Vec2D newReflect(Vec2D rayPt, Vec2D intersectPt) {
  	double angle = Vec2D.sub(p0, p1).heading();
  	Vec2D a = intersectPt.copy();
  	Vec2D b = Vec2D.fromAngle(angle+PApplet.PI/2).add(intersectPt);
  	Vec2D c = rayPt;
  	Vec2D d = Vec2D.fromAngle(angle).add(rayPt);
  	
  	double den = (a.x-b.x)*(c.y-d.y)-(a.y-b.y)*(c.x-d.x);
  	double t = ((a.x-c.x)*(c.y-d.y)-(a.y-c.y)*(c.x-d.x))/den;
  	Vec2D cross = new Vec2D(a.x+t*(b.x-a.x), a.y+t*(b.y-a.y));
  	Vec2D ext = Vec2D.sub(cross, rayPt).add(cross);
  	return Vec2D.sub(ext, intersectPt).normalize();
  }
  
  public boolean onLeftSide(Vec2D a){
    return (a.x-p0.x)*(p1.y-p0.x)-(a.y-p0.y)*(p1.x-p0.x)>0;
  }
}
