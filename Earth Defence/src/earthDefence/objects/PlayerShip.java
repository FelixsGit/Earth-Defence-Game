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


public class PlayerShip extends GameObject{

	private Handler handler;
	public static int playerShipSpeed = (int) 7.5;
	private Texture tex = Game.getInstance();
	public static boolean SHOOTING = false;
	private File playerShipShootingSound = new File("res/PlayerShipShootingSound.wav");
	private File ailenReachEarthSound = new File("res/AlienReachEarthSound.wav");
	private File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	private File levelUpSound= new File("res/LevelUpSound.wav");
	public static double earthPopulation = 200;
	private int humansKilled = 20;
	private Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 15);
	public static int shipHealth = 200;
	private boolean readyToPlaySound = true;
	private boolean shipExplosion = false;
	private int explosionTimer = 0;
	private boolean deathSound = false;
	public static boolean timeToUpgrade = false;
	public static  int suicideEnemyShipDamage = 50;
	public static int normalEnemyShipDamage = 30;
	public static int mineEnemyShipDamage = 100;
	public static float meteorDamage = (float) 0.5;
	public static boolean upgradeSpeed = false;
	public static boolean upgradeArmor = false;
	public static boolean upgradeWeapon = false;

	public static int POINTS = 0;
	private Animation playerShipFly;
	private Animation playerShipSmoking;
	private Animation upgradeFlashIcon;
	private Animation armorOnePlayerShipFly;
	private Animation armorTwoPlayerShipFly;
	private Animation armorThreePlayerShipFly;
	private Animation armorFourPlayerShipFly;
	private Animation moonExploding;
	private Animation bossEnemyShip;
	private Animation alienCrashIntoEarth;
	public static int currentLevel = 1;
	public static boolean crashIntoEarth = false;


	private static int POINTSTONEXTLEVEL;
	
	private File buttonClick = new File("res/ClickSound.wav");
	private File menuMusic = new File("res/MenuMusic.wav");
	private File gameMusic = new File("res/GameMusic.wav");
	private File gameOverMusic = new File("res/GameOverMusic.wav");
	private File suicideShipSpawn = new File("res/SuicideEnemyShipSpawnSound.wav");



	
	public PlayerShip(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		armorOnePlayerShipFly = new Animation(2, tex.PlayerShip[16], tex.PlayerShip[17],tex.PlayerShip[18], tex.PlayerShip[19]);
		armorTwoPlayerShipFly = new Animation(2, tex.PlayerShip[36], tex.PlayerShip[37],tex.PlayerShip[38], tex.PlayerShip[39]);
		armorThreePlayerShipFly = new Animation(2, tex.PlayerShip[56], tex.PlayerShip[57],tex.PlayerShip[58], tex.PlayerShip[58]);
		armorFourPlayerShipFly = new Animation(2, tex.PlayerShip[76], tex.PlayerShip[77],tex.PlayerShip[78], tex.PlayerShip[79]);
		moonExploding = new Animation(2, tex.MoonExploding[0], tex.MoonExploding[1], tex.MoonExploding[2], tex.MoonExploding[3], tex.MoonExploding[4], tex.MoonExploding[5], tex.MoonExploding[6], tex.MoonExploding[7], tex.MoonExploding[8], tex.MoonExploding[9], tex.MoonExploding[10]);  
		bossEnemyShip = new Animation(2, tex.BossEnemyShip[0], tex.BossEnemyShip[1], tex.BossEnemyShip[2], tex.BossEnemyShip[3]);
		alienCrashIntoEarth = new Animation(2, tex.AlienCrashIntoEarthBlood[0], tex.AlienCrashIntoEarthBlood[1], tex.AlienCrashIntoEarthBlood[2]);
		
		
		
		playerShipSmoking = new Animation(10, tex.PlayerShipSmoking[0], tex.PlayerShipSmoking[1],tex.PlayerShipSmoking[2]);
		upgradeFlashIcon = new Animation(5, tex.UpgradeFlashIcon[0], tex.UpgradeFlashIcon[1], tex.UpgradeFlashIcon[2]);

	}
	
	public static enum CURRENTLEVEL{
		
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
	}

	public static CURRENTLEVEL level = CURRENTLEVEL.ONE;

	public void tick(LinkedList<GameObject> object) {

		
		if(NormalEnemyShip.makeHarder){
			//Buffs for ENEMY each lvl
			if(PlayerShip.currentLevel == 1){
				suicideEnemyShipDamage = suicideEnemyShipDamage += 0;
				normalEnemyShipDamage = normalEnemyShipDamage += 0;
				NormalEnemyShip.normalEnemyShipDamageTaken = NormalEnemyShip.normalEnemyShipDamageTaken += 0;
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 0;
				//System.out.println("level (1) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (1) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			if(PlayerShip.currentLevel == 2) {
				suicideEnemyShipDamage = suicideEnemyShipDamage += 3;//3
				normalEnemyShipDamage = normalEnemyShipDamage += 3;//3
				SuicideEnemyShip.suicideEnemyShipDamageTaken -= 2;
				NormalEnemyShip.normalEnemyShipDamageTaken -= 2;
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 0;
				//System.out.println("level (2) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (2) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			if(PlayerShip.currentLevel == 3){
				suicideEnemyShipDamage = suicideEnemyShipDamage += 5; //5 
				normalEnemyShipDamage = normalEnemyShipDamage += 5; //5
				SuicideEnemyShip.suicideEnemyShipDamageTaken -= 1; //1
				NormalEnemyShip.normalEnemyShipDamageTaken -= 1; //3
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 1;
				//System.out.println("level (3) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (3) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			if(PlayerShip.currentLevel == 4){
				suicideEnemyShipDamage = suicideEnemyShipDamage += 2;
				normalEnemyShipDamage = normalEnemyShipDamage += 2;
				SuicideEnemyShip.suicideEnemyShipDamageTaken -= 1;
				NormalEnemyShip.normalEnemyShipDamageTaken -= 1;
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 1;
				//System.out.println("level (4) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (4) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			if(PlayerShip.currentLevel == 5 ){
				suicideEnemyShipDamage = suicideEnemyShipDamage += 5;
				normalEnemyShipDamage = normalEnemyShipDamage += 5;
				SuicideEnemyShip.suicideEnemyShipDamageTaken -= 1;
				NormalEnemyShip.normalEnemyShipDamageTaken -= 1;
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 1;
				//System.out.println("level (5) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (5) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			if(PlayerShip.currentLevel == 6 ){
				suicideEnemyShipDamage = suicideEnemyShipDamage += 0;
				normalEnemyShipDamage = normalEnemyShipDamage += 0;
				SuicideEnemyShip.suicideEnemyShipDamageTaken -= 0;
				NormalEnemyShip.normalEnemyShipDamageTaken -= 0;
				NormalEnemyShip.normalShipSpeed = NormalEnemyShip.normalShipSpeed += 0;
				//System.out.println("level (6) NormalEnemyShip now takes = " + NormalEnemyShip.normalEnemyShipDamageTaken + " each bullet " );
				//System.out.println("level (6) NormalEnemyShip SPEED = " + NormalEnemyShip.normalShipSpeed  );
			}
			NormalEnemyShip.makeHarder = false;
		}
	
		//Speed Level settings
		if(upgradeSpeed){
			if(Game.speedLevel == 1){
				//System.out.println("PlayerSpeed lvl 1 = " + playerShipSpeed);
				playerShipSpeed = (int) 7.5;
				upgradeSpeed = false;
			}
			if(Game.speedLevel == 2){
				playerShipSpeed = (int) 10;
				//System.out.println("PlayerSpeed lvl 2 = " + playerShipSpeed);
				upgradeSpeed = false;
			}
			if(Game.speedLevel == 3){
				playerShipSpeed = (int) 12.5;
				//System.out.println("PlayerSpeed lvl 3 = " + playerShipSpeed);
				upgradeSpeed = false;
			}
			if(Game.speedLevel == 4){
				playerShipSpeed = (int) 15;
				//System.out.println("PlayerSpeed lvl 4 = "  + playerShipSpeed);
				upgradeSpeed = false;
			}
		}
		//Defence Level settings
		if(upgradeArmor){
			if(Game.defenceLevel == 1){
				suicideEnemyShipDamage = suicideEnemyShipDamage - 0;
				normalEnemyShipDamage = normalEnemyShipDamage - 0;
				//System.out.println("Damage Taken (1) =  " + suicideEnemyShipDamage + "  ,"  +normalEnemyShipDamage);
				upgradeArmor = false;
			}
			if(Game.defenceLevel == 2){
				suicideEnemyShipDamage = suicideEnemyShipDamage - 30;
				normalEnemyShipDamage = normalEnemyShipDamage - 30;
				//System.out.println("Damage Taken (2) =  " + suicideEnemyShipDamage + "  ,"  +normalEnemyShipDamage);
				upgradeArmor = false;
			}
			if(Game.defenceLevel == 3){
				suicideEnemyShipDamage = (int) (suicideEnemyShipDamage - 5);
				normalEnemyShipDamage = (int) (normalEnemyShipDamage - 5);
				//System.out.println("Damage Taken (3) =  " + suicideEnemyShipDamage + "  ,"  +normalEnemyShipDamage);
				upgradeArmor = false;
			}
			if(Game.defenceLevel == 4){
				suicideEnemyShipDamage = suicideEnemyShipDamage - 5;
				normalEnemyShipDamage = normalEnemyShipDamage - 5;
				//System.out.println("Damage Taken (4) =  " + suicideEnemyShipDamage + " ,"  +normalEnemyShipDamage);
				upgradeArmor = false;
			}
		}
		
		if(upgradeWeapon){
			//Weapon Level settings
			if(Game.weaponLevel == 1){
				NormalEnemyShip.normalEnemyShipDamageTaken = 6;
				SuicideEnemyShip.suicideEnemyShipDamageTaken = 6;
				//System.out.println("weapon lvl (1) damage taken = " + NormalEnemyShip.normalEnemyShipDamageTaken + ", " + SuicideEnemyShip.suicideEnemyShipDamageTaken);
				upgradeWeapon = false;
			}
			if(Game.weaponLevel == 2){
				NormalEnemyShip.normalEnemyShipDamageTaken = NormalEnemyShip.normalEnemyShipDamageTaken += 6;
				SuicideEnemyShip.suicideEnemyShipDamageTaken = SuicideEnemyShip.suicideEnemyShipDamageTaken += 6;
				//System.out.println("weapon lvl (2) damage taken = " + NormalEnemyShip.normalEnemyShipDamageTaken + ", " + SuicideEnemyShip.suicideEnemyShipDamageTaken);
				upgradeWeapon = false;
				BadPlayerBullet.BULLET_MAXSPEED = 25;
			}
			if(Game.weaponLevel == 3){
				NormalEnemyShip.normalEnemyShipDamageTaken = NormalEnemyShip.normalEnemyShipDamageTaken += 4;
				SuicideEnemyShip.suicideEnemyShipDamageTaken = SuicideEnemyShip.suicideEnemyShipDamageTaken += 4;
				//System.out.println("weapon lvl (3) damage taken  = " + NormalEnemyShip.normalEnemyShipDamageTaken + ", " + SuicideEnemyShip.suicideEnemyShipDamageTaken);
				upgradeWeapon = false;
			}
			if(Game.weaponLevel == 4){
				NormalEnemyShip.normalEnemyShipDamageTaken = NormalEnemyShip.normalEnemyShipDamageTaken += 3;
				SuicideEnemyShip.suicideEnemyShipDamageTaken = SuicideEnemyShip.suicideEnemyShipDamageTaken += 3;
				//System.out.println("weapon lvl (4) damage taken = " + NormalEnemyShip.normalEnemyShipDamageTaken + ", " + SuicideEnemyShip.suicideEnemyShipDamageTaken);
				upgradeWeapon = false;
			}
		}
		if(currentLevel == 1){
			POINTSTONEXTLEVEL = 20 - POINTS;
			level = CURRENTLEVEL.ONE;
		}
		if(currentLevel == 2){
			POINTSTONEXTLEVEL = 33 - POINTS;
			level = CURRENTLEVEL.TWO;
		}
		if(currentLevel == 3){
			POINTSTONEXTLEVEL = 50 - POINTS;
			level = CURRENTLEVEL.THREE;
		}
		if(currentLevel == 4){
			POINTSTONEXTLEVEL = 100 - POINTS;
			level = CURRENTLEVEL.FOUR;
		}
		if(currentLevel == 5){
			POINTSTONEXTLEVEL = 100 - POINTS;
			level = CURRENTLEVEL.FIVE;
		}
		if(currentLevel == 6){
			POINTSTONEXTLEVEL = 500 - POINTS;
			level = CURRENTLEVEL.SIX;
		}
		//Level Up
		if(POINTSTONEXTLEVEL <= 0){
			if(currentLevel == 6){
				
			}
			else{
				Game.playSound(levelUpSound, true);
				POINTS = 0;
				setShipHealth(200);
				currentLevel++;
				timeToUpgrade = true;
				NormalEnemyShip.makeHarder = true;
				System.out.println();
				System.out.println("LEVEL " + currentLevel + " REACHED");	
			}
		}
		
		x += velX;
		y += velY;
		
		playerShipSmoking.runAnimation();
		upgradeFlashIcon.runAnimation();
		armorOnePlayerShipFly.runAnimation();
		armorTwoPlayerShipFly.runAnimation();
		armorThreePlayerShipFly.runAnimation();
		armorFourPlayerShipFly.runAnimation();
		moonExploding.runAnimation();
		bossEnemyShip.runAnimation();
		alienCrashIntoEarth.runAnimation();
	
		collision(object);

		if(deathSound == true){
			Game.playSound(normalEnemyExplosionSound, true);
			deathSound = false;
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayerShip){
				if(PlayerShip.SHOOTING){
					handler.addPlayerBullet(tempObject);
					//Game.playSound(playerShipShootingSound, false);
				}
			}
		}
		if(shipHealth <= 0 && !BossEnemyShip.bossShipIsExploding){
			Game.playMusic(gameMusic, false);
			Game.playMusic(gameOverMusic, true);
			Game.state = GAMESTATE.SCOREBOARD;
		}	
	}
	
	public void collision(LinkedList<GameObject> object){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Meteor){
				if(tempObject.getBounds().intersects(getBounds())){
					shipHealth -= 1;
				}
			}
		}
		
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.BossBullet){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.addPlayerBullet(tempObject);
					shipHealth -= 1;
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.NormalEnemyShip || tempObject.getId() == ObjectId.SuicideEnemyShip || tempObject.getId() == ObjectId.BossBullet){
				if(tempObject.getY() >= Game.HEIGHT){
					if(tempObject.getId() == ObjectId.BossBullet){
						handler.removeObject(tempObject);
					}
					else{
						Game.playSound(ailenReachEarthSound, true);
						setEarthPopulation(getEarthPopulation() - humansKilled);  
						handler.removeObject(tempObject);
						if(getEarthPopulation() <= 0 && !BossEnemyShip.bossShipIsExploding){
							Game.state = GAMESTATE.SCOREBOARD;
							Game.playMusic(gameMusic, false);
							Game.playMusic(gameOverMusic, true);
						}
					}
				}
			}
		}
	for(int i = 0; i < handler.object.size(); i++){
		GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.NormalEnemyShip || tempObject.getId() == ObjectId.SuicideEnemyShip || tempObject.getId() == ObjectId.MineEnemyShip || tempObject.getId() == ObjectId.Meteor || tempObject.getId() == ObjectId.RandomMeteorsOne || tempObject.getId() == ObjectId.RandomMeteorsTwo || tempObject.getId() == ObjectId.RandomMeteorsThree || tempObject.getId() == ObjectId.RandomMeteorsFour){
				if(tempObject.getBounds().intersects(getBounds())){
					if(tempObject.getId() == ObjectId.SuicideEnemyShip){
						shipHealth -= suicideEnemyShipDamage;
						handler.removeObject(tempObject);
						shipExplosion = true;
						deathSound = true;
					}
					if(tempObject.getId() == ObjectId.NormalEnemyShip){
						shipHealth -= normalEnemyShipDamage;
						handler.removeObject(tempObject);
						shipExplosion = true;
						deathSound = true;
					}
					if(tempObject.getId() == ObjectId.MineEnemyShip){
						shipHealth -= mineEnemyShipDamage;
						handler.removeObject(tempObject);
						shipExplosion = true;
						deathSound = true;
					}
					
					if(tempObject.getId() == ObjectId.Meteor || tempObject.getId() == ObjectId.RandomMeteorsOne || tempObject.getId() == ObjectId.RandomMeteorsTwo || tempObject.getId() == ObjectId.RandomMeteorsThree || tempObject.getId() == ObjectId.RandomMeteorsFour){
						if(currentLevel == 6){
							shipHealth -= meteorDamage;
							shipExplosion = true;
						}
					}
					if(shipHealth >= 200){
						shipHealth = 200;
					}	
				}
			}
		}
	
		//Left Collision
		if(x <= - 35){
			velX = 0;
			x =  - 35;
		}
		//Right Collision
		if(x >= Game.WIDTH - 90){
			velX = 0;
			x = Game.WIDTH - 90;
		}
		//Bottom Collision
		if(y >= Game.HEIGHT - 85){
			velY = 0;
			y = Game.HEIGHT - 85;
		}
		//Top Collision
		if(y <= 0){
			velY = 0;
			y = 0;
		}
	}

	public void render(Graphics g) {
		
		
		
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		*/
		if(Game.defenceLevel >= 2){
			armorFourPlayerShipFly.drawAnimations(g, (int) x + 20, (int) y , 90, 90);
		}
		else{
			armorOnePlayerShipFly.drawAnimations(g, (int) x + 20, (int) y , 64, 64);
		}
	
		if(shipExplosion && (explosionTimer < 3000)){
			explosionTimer++;
			g.drawImage(tex.NormalEnemyShipExplosion[2], (int) x - 20 , (int) y , 128, 128, null );
		}
		if(shipExplosion && explosionTimer >= 3000){
			deathSound = false;
			explosionTimer = 0;
			shipExplosion = false;
		}
		if(shipHealth < 120){
			if(Game.defenceLevel >= 2){
				playerShipSmoking.drawAnimations(g, (int) x + 50, (int) y + 40, 52, 32);
			}
			else{
				playerShipSmoking.drawAnimations(g, (int) x + 20, (int) y , 52, 64);
			}
		}  
		if(shipHealth < 80){
			if(Game.defenceLevel >= 2){
				playerShipSmoking.drawAnimations(g, (int) x + 24, (int) y + 40, 32, 32);
			}
			else{
				playerShipSmoking.drawAnimations(g, (int) x + 20, (int) y , 64, 64);
			}
		}  
		

		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH - 200, 10, 190, 20);
		g.setColor(Color.GREEN);
		g.fillRect(Game.WIDTH - 10, 10, (int) ((int) -PlayerShip.earthPopulation/1.05) ,20);
		g.setColor(Color.BLACK);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Earth Population", Game.WIDTH - 127 , 45);
		

		g.setColor(Color.GRAY);
		g.fillRect(10, 10 , 180, 20);
		g.setColor(Color.GREEN);
		g.fillRect(10, 10, (int) ((int) shipHealth/1.05), 20);
		g.setColor(Color.white);
		g.drawString("Ship Health", 10 , 45);
		
		g.setColor(Color.white);
		g.drawString("XP: " + POINTS, Game.WIDTH/2 - 25, 20);
		g.setColor(Color.BLACK);
		g.fillRect(Game.WIDTH/2 - 55, 25 , 100, 10);
		g.setColor(Color.magenta);
		
		
		if(level == CURRENTLEVEL.ONE){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS*5, 10);
		}
		if(level == CURRENTLEVEL.TWO){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS*3, 10);
		}
		if(level == CURRENTLEVEL.THREE){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS*2, 10);
		}
		if(level == CURRENTLEVEL.FOUR){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS, 10);
		}
		if(level == CURRENTLEVEL.FIVE){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS, 10);
		}
		if(level == CURRENTLEVEL.SIX){
			g.fillRect(Game.WIDTH/2 - 55, 25 , POINTS/5, 10);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("LEVEL: " + currentLevel, Game.WIDTH/2 - 35, 50);
		
		if((level == CURRENTLEVEL.TWO || level == CURRENTLEVEL.THREE || level == CURRENTLEVEL.FOUR || level == CURRENTLEVEL.FIVE || level == CURRENTLEVEL.SIX) && timeToUpgrade){
			g.setColor(Color.yellow);
			upgradeFlashIcon.drawAnimations(g, (int) 5, (int) 60 , 40, 40);
			g.drawString("Level Up", 5, 120);
			g.drawString("Press Enter", 5, 135);
		}
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.yellow);
		//g2d.draw(getBounds());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.setColor(Color.red);
		g2d.draw(getBoundsLeft());
		g2d.setColor(Color.GREEN);
		g2d.draw(getBoundsRight());
		*/
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 30, (int) y + 10 , 70, 85);	
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

	public double getEarthPopulation() {
		return earthPopulation;
	}

	public void setEarthPopulation(double earthPopulation) {
		this.earthPopulation = earthPopulation;
	}
	
	public int getShipHealth() {
		return shipHealth;
	}

	public void setShipHealth(int shipHealth) {
		this.shipHealth = shipHealth;
	}
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
