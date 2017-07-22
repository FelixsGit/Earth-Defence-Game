package earthDefence.objects;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.ObjectId;
import earthDefence.frameWorks.Texture;
import earthDefence.window.Animation;
import earthDefence.window.Game;
import earthDefence.window.Handler;


public class RandomMeteorsOne extends GameObject{

	private Handler handler;
	private Texture tex = Game.getInstance();
	PlayerShip playerShip;
	private final Random random = new Random();
	int direction = random.nextInt(150) + 16;
	
	private Animation bigMeteorFlying;

	
	public RandomMeteorsOne(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		bigMeteorFlying = new Animation(15, tex.MeteorFlying[0], tex.MeteorFlying[1], tex.MeteorFlying[2], tex.MeteorFlying[3]);

	}

	public void tick(LinkedList<GameObject> object) {
		
		x += velX;
		y += velY;

		if(BossEnemyShip.waitForMoonExploding >= 17000){
			velY = 0;
			velX = -1;
		}
		
		bigMeteorFlying.runAnimation();
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
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.RandomMeteorsOne){
				if(tempObject.getX() <= -350){
					handler.removeObject(tempObject);
				}
			}
		}	
	}

	public void render(Graphics g) {
		if(BossEnemyShip.waitForMoonExploding >= 17000){
			bigMeteorFlying.drawAnimations(g, (int) x, (int) y , direction, direction);
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