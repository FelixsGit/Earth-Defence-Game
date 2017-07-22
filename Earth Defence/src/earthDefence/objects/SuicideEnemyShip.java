package earthDefence.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.ObjectId;
import earthDefence.frameWorks.Texture;
import earthDefence.window.Animation;
import earthDefence.window.Game;
import earthDefence.window.Handler;


public class SuicideEnemyShip extends GameObject{

	private Handler handler;
	public static int MAXSPEED = 5;
	private Texture tex = Game.getInstance();
	public static boolean SHOOTING = false;
	private int HEALTH = 100;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	private boolean death = false;
	File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private boolean remove = false;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	PlayerShip playerShip;
	private int suicideSpeed = 12;
	public static int suicideEnemyShipDamageTaken = 3;

	
	private Animation suicideEnemyShipFly;
	private Animation suicideEnemyShipExplosion;
	
	public SuicideEnemyShip(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		suicideEnemyShipFly = new Animation(2, tex.SuicideEnemyShip[0], tex.SuicideEnemyShip[1],tex.SuicideEnemyShip[2], tex.SuicideEnemyShip[3]);
		suicideEnemyShipExplosion = new Animation(4, tex.SuicideEnemyShipExplosion[0], tex.SuicideEnemyShipExplosion[1]);
	}

	public void tick(LinkedList<GameObject> object) {
		
		if(PlayerShip.currentLevel == 6){
			suicideSpeed = 5;
		}
		y += velY;
		x += velX;
		suicideEnemyShipFly.runAnimation();
		suicideEnemyShipExplosion.runAnimation();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayerShip){
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
				velX = (float) (dx * suicideSpeed / distance);
				velY = (float) (dy * suicideSpeed / distance);
			}
		}
		collision(object);
		
		if(PlayerShip.currentLevel == 6){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.Meteor){
					if(tempObject.getBounds().intersects(getBounds())){
						setDeath(true);
					}
				}
			}
		}
		if(HEALTH <= 0){
			setDeath(true);
			velY = 0;
			velX = 0;
		}
		if(HEALTH <= 0 && deathSound == false){
			Game.playSound(normalEnemyExplosionSound, true);
		}
		if(getDeath()){
			HEALTH = 0;
			velY = 0;
			velX = 0;
		}
	}
	
	public void collision(LinkedList<GameObject> object){

		//Removing Bullets if they are hitting and reducing health on enemy ship
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.BadPlayerBullet){
				if(tempObject.getBounds().intersects(getBounds())){
					if(!getDeath()){
						handler.removeObject(tempObject);
						HEALTH -= suicideEnemyShipDamageTaken;
						if(HEALTH <= 0){
							HEALTH = 0;
						}
					}
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.SuicideEnemyShip){
				if(tempObject.getExploded()){
					if(remove == true){
						remove = false;
						Game.playSound(normalEnemyExplosionSound, true);
						handler.removeObject(tempObject);
						PlayerShip.POINTS++;
						tempObject.setExploded(false);
						
					}
				}
			}
		}
		
	}

	public void render(Graphics g) {
		if(!getDeath()){
			suicideEnemyShipFly.drawAnimations(g, (int) x, (int) y , 45, 45);
		}
		if(getDeath() && (explosionTimer < 2000)){
			deathSound = true;
			explosionTimer++;
			suicideEnemyShipExplosion.drawAnimations(g, (int) x - 30, (int) y - 30 , 128, 128);
		}
		if(getDeath() && explosionTimer >= 2000){
			setExploded(true); 
			remove = true;
			deathSound = false;
			explosionTimer = 0;
		}
		
		//Healthbar
		g.setColor(Color.black);
		g.drawRect((int) x -2, (int) y - 14, 50, 10);
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x -2 , (int) y - 14, 50, 10);
		g.setColor(Color.GREEN);
		g.setFont(fnt1);
		g.fillRect((int) x - 2, (int) y - 14, (int) (HEALTH/2), 10);
	
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		*/
		
		
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 45, 45);	
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 5, (int) y, 64 - 10, 10);
		
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 5, (int) y + 45, 64 - 10 , 10);
		
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x + 5, (int) y + 7, 5, 64 - 20);	
		
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + 54, (int) y + 7, 5, 64 - 20);	
	}

	public int getMaxSpeed() {
		return MAXSPEED;
	}

	public void setMaxSpeed(int maxSpeed) {
		SuicideEnemyShip.MAXSPEED = maxSpeed;
	}

	public void setExploded(boolean exploded){
		this.exploded = exploded;
	}
	public boolean getExploded() {
		return exploded;
	}
}