package earthDefence.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.ObjectId;
import earthDefence.frameWorks.Texture;
import earthDefence.window.Animation;
import earthDefence.window.Game;
import earthDefence.window.Handler;


public class BossBullet extends GameObject{

	private Handler handler;
	private int bossBulletSpeed = 3;
	private Texture tex = Game.getInstance();
	
	private Animation badPlayerBullet;
	private Animation alrightPlayerBullet;
	private Animation bestPlayerBullet;
	private Animation normalEnemyShipExplosion;
	
	public BossBullet(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		badPlayerBullet = new Animation(3, tex.BadPlayerBullet[0], tex.BadPlayerBullet[1],tex.BadPlayerBullet[2], tex.BadPlayerBullet[3]);
		alrightPlayerBullet = new Animation(3, tex.AlrightPlayerBullet[0], tex.AlrightPlayerBullet[1], tex.AlrightPlayerBullet[2], tex.AlrightPlayerBullet[3]);
		bestPlayerBullet = new Animation(3, tex.BestPlayerBullet[0], tex.BestPlayerBullet[1], tex.BestPlayerBullet[2], tex.BestPlayerBullet[3]);
		normalEnemyShipExplosion = new Animation(4, tex.NormalEnemyShipExplosion[0], tex.NormalEnemyShipExplosion[1]);
	}

	public void tick(LinkedList<GameObject> object) {

		velY = bossBulletSpeed;
	
		y += velY;
		collision(object);
		
		badPlayerBullet.runAnimation();
		alrightPlayerBullet.runAnimation();
		bestPlayerBullet.runAnimation();
		normalEnemyShipExplosion.runAnimation();
		
		
	}
	
	public void collision(LinkedList<GameObject> object){
		
	}

	public void render(Graphics g) {

		alrightPlayerBullet.drawAnimations(g, (int) x, (int) y , 32, 32);
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Meteor){
				if(tempObject.getBounds().intersects(getBounds())){
					g.drawImage(tex.NormalEnemyShipExplosion[2], (int) x - 50, (int) y - 20, null);
				}
			}
		}	

	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 8, 8);	
	}
}