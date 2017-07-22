package earthDefence.frameWorks;

import java.awt.image.BufferedImage;

import earthDefence.window.BufferedImageLoader;

public class Texture {
	
	private SpriteSheet pss, bpb, ness, neses, psss, sess, seses, usis, umis, sums, mees, mess, apbs, bpbs, tess, Ness, less, bess, mes, mfs, aciebs;

	private BufferedImage PlayerShip_sheet = null;
	private BufferedImage MineEnemyShip_sheet = null;
	private BufferedImage SuicideEnemyShip_sheet = null;
	private BufferedImage NormalEnemyShip_sheet = null;
	private BufferedImage TurtleEnemyShip_sheet = null;
	private BufferedImage NinjaEnemyShip_sheet = null;
	private BufferedImage LighterEnemyShip_sheet = null;
	private BufferedImage BossEnemyShip_sheet = null;
	private BufferedImage MeteorFlying_sheet = null;
	private BufferedImage AlienCrashIntoEarthBlood_sheet = null;
	
	
	private BufferedImage BadPlayerBullet_sheet = null;
	private BufferedImage AlrightPlayerBullet_sheet = null;
	private BufferedImage BestPlayerBullet_sheet = null;
	
	private BufferedImage PlayerShipSmoking_sheet = null;
	
	private BufferedImage SuicideEnemyShipExplosion_sheet = null;
	private BufferedImage MineEnemyExplosion_sheet = null;
	private BufferedImage NormalEnemyShipExplosion_sheet = null;
	private BufferedImage MoonExploding_sheet = null;
	
	private BufferedImage UpgradeFlashIcon_sheet = null;
	private BufferedImage UpgradeMenuIcons_sheet = null;
	private BufferedImage ShipUpgradeMeter_sheet = null;
	
	

	public BufferedImage[] PlayerShip = new BufferedImage[80];
	
	public BufferedImage[] BadPlayerBullet = new BufferedImage[4];
	public BufferedImage[] AlrightPlayerBullet = new BufferedImage[4]; 
	public BufferedImage[] BestPlayerBullet = new BufferedImage[4];
	
	public BufferedImage[] AlienCrashIntoEarthBlood = new BufferedImage[3];
	
	public BufferedImage[] TurtleEnemyShip = new BufferedImage[4];
	public BufferedImage[] NinjaEnemyShip = new BufferedImage[4];
	public BufferedImage[] LighterEnemyShip = new BufferedImage[4];
	public BufferedImage[] NormalEnemyShip = new BufferedImage[76];
	public BufferedImage[] NormalEnemyShipExplosion = new BufferedImage[3];
	public BufferedImage[] PlayerShipSmoking = new BufferedImage[3];
	public BufferedImage[] SuicideEnemyShip = new BufferedImage[4];
	public BufferedImage[] SuicideEnemyShipExplosion = new BufferedImage[3];
	public BufferedImage[] UpgradeFlashIcon = new BufferedImage[3];
	public BufferedImage[] UpgradeMenuIcons = new BufferedImage[3];
	public BufferedImage[] ShipUpgradeMeter = new BufferedImage[4];
	public BufferedImage[] MineEnemyExplosion = new BufferedImage[2];
	public BufferedImage[] MineEnemyShip = new BufferedImage[4];
	public BufferedImage[] MoonExploding = new BufferedImage[11];
	public BufferedImage[] MeteorFlying = new BufferedImage[12];
	public BufferedImage[] BossEnemyShip = new BufferedImage[4];

	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
		TurtleEnemyShip_sheet = loader.loadImage("/TurtleEnemyShip_sheet.png");
		NinjaEnemyShip_sheet = loader.loadImage("/NinjaEnemyShip_sheet.png");
		LighterEnemyShip_sheet = loader.loadImage("/LighterEnemyShip_sheet.png");
		PlayerShip_sheet = loader.loadImage("/PlayerShip_sheet.png");
		BadPlayerBullet_sheet = loader.loadImage("/BadPlayerBullet_sheet.png");
		NormalEnemyShip_sheet = loader.loadImage("/NormalEnemyShip_sheet.png");
		NormalEnemyShipExplosion_sheet = loader.loadImage("/NormalEnemyShipExplosion_sheet.png");
		PlayerShipSmoking_sheet = loader.loadImage("/PlayerShipSmoking_sheet.png");
		SuicideEnemyShip_sheet = loader.loadImage("/SuicideEnemyShip_sheet.png");
		SuicideEnemyShipExplosion_sheet = loader.loadImage("/SuicideEnemyShipExplosion_sheet.png");
		UpgradeFlashIcon_sheet = loader.loadImage("/UpgradeFlashIcon_sheet.png");
		UpgradeMenuIcons_sheet = loader.loadImage("/UpgradeMenuIcons_sheet.png");
		ShipUpgradeMeter_sheet = loader.loadImage("/ShipUpgradeMeter_sheet.png");
		MineEnemyExplosion_sheet = loader.loadImage("/MineEnemyExplosion_sheet.png");
		MineEnemyShip_sheet = loader.loadImage("/MineEnemyShip_sheet.png");
		AlrightPlayerBullet_sheet = loader.loadImage("/AlrightPlayerBullet_sheet.png");
		BestPlayerBullet_sheet = loader.loadImage("/BestPlayerBullet_sheet.png");
		BossEnemyShip_sheet = loader.loadImage("/BossEnemyShip_sheet.png");
		MeteorFlying_sheet = loader.loadImage("/MeteorFlying_sheet.png");
		MoonExploding_sheet = loader.loadImage("/MoonExploding_sheet.png");
		AlienCrashIntoEarthBlood_sheet = loader.loadImage("/AlienCrashIntoEarthBlood_sheet.png");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		pss = new SpriteSheet(PlayerShip_sheet);
		bpb = new SpriteSheet(BadPlayerBullet_sheet);
		ness = new SpriteSheet(NormalEnemyShip_sheet);
		neses = new SpriteSheet(NormalEnemyShipExplosion_sheet);
		psss = new SpriteSheet(PlayerShipSmoking_sheet);
		sess = new SpriteSheet(SuicideEnemyShip_sheet);
		seses = new SpriteSheet(SuicideEnemyShipExplosion_sheet);
		usis = new SpriteSheet(UpgradeFlashIcon_sheet);
		umis = new SpriteSheet(UpgradeMenuIcons_sheet);
		sums = new SpriteSheet(ShipUpgradeMeter_sheet);
		mees = new SpriteSheet(MineEnemyExplosion_sheet);
		mess = new SpriteSheet(MineEnemyShip_sheet);
		apbs = new SpriteSheet(AlrightPlayerBullet_sheet);
		bpbs = new SpriteSheet(BestPlayerBullet_sheet);
		tess = new SpriteSheet(TurtleEnemyShip_sheet);
		Ness = new SpriteSheet(NinjaEnemyShip_sheet);
		less = new SpriteSheet(LighterEnemyShip_sheet);
		mes = new SpriteSheet(MoonExploding_sheet);
		bess = new SpriteSheet(BossEnemyShip_sheet);
		mfs = new SpriteSheet(MeteorFlying_sheet);
		aciebs = new SpriteSheet(AlienCrashIntoEarthBlood_sheet);
		
		getTextures();
	}
	private void getTextures(){
		
		TurtleEnemyShip[0] = tess.grabImage(1, 1, 32, 32);
		TurtleEnemyShip[1] = tess.grabImage(2, 1, 32, 32);
		TurtleEnemyShip[2] = tess.grabImage(3, 1, 32, 32);
		TurtleEnemyShip[3] = tess.grabImage(4, 1, 32, 32);
		
		NinjaEnemyShip[0] = Ness.grabImage(1, 1, 32, 32);
		NinjaEnemyShip[1] = Ness.grabImage(2, 1, 32, 32);
		NinjaEnemyShip[2] = Ness.grabImage(3, 1, 32, 32);
		NinjaEnemyShip[3] = Ness.grabImage(4, 1, 32, 32);
		
		LighterEnemyShip[0] = less.grabImage(1, 1, 32, 32);
		LighterEnemyShip[1] = less.grabImage(2, 1, 32, 32);
		LighterEnemyShip[2] = less.grabImage(3, 1, 32, 32);
		LighterEnemyShip[3] = less.grabImage(4, 1, 32, 32);
		
		BossEnemyShip[0] = bess.grabImage(1, 1, 80, 105);
		BossEnemyShip[1] = bess.grabImage(2, 1, 80, 105);
		BossEnemyShip[2] = bess.grabImage(3, 1, 80, 105);
		BossEnemyShip[3] = bess.grabImage(4, 1, 80, 105);
		
		MoonExploding[0] = mes.grabImage(1, 1, 128, 128);
		MoonExploding[1] = mes.grabImage(2, 1, 128, 128);
		MoonExploding[2] = mes.grabImage(3, 1, 128, 128);
		MoonExploding[3] = mes.grabImage(4, 1, 128, 128);
		MoonExploding[4] = mes.grabImage(5, 1, 128, 128);
		MoonExploding[5] = mes.grabImage(6, 1, 128, 128);
		MoonExploding[6] = mes.grabImage(7, 1, 128, 128);
		MoonExploding[7] = mes.grabImage(8, 1, 128, 128);
		MoonExploding[8] = mes.grabImage(9, 1, 128, 128);
		MoonExploding[9] = mes.grabImage(10, 1, 128, 128);
		MoonExploding[10] = mes.grabImage(11, 1, 128, 128);
		
		MeteorFlying[0] = mfs.grabImage(1, 1, 64, 64);
		MeteorFlying[1] = mfs.grabImage(2, 1, 64, 64);
		MeteorFlying[2] = mfs.grabImage(3, 1, 64, 64);
		MeteorFlying[3] = mfs.grabImage(4, 1, 64, 64);
		
		MeteorFlying[4] = mfs.grabImage(1, 2, 64, 64);
		MeteorFlying[5] = mfs.grabImage(2, 2, 64, 64);
		MeteorFlying[6] = mfs.grabImage(3, 2, 64, 64);
		MeteorFlying[7] = mfs.grabImage(4, 2, 64, 64);
		
		MeteorFlying[8] = mfs.grabImage(1, 3, 64, 64);
		MeteorFlying[9] = mfs.grabImage(2, 3, 64, 64);
		MeteorFlying[10] = mfs.grabImage(3, 3, 64, 64);
		MeteorFlying[11] = mfs.grabImage(4, 3, 64, 64);
	
		PlayerShip[0] = pss.grabImage(1, 1, 32, 32); //Player idle
		PlayerShip[1] = pss.grabImage(2, 1, 32, 32); //Player idle
		PlayerShip[2] = pss.grabImage(3, 1, 32, 32); //Player idle
		PlayerShip[3] = pss.grabImage(4, 1, 32, 32); //Player idle
		
		PlayerShip[4] = pss.grabImage(5, 1, 32, 32); //Player idle
		PlayerShip[5] = pss.grabImage(6, 1, 32, 32); //Player idle
		PlayerShip[6] = pss.grabImage(7, 1, 32, 32); //Player idle
		PlayerShip[7] = pss.grabImage(8, 1, 32, 32); //Player idle
		
		PlayerShip[8] = pss.grabImage(9, 1, 32, 32); //Player idle
		PlayerShip[9] = pss.grabImage(10, 1, 32, 32); //Player idle
		PlayerShip[10] = pss.grabImage(11, 1, 32, 32); //Player idle
		PlayerShip[11] = pss.grabImage(12, 1, 32, 32); //Player idle
		
		PlayerShip[12] = pss.grabImage(13, 1, 32, 32); //Player idle
		PlayerShip[13] = pss.grabImage(14, 1, 32, 32); //Player idle
		PlayerShip[14] = pss.grabImage(15, 1, 32, 32); //Player idle
		PlayerShip[15] = pss.grabImage(16, 1, 32, 32); //Player idle
		
		PlayerShip[16] = pss.grabImage(17, 1, 32, 32); //Player idle
		PlayerShip[17] = pss.grabImage(18, 1, 32, 32); //Player idle
		PlayerShip[18] = pss.grabImage(19, 1, 32, 32); //Player idle
		PlayerShip[19] = pss.grabImage(20, 1, 32, 32); //Player idle
		
		PlayerShip[20] = pss.grabImage(1, 2, 32, 32); //Player idle
		PlayerShip[21] = pss.grabImage(2, 2, 32, 32); //Player idle
		PlayerShip[22] = pss.grabImage(3, 2, 32, 32); //Player idle
		PlayerShip[23] = pss.grabImage(4, 2, 32, 32); //Player idle
		
		PlayerShip[24] = pss.grabImage(5, 2, 32, 32); //Player idle
		PlayerShip[25] = pss.grabImage(6, 2, 32, 32); //Player idle
		PlayerShip[26] = pss.grabImage(7, 2, 32, 32); //Player idle
		PlayerShip[27] = pss.grabImage(8, 2, 32, 32); //Player idle
		
		PlayerShip[28] = pss.grabImage(9, 2, 32, 32); //Player idle
		PlayerShip[29] = pss.grabImage(10, 2, 32, 32); //Player idle
		PlayerShip[30] = pss.grabImage(11, 2, 32, 32); //Player idle
		PlayerShip[31] = pss.grabImage(12, 2, 32, 32); //Player idle
		
		PlayerShip[32] = pss.grabImage(13, 2, 32, 32); //Player idle
		PlayerShip[33] = pss.grabImage(14, 2, 32, 32); //Player idle
		PlayerShip[34] = pss.grabImage(15, 2, 32, 32); //Player idle
		PlayerShip[35] = pss.grabImage(16, 2, 32, 32); //Player idle
		
		PlayerShip[36] = pss.grabImage(17, 2, 32, 32); //Player idle
		PlayerShip[37] = pss.grabImage(18, 2, 32, 32); //Player idle
		PlayerShip[38] = pss.grabImage(19, 2, 32, 32); //Player idle
		PlayerShip[39] = pss.grabImage(20, 2, 32, 32); //Player idle
		
		PlayerShip[40] = pss.grabImage(1, 3, 32, 32); //Player idle
		PlayerShip[41] = pss.grabImage(2, 3, 32, 32); //Player idle
		PlayerShip[42] = pss.grabImage(3, 3, 32, 32); //Player idle
		PlayerShip[43] = pss.grabImage(4, 3, 32, 32); //Player idle
		
		PlayerShip[44] = pss.grabImage(5, 3, 32, 32); //Player idle
		PlayerShip[45] = pss.grabImage(6, 3, 32, 32); //Player idle
		PlayerShip[46] = pss.grabImage(7, 3, 32, 32); //Player idle
		PlayerShip[47] = pss.grabImage(8, 3, 32, 32); //Player idle
		
		PlayerShip[48] = pss.grabImage(9, 3, 32, 32); //Player idle
		PlayerShip[49] = pss.grabImage(10,3, 32, 32); //Player idle
		PlayerShip[50] = pss.grabImage(11, 3, 32, 32); //Player idle
		PlayerShip[51] = pss.grabImage(12, 3, 32, 32); //Player idle
		PlayerShip[52] = pss.grabImage(13, 3, 32, 32); //Player idle
		PlayerShip[53] = pss.grabImage(14, 3, 32, 32); //Player idle
		PlayerShip[54] = pss.grabImage(15, 3, 32, 32); //Player idle
		PlayerShip[55] = pss.grabImage(16, 3, 32, 32); //Player idle
		
		PlayerShip[56] = pss.grabImage(17, 3, 32, 32); //Player idle
		PlayerShip[57] = pss.grabImage(18, 3, 32, 32); //Player idle
		PlayerShip[58] = pss.grabImage(19, 3, 32, 32); //Player idle
		PlayerShip[59] = pss.grabImage(20, 3, 32, 32); //Player idle
		
		PlayerShip[60] = pss.grabImage(1, 4, 32, 32); //Player idle
		PlayerShip[61] = pss.grabImage(2, 4, 32, 32); //Player idle
		PlayerShip[62] = pss.grabImage(3, 4, 32, 32); //Player idle
		PlayerShip[63] = pss.grabImage(4, 4, 32, 32); //Player idle
		
		PlayerShip[64] = pss.grabImage(5, 4, 32, 32); //Player idle
		PlayerShip[65] = pss.grabImage(6, 4, 32, 32); //Player idle
		PlayerShip[66] = pss.grabImage(7, 4, 32, 32); //Player idle
		PlayerShip[67] = pss.grabImage(8, 4, 32, 32); //Player idle
		
		PlayerShip[68] = pss.grabImage(9, 4, 32, 32); //Player idle	
		PlayerShip[69] = pss.grabImage(10,4, 32, 32); //Player idle
		PlayerShip[70] = pss.grabImage(11, 4, 32, 32); //Player idle
		PlayerShip[71] = pss.grabImage(12, 4, 32, 32); //Player idle
		
		PlayerShip[72] = pss.grabImage(13, 4, 32, 32); //Player idle
		PlayerShip[73] = pss.grabImage(14, 4, 32, 32); //Player idle
		PlayerShip[74] = pss.grabImage(15, 4, 32, 32); //Player idle
		PlayerShip[75] = pss.grabImage(16, 4, 32, 32); //Player idle
		
		PlayerShip[76] = pss.grabImage(17, 5, 32, 32); //Player idle
		PlayerShip[77] = pss.grabImage(18, 5, 32, 32); //Player idle
		PlayerShip[78] = pss.grabImage(19, 5, 32, 32); //Player idle
		PlayerShip[79] = pss.grabImage(20, 5, 32, 32); //Player idle
		
		MineEnemyExplosion[0] = mees.grabImage(1, 1, 128, 128);
		MineEnemyExplosion[1] = mees.grabImage(2, 1, 128, 128);
		
		MineEnemyShip[0] = mess.grabImage(1, 1, 32, 32);
		MineEnemyShip[1] = mess.grabImage(2, 1, 32, 32);
		MineEnemyShip[2] = mess.grabImage(3, 1, 32, 32);
		MineEnemyShip[3] = mess.grabImage(4, 1, 32, 32);
		
		SuicideEnemyShip[0] = sess.grabImage(1, 1, 32, 32);
		SuicideEnemyShip[1] = sess.grabImage(2, 1, 32, 32);
		SuicideEnemyShip[2] = sess.grabImage(3, 1, 32, 32);
		SuicideEnemyShip[3] = sess.grabImage(4, 1, 32, 32);
		
		SuicideEnemyShipExplosion[0] = seses.grabImage(1, 1, 128, 128);
		SuicideEnemyShipExplosion[1] = seses.grabImage(2, 1, 128, 128);
		SuicideEnemyShipExplosion[2] = seses.grabImage(3, 1, 128, 128);
		
		UpgradeFlashIcon[0] = usis.grabImage(1, 1, 128, 128);
		UpgradeFlashIcon[1] = usis.grabImage(2, 1, 128, 128);
		UpgradeFlashIcon[2] = usis.grabImage(3, 1, 128, 128);
		
		UpgradeMenuIcons[0] = umis.grabImage(1, 1, 128, 128);
		UpgradeMenuIcons[1] = umis.grabImage(2, 1, 128, 128);
		UpgradeMenuIcons[2] = umis.grabImage(3, 1, 128, 128);
		
		ShipUpgradeMeter[0] = sums.grabImage(1, 1, 32, 32);
		ShipUpgradeMeter[1] = sums.grabImage(2, 1, 32, 32);
		ShipUpgradeMeter[2] = sums.grabImage(3, 1, 32, 32);
		ShipUpgradeMeter[3] = sums.grabImage(4, 1, 32, 32);
		
		AlienCrashIntoEarthBlood[0] = aciebs.grabImage(1, 1, 124, 124);
		AlienCrashIntoEarthBlood[1] = aciebs.grabImage(2, 1, 124, 124);
		AlienCrashIntoEarthBlood[2] = aciebs.grabImage(3, 1, 124, 124);

		BadPlayerBullet[0] = bpb.grabImage(1, 1, 8, 8); //BadPlayerBullet idle
		BadPlayerBullet[1] = bpb.grabImage(2, 1, 8, 8); //BadPlayerBullet idle
		BadPlayerBullet[2] = bpb.grabImage(3, 1, 8, 8); //BadPlayerBullet idle
		BadPlayerBullet[3] = bpb.grabImage(4, 1, 8, 8); //BadPlayerBullet idle
		
		AlrightPlayerBullet[0] = apbs.grabImage(1, 1, 128, 128);
		AlrightPlayerBullet[1] = apbs.grabImage(2, 1, 128, 128);
		AlrightPlayerBullet[2] = apbs.grabImage(3, 1, 128, 128);
		AlrightPlayerBullet[3] = apbs.grabImage(4, 1, 128, 128);
		
		BestPlayerBullet[0] = bpbs.grabImage(1, 1, 128, 128);
		BestPlayerBullet[1] = bpbs.grabImage(2, 1, 128, 128);
		BestPlayerBullet[2] = bpbs.grabImage(3, 1, 128, 128);
		BestPlayerBullet[3] = bpbs.grabImage(4, 1, 128, 128);
		
		NormalEnemyShip[0] = ness.grabImage(1, 1, 32, 32); // NormalEnemyShip idle
		NormalEnemyShip[1] = ness.grabImage(2, 1, 32, 32); // NormalEnemyShip idle
		NormalEnemyShip[2] = ness.grabImage(3, 1, 32, 32); // NormalEnemyShip idle
		NormalEnemyShip[3] = ness.grabImage(4, 1, 32, 32); // NormalEnemyShip idle
		
		NormalEnemyShipExplosion[0] = neses.grabImage(1, 1, 128, 128); //NormalEnemyShipExplosion idle
		NormalEnemyShipExplosion[1] = neses.grabImage(2, 1, 128, 128); //NormalEnemyShipExplosion idle
		NormalEnemyShipExplosion[2] = neses.grabImage(3, 1, 128, 128); //NormalEnemyShipExplosion idle
		
		PlayerShipSmoking[0] = psss.grabImage(1, 1, 32, 32);
		PlayerShipSmoking[1] = psss.grabImage(2, 1, 32, 32);
		PlayerShipSmoking[2] = psss.grabImage(3, 1, 32, 32);	
	}
}