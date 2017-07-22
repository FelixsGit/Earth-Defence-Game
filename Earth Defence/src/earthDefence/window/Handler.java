package earthDefence.window;

import java.awt.Graphics;
import java.util.LinkedList;
import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.ObjectId;
import earthDefence.objects.BadPlayerBullet;
import earthDefence.objects.PlayerShip;

public class Handler {
	
	public LinkedList <GameObject> object  = new LinkedList<GameObject>();
	private GameObject tempObject;
	private Handler handler;
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	public void render(Graphics g){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public void addPlayerBullet(GameObject object){
		if(Game.defenceLevel >= 2 && Game.weaponLevel >= 3){
			addObject(new BadPlayerBullet(object.getX() + 42, object.getY() + 80, handler, ObjectId.BadPlayerBullet));
			addObject(new BadPlayerBullet(object.getX() + 62, object.getY() + 80, handler, ObjectId.BadPlayerBullet));
		}
		else{
			addObject(new BadPlayerBullet(object.getX() + 30, object.getY() + 80, handler, ObjectId.BadPlayerBullet));
			addObject(new BadPlayerBullet(object.getX() + 65, object.getY() + 80, handler, ObjectId.BadPlayerBullet));
		}
	}
 }