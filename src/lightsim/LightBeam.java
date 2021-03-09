package lightsim;

import processing.core.*;
import java.util.ArrayList;

public class LightBeam {
	public PApplet papp;
	
  public ArrayList<Ray> rays;
  public int col;
  
  public LightBeam(PApplet pa, float x, float y, float lx, float ly) {
  	papp = pa;
  	rays = new ArrayList<Ray>();
  	rays.add(new Ray(papp, x, y, lx, ly, null));
  	col = papp.color(0);
  }
  
  public void update(ArrayList<Mirror> ms) {
  	col = papp.color(papp.random(255), papp.random(255), papp.random(255));
  	double record = Float.POSITIVE_INFINITY;
  	Mirror mirrorToReflect = null;
  	Vec2D intersectPoint = null;
  	for(Mirror m:ms) {
  		Vec2D mirrorIntersectPoint = rays.get(rays.size()-1).checkMirror(m);
  		if(mirrorIntersectPoint!=null) {
  			Vec2D pt = rays.get(rays.size()-1).start;
  			double d = Math.hypot(mirrorIntersectPoint.x-pt.x, mirrorIntersectPoint.y-pt.y);
  			if(d<record) {
  				record = d;
  				mirrorToReflect = m;
  				intersectPoint = mirrorIntersectPoint;
  			}
  		}
  	}
  	if(intersectPoint!=null) {
  		Vec2D reflected = Vec2D.add(Vec2D.mult(mirrorToReflect.reflect(rays.get(rays.size()-1).start, intersectPoint), Main.MAX_RAY_SIZE), intersectPoint);
  		rays.get(rays.size()-1).end = intersectPoint;
  		rays.add(new Ray(papp, intersectPoint.x, intersectPoint.y, reflected.x, reflected.y, mirrorToReflect));
  	}
  }
  
  public void show() {
  	papp.push();
  	papp.noFill();
  	papp.stroke(col);
  	papp.beginShape();
  	for(int i=0;i<rays.size();i++) {
  		Ray r = rays.get(i);
  		papp.vertex((float)r.start.x, (float)r.start.y);
  		if(i<Main.MAX_RAY_SIZE-1) {
  	    papp.vertex((float)r.end.x, (float)r.end.y);
  		}
  	}
  	papp.endShape();
  	papp.pop();
  }
}
