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
import earthDefence.window.Game.GAMESTATE;


public class BossEnemyShip extends GameObject{

	private Handler handler;
	private Texture tex = Game.getInstance();
	public static int HEALTH = 20000;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	private boolean death = false;
	private File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private File victoryMusic = new File("res/VictoryMusic.wav");
	private File gameMusic = new File("res/GameMusic.wav");
	private boolean remove = false;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	PlayerShip playerShip;
	public static int waitForMoonExploding = 0;
	private int bossEnemyShipDamageTaken = NormalEnemyShip.normalEnemyShipDamageTaken;
	private int shootingSuicideShipTimer = 0;
	private int shipCount = 0;
	public static boolean attackWithBullet = false;
	private int attackWithBulletTimer = 0;
	public static boolean bossShipIsExploding = false;
	private boolean switchToVictory;
	

	private Animation playerShipSmoking;
	private Animation moonExploding;
	private Animation bossEnemyShip;
	
	public BossEnemyShip(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		moonExploding = new Animation(1, tex.MoonExploding[0], tex.MoonExploding[1], tex.MoonExploding[2], tex.MoonExploding[3], tex.MoonExploding[4], tex.MoonExploding[5], tex.MoonExploding[6], tex.MoonExploding[7], tex.MoonExploding[8], tex.MoonExploding[9], tex.MoonExploding[10]);  
		bossEnemyShip = new Animation(1, tex.BossEnemyShip[0], tex.BossEnemyShip[1], tex.BossEnemyShip[2], tex.BossEnemyShip[3]);
		playerShipSmoking = new Animation(10, tex.PlayerShipSmoking[0], tex.PlayerShipSmoking[1],tex.PlayerShipSmoking[2]);

	}

	public void tick(LinkedList<GameObject> object) {
		
		moonExploding.runAnimation();
		bossEnemyShip.runAnimation();
		playerShipSmoking.runAnimation();
		
		if(HEALTH <= 12000 ){
			shootingSuicideShipTimer++;
		}
		
		if(PlayerShip.currentLevel == 6 && BossEnemyShip.waitForMoonExploding == 25000){
			//attacking with Suicide Ships
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.BossEnemyShip){
					if(shootingSuicideShipTimer == 15 && shipCount < 15){
						handler.addObject(new SuicideEnemyShip((int) x + 115, (int) y + 250, handler, ObjectId.SuicideEnemyShip));
						shootingSuicideShipTimer = 0;
						shipCount++;	
					}	
				}
			}
			//attacking with boss bullets
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.BossEnemyShip){
					attackWithBulletTimer++;
					if(attackWithBulletTimer >= 210 && attackWithBulletTimer <= 215){
						if(attackWithBulletTimer == 215){
							attackWithBulletTimer = 0;
						}
						attackWithBullet = true;
						handler.addObject(new BossBullet((int) x + 115, (int) y + 250, handler, ObjectId.BossBullet));
					}
				}
			}
		}

		
		
		if(waitForMoonExploding >= 7000){
			y += velY;
			x += velX;
			
			float dx =(Game.WIDTH/2 - 124) - getX(), dy = 100 - getY();
			float distance = (float)Math.sqrt(dx*dx + dy*dy);
			velX = (float) (dx * 1.5 / distance);
			velY = (float) (dy * 1.5 / distance);
			
			if(distance < 2){
				velX = 0;
				velY = 0;
			}
			
		}
		
		collision(object);
			
		if(HEALTH <= 0){
			setDeath(true);
			velY = 0;
		}
		
		if(HEALTH <= 200){
			velY = 3;
			velX = (float) 0.4;
		}
		
		//change to falling down out off view
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
					if(!death){
						handler.removeObject(tempObject);
						HEALTH -= bossEnemyShipDamageTaken;
						if(HEALTH <= 0){
							HEALTH = 0;
						}
					}
				}
			}
		}
		//Remvoing everything from the playfield after boss death
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if((tempObject.getId() == ObjectId.NormalEnemyShip || tempObject.getId() == ObjectId.SuicideEnemyShip || tempObject.getId() == ObjectId.MineEnemyShip || tempObject.getId() == ObjectId.BossBullet) && bossShipIsExploding){
				handler.removeObject(tempObject);
			}
		}
		//Boss death
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.BossEnemyShip){
				if(tempObject.getExploded()){
					if(remove == true){
						handler.removeObject(tempObject);
						tempObject.setExploded(false);
						Game.state = GAMESTATE.VICTORY;
						PlayerShip.POINTS = 500;
						remove = false;
						
					}
				}
			}
		}
		
	}

	public void render(Graphics g) {
		
		if(HEALTH < 5000 && !getDeath()){
			playerShipSmoking.drawAnimations(g, (int) x, (int) y - 150 , 342, 278);
			playerShipSmoking.drawAnimations(g, (int) x, (int) y - 150, 279, 289);
			playerShipSmoking.drawAnimations(g, (int) x, (int) y - 150, 310, 395);
			playerShipSmoking.drawAnimations(g, (int) x, (int) y - 150, 360, 356);
		}
		
		if(PlayerShip.currentLevel == 6){
			if(waitForMoonExploding < 25000){
				waitForMoonExploding++;
			}
		}
		
		if(waitForMoonExploding >= 15000 && waitForMoonExploding < 20000){
			moonExploding.drawAnimations(g, 300, -140 , 512, 512);
		}
		
		if(waitForMoonExploding == 15000 || waitForMoonExploding == 17500 || waitForMoonExploding == 19500){
			Game.playSound(normalEnemyExplosionSound, true);
		}
			
		if(waitForMoonExploding >= 20000){
			SuicideEnemyShip.MAXSPEED = 10;
			if(!getDeath()){
				bossEnemyShip.drawAnimations(g, (int) x, (int) y , 256, 256);
			}
			
			if(getDeath() && (explosionTimer < 10000)){
				if(explosionTimer == 0){
					switchToVictory = true;
				}
				bossShipIsExploding = true;
				deathSound = true;
				explosionTimer++;
				moonExploding.drawAnimations(g, (int) x - 100 , (int) y - 100 , 500, 500);
			}
			if(getDeath() && explosionTimer >= 10000){
				setExploded(true); 
				remove = true;
				deathSound = false;
				explosionTimer = 0;
			}		
			//Healthbar
			g.setColor(Color.black);
			g.drawRect((int) x + 25 , (int) y - 14, 200, 10);
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int) x + 25 , (int) y - 14, 200, 10);
			g.setColor(Color.GREEN);
			g.setFont(fnt1);
			g.fillRect((int) x + 25, (int) y - 14, (int) (HEALTH/100), 10);
		}


		if(switchToVictory && explosionTimer == 1 && !remove){
			//System.out.println("play end");
			Game.playMusic(gameMusic, false);
			Game.playMusic(victoryMusic, true);
			switchToVictory = false;
		}
		

		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		*/
		
	}

	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x + 20, (int) y + 30 , 190, 230);	
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