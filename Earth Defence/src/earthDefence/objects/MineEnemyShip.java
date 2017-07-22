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


public class MineEnemyShip extends GameObject{

	private Handler handler;
	private Texture tex = Game.getInstance();
	public static boolean SHOOTING = false;
	private int HEALTH = 40;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	private boolean death = false;
	File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private boolean remove = false;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	PlayerShip playerShip;
	public static int mineEnemyShipDamageTaken = 3;


	private Animation MineEnemyExplosion;
	private Animation MineEnemyShipFly;
	
	public MineEnemyShip(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		if(PlayerShip.currentLevel == 6){
			velY = 1;
		}
		else{
			velY = 2;
		}
		MineEnemyExplosion = new Animation(4, tex.MineEnemyExplosion[0], tex.MineEnemyExplosion[1]);
		MineEnemyShipFly = new Animation(2, tex.MineEnemyShip[0], tex.MineEnemyShip[1], tex.MineEnemyShip[2], tex.MineEnemyShip[3]);

	}

	public void tick(LinkedList<GameObject> object) {
		
		y += velY;

		collision(object);
		
		MineEnemyShipFly.runAnimation();
		MineEnemyExplosion.runAnimation();
		
		
		if(HEALTH <= 0){
			setDeath(true);
			velY = 0;
		}
		if(HEALTH <= 0 && deathSound == false){
			Game.playSound(normalEnemyExplosionSound, true);
		}
		
		if(getDeath()){
			HEALTH = 0;
			velY = 0;
		}
	}
	
	public void collision(LinkedList<GameObject> object){
		
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

		//Removing Bullets if they are hitting and reducing health on enemy ship
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.BadPlayerBullet){
				if(tempObject.getBounds().intersects(getBounds())){
					if(!death){
						handler.removeObject(tempObject);
						HEALTH -= mineEnemyShipDamageTaken;
						if(HEALTH <= 0){
							HEALTH = 0;
						}
					}
				}
			}
		}
		//if NormalEnemyShip is inside explosion it will explode
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.NormalEnemyShip){
				if(tempObject.getBounds().intersects(getExplosionBounds()) && getDeath()){
					tempObject.setDeath(true);
				}
			}
		}
		//if suicideEnemyShip is inside explosion it will explode
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.SuicideEnemyShip &&  getDeath()){
				if(tempObject.getBounds().intersects(getExplosionBounds())){
					tempObject.setDeath(true);
				}
			}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.BossEnemyShip &&  getDeath()){
				if(tempObject.getBounds().intersects(getExplosionBounds())){
					BossEnemyShip.HEALTH -= 20;
				}
			}
		}
		// if playerShip is inside explosion it will take damage
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayerShip &&  getDeath()){
				if(tempObject.getBounds().intersects(getExplosionBounds())){
					PlayerShip.shipHealth =- 50;
				}
			}
		}
		//if MineEnemeyShip is inside explosion it will explode
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.MineEnemyShip &&  getDeath()){
				if(tempObject.getBounds().intersects(getExplosionBounds())){
					tempObject.setDeath(true);
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.MineEnemyShip){
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
		
	}

	public void render(Graphics g) {
		if(!getDeath()){
			MineEnemyShipFly.drawAnimations(g, (int) x, (int) y , 45, 45);
		}
		if(getDeath() && (explosionTimer < 3000)){
			deathSound = true;
			explosionTimer++;
			MineEnemyExplosion.drawAnimations(g, (int) x - 150, (int) y - 150 , 300, 300);
		}
		if(getDeath() && explosionTimer >= 3000){
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
		g.fillRect((int) x - 2, (int) y - 14, (int) (HEALTH*1.2), 10);
	
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getExplosionBounds());
		*/
		
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
		
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
	public void setExploded(boolean exploded){
		this.exploded = exploded;
	}
	public boolean getExploded() {
		return exploded;
	}
}