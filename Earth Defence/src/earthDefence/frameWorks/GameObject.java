package earthDefence.frameWorks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected float x, y;
	protected float velX = 0 , velY = 0;
	protected ObjectId id;
	protected boolean exploded = false;
	protected boolean death = false;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;	
		this.id = id;
	}
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public ObjectId getId(){
		return id;
	}
	public boolean getExploded(){
		return exploded;
	}
	public void setExploded(boolean exploded){
		this.exploded = exploded;
	}
	
	public boolean getDeath(){
		return death;
	}
	public void setDeath(boolean death){
		this.death = death;
	}
	
}
