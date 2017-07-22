package earthDefence.objects;

import java.awt.Color;
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


public class BadPlayerBullet extends GameObject{

	private Handler handler;
	public static int BULLET_MAXSPEED = 30;
	private Texture tex = Game.getInstance();
	
	
	private Animation badPlayerBullet;
	private Animation alrightPlayerBullet;
	private Animation bestPlayerBullet;
	
	public BadPlayerBullet(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		badPlayerBullet = new Animation(3, tex.BadPlayerBullet[0], tex.BadPlayerBullet[1],tex.BadPlayerBullet[2], tex.BadPlayerBullet[3]);
		alrightPlayerBullet = new Animation(3, tex.AlrightPlayerBullet[0], tex.AlrightPlayerBullet[1], tex.AlrightPlayerBullet[2], tex.AlrightPlayerBullet[3]);
		bestPlayerBullet = new Animation(3, tex.BestPlayerBullet[0], tex.BestPlayerBullet[1], tex.BestPlayerBullet[2], tex.BestPlayerBullet[3]);
	}

	public void tick(LinkedList<GameObject> object) {
		
		y -= BULLET_MAXSPEED;
		
		collision(object);
		
		badPlayerBullet.runAnimation();
		alrightPlayerBullet.runAnimation();
		bestPlayerBullet.runAnimation();
		
	}
	
	public void collision(LinkedList<GameObject> object){
		
	}

	public void render(Graphics g) {
		if(Game.weaponLevel == 1){
			badPlayerBullet.drawAnimations(g, (int) x, (int) y , 8, 8);
		}
		if(Game.weaponLevel == 2){
			alrightPlayerBullet.drawAnimations(g, (int) x, (int) y , 20, 20);
		}
		if(Game.weaponLevel == 3){
			bestPlayerBullet.drawAnimations(g, (int) x, (int) y , 25, 25);
		}
		if(Game.weaponLevel == 4){
			bestPlayerBullet.drawAnimations(g, (int) x, (int) y , 25, 25);
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 8, 8);	
	}
	public int getMaxSpeed() {
		return BULLET_MAXSPEED;
	}

	public void setMaxSpeed(int maxSpeed) {
		BadPlayerBullet.BULLET_MAXSPEED = maxSpeed;
	}
}