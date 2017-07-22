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


public class Meteor extends GameObject{

	private Handler handler;
	private Texture tex = Game.getInstance();
	public static boolean SHOOTING = false;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	private boolean death = false;
	File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private boolean remove = false;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	PlayerShip playerShip;
	private boolean orbit = false;

	private Animation bigMeteorFlying;
	private Animation playerShipSmoking;
	private Animation normalEnemyShipExplosion;
	
	public Meteor(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		bigMeteorFlying = new Animation(15, tex.MeteorFlying[0], tex.MeteorFlying[1], tex.MeteorFlying[2], tex.MeteorFlying[3]);
		playerShipSmoking = new Animation(10, tex.PlayerShipSmoking[0], tex.PlayerShipSmoking[1],tex.PlayerShipSmoking[2]);
		normalEnemyShipExplosion = new Animation(4, tex.NormalEnemyShipExplosion[0], tex.NormalEnemyShipExplosion[1]);

	}

	public void tick(LinkedList<GameObject> object) {
		
		bigMeteorFlying.runAnimation();
		playerShipSmoking.runAnimation();
		normalEnemyShipExplosion.runAnimation();
		
		
		if(BossEnemyShip.waitForMoonExploding >= 15000){
			y += velY;
			x += velX;

			float dx =(Game.WIDTH/2 - 200) - getX(), dy = (Game.HEIGHT - 390) - getY();
			float distance = (float)Math.sqrt(dx*dx + dy*dy);
			velX = (float) (dx * 3 / distance);
			velY = (float) (dy * 3 / distance);
			
			if(distance < 2){
				velX = 0;
				velY = 0;
				orbit = true;
			}
			
		}
		if(orbit == true){
			velY = 0;
			velX = -1;
		}

		collision(object);
	
	}
	
	public void collision(LinkedList<GameObject> object){

		//Removing Bullets if they are hitting and reducing health on enemy ship
		if(PlayerShip.currentLevel == 6){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.BadPlayerBullet){
					if(tempObject.getBounds().intersects(getBounds())){
						handler.removeObject(tempObject);	
					}
				}
			}	
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.BossBullet){
					if(tempObject.getBounds().intersects(getBounds())){
						handler.removeObject(tempObject);
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		if (BossEnemyShip.waitForMoonExploding >= 15000){
			
			bigMeteorFlying.drawAnimations(g, (int) x, (int) y , 150, 150);
		}
		
		if(getX() <= -120){
			setX(640);
		}
		


		
		/*
		//Healthbar
		g.setColor(Color.black);
		g.drawRect((int) x -2, (int) y - 14, 50, 10);
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x -2 , (int) y - 14, 50, 10);
		g.setColor(Color.GREEN);
		g.setFont(fnt1);
		g.fillRect((int) x - 2, (int) y - 14, (int) (HEALTH*1.2), 10);
		*/
	
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		*/
		
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 150, 150);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x + 10, (int) y + 10 , 110, 110);	
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