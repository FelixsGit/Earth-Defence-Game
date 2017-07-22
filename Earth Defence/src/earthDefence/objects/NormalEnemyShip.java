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
import earthDefence.objects.PlayerShip.CURRENTLEVEL;
import earthDefence.window.Animation;
import earthDefence.window.Game;
import earthDefence.window.Game.GAMESTATE;
import earthDefence.window.Handler;


public class NormalEnemyShip extends GameObject{

	private Handler handler;
	public static int MAXSPEED = 5;
	private Texture tex = Game.getInstance();
	public static boolean SHOOTING = false;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	public boolean death = false;
	File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private boolean remove = false;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	PlayerShip playerShip;
	public static int normalShipSpeed = 4;
	public static int normalEnemyShipDamageTaken = 6;
	public static boolean makeHarder = false;
	private int normalEnemyShipHealth = 150;
	
	private int chosenAlienShip = (int) ((Math.random() * 4) + 1);

	
	private Animation normalEnemyShipFly;
	private Animation normalEnemyShipExplosion;
	private Animation turtleEnemyShipFly;
	private Animation ninjaEnemyShipFly;
	private Animation lighterEnemyShipFly;
	private Animation alienCrashIntoEarth;
	
	public NormalEnemyShip(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		velY = normalShipSpeed;
		normalEnemyShipFly = new Animation(2, tex.NormalEnemyShip[0], tex.NormalEnemyShip[1],tex.NormalEnemyShip[2], tex.NormalEnemyShip[3]);
		normalEnemyShipExplosion = new Animation(4, tex.NormalEnemyShipExplosion[0], tex.NormalEnemyShipExplosion[1]);
		turtleEnemyShipFly = new Animation(5, tex.TurtleEnemyShip[0], tex.TurtleEnemyShip[1], tex.TurtleEnemyShip[2], tex.TurtleEnemyShip[3]);
		ninjaEnemyShipFly = new Animation(5, tex.NinjaEnemyShip[0], tex.NinjaEnemyShip[1], tex.NinjaEnemyShip[2], tex.NinjaEnemyShip[3]);
		lighterEnemyShipFly = new Animation(2, tex.LighterEnemyShip[0], tex.LighterEnemyShip[1], tex.LighterEnemyShip[2], tex.LighterEnemyShip[3]);
		alienCrashIntoEarth = new Animation(3, tex.AlienCrashIntoEarthBlood[0], tex.AlienCrashIntoEarthBlood[1], tex.AlienCrashIntoEarthBlood[2]);
		
	}

	public void tick(LinkedList<GameObject> object) {
	
		//Difficulty settings for diffrent Levels
		
		y += velY;
		
		normalEnemyShipFly.runAnimation();
		normalEnemyShipExplosion.runAnimation();
		turtleEnemyShipFly.runAnimation();
		ninjaEnemyShipFly.runAnimation();
		lighterEnemyShipFly.runAnimation();
		alienCrashIntoEarth.runAnimation();
		
		collision(object);
		
		if(normalEnemyShipHealth <= 0){
			setDeath(true);
			velY = 0;
		}
		if(normalEnemyShipHealth<= 0 && deathSound == false){
			Game.playSound(normalEnemyExplosionSound, true);
		}
		if(getDeath()){
			normalEnemyShipHealth = 0;
			velY = 0;
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
						normalEnemyShipHealth -= normalEnemyShipDamageTaken;
						if(normalEnemyShipHealth <= 0){
							normalEnemyShipHealth = 0;
						}
					}
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.NormalEnemyShip){
				if(tempObject.getExploded()){
					if(remove == true){
						remove = false;
						handler.removeObject(tempObject);
						PlayerShip.POINTS++;
						tempObject.setExploded(false);
					}
				}
			}
		}
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
	}

	public void render(Graphics g) {
		
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.NormalEnemyShip){
				if(tempObject.getY() >= Game.HEIGHT - 60){
					alienCrashIntoEarth.drawAnimations(g, (int) tempObject.getX(), (int) tempObject.getY(), 64, 64);
				}
			}
		}
		if(!getDeath()){
			if(chosenAlienShip == 1){
				normalEnemyShipFly.drawAnimations(g, (int) x, (int) y , 64, 64);
			}
			if(chosenAlienShip == 2){
				turtleEnemyShipFly.drawAnimations(g, (int) x, (int) y , 64, 64);
			}
			if(chosenAlienShip == 3){
				ninjaEnemyShipFly.drawAnimations(g, (int) x, (int) y , 64, 64);
			}
			if(chosenAlienShip == 4){
				lighterEnemyShipFly.drawAnimations(g, (int) x, (int) y , 64, 64);
			}
			
		}
		if(getDeath() && (explosionTimer < 2000)){
			deathSound = true;
			explosionTimer++;
			normalEnemyShipExplosion.drawAnimations(g, (int) x - 30, (int) y - 30 , 128, 128);
		}
		if(getDeath() && explosionTimer >= 2000){
			setExploded(true); 
			remove = true;
			deathSound = false;
			explosionTimer = 0;
		}
		
		g.setColor(Color.black);
		g.drawRect((int) x - 8, (int) y - 10, 80, 10);
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x - 8, (int) y - 10, 80, 10);
		g.setColor(Color.GREEN);
		g.setFont(fnt1);
		
		g.fillRect((int) x - 8, (int) y - 10, (int) (normalEnemyShipHealth/1.875), 10);

		
	
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.draw(getBounds());
		*/
		
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);	
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
		NormalEnemyShip.MAXSPEED = maxSpeed;
	}

	public void setExploded(boolean exploded){
		this.exploded = exploded;
	}
	public boolean getExploded() {
		return exploded;
	}
}