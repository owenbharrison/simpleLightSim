package lightsim;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet{
	public static final int MAX_RAY_SIZE = 2500;
	public static final int MAX_MIRROR_REFLECTIONS=25;
	
	public ArrayList<LightBeam> lightBeams;
	public ArrayList<Mirror> mirrors;
	public float noiseVal = 0;
	
  public static void main(String[] args) {
  	PApplet.main(new String[] {"lightsim.Main"});
  }
  
  public void settings() {
  	size(800, 800);
  }
  
  public void setup() {
  	lightBeams = new ArrayList<LightBeam>();
  	for(int i=0;i<16;i++) {
  	  lightBeams.add(new LightBeam(this, 200, 400, 0, 0));
  	}
  	mirrors = new ArrayList<Mirror>();
  	for(int i=0;i<4;i++) {
  	  mirrors.add(new Mirror(this, random(width), random(height), random(width), random(height)));
  	}
  }
  
  public void mouseWheel(processing.event.MouseEvent e){
  	int v = 3*-e.getCount();
  	if(v>0) {
  	 lightBeams.add(new LightBeam(this, 200, 400, 0, 0));
    }
  	if(v<0&&lightBeams.size()>1) {
  		lightBeams.remove(lightBeams.size()-1);
  	}
  }
  
  public void draw() {
  	background(30);
  	for(int i=0;i<lightBeams.size();i++) {
  		float angle = map(i, 0, lightBeams.size(), 0, PI*2);
  		Vec2D dir = Vec2D.fromAngle(angle);
  		lightBeams.set(i, new LightBeam(this, mouseX, mouseY, (float)dir.x, (float)dir.y));
  		LightBeam l = lightBeams.get(i);
  		for(int n=0;n<MAX_MIRROR_REFLECTIONS;n++) {
  		  l.update(mirrors);
  		}
  	  l.show();
  	}
  	for(Mirror m:mirrors)m.show();
  	surface.setTitle("LightSim ... "+round(frameRate)+"fps");
  }
}

