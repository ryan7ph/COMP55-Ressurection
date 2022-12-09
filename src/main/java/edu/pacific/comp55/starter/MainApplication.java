package edu.pacific.comp55.starter;

import java.util.ArrayList;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private ArrayList<Room> rooms;
	private ArrayList<Room> combat;
	private MenuPane menu;
	private SettingPane setting;
	private Player player;
	private int count;
	private DataStorage data = new DataStorage();

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		player = new Player();
		setupRooms();
		player.setRoom(rooms.get(0));

		
	}
	
	public void setupRooms() {
		rooms = new ArrayList<Room>();
		ArrayList<Monster> monsters = new ArrayList<Monster>();

		monsters.add(new MonsterChaser(100, 200, 50, 50));
		monsters.add(new MonsterChaser(250, 425, 50, 50));
		monsters.add(new MonsterChaser(400, 500, 50, 50));
		monsters.add(new MonsterSentry(425, 150, 50, 50));
		monsters.add(new MonsterSentry(500, 275, 50, 50));


		ArrayList<Point> path = new ArrayList<Point>();
		path.add(new Point(100, 100));
		path.add(new Point(50, 100));
		path.add(new Point(50, 50));
		path.add(new Point(150, 50));
		path.add(new Point(400, 100));
		path.add(new Point(200, 100));
		path.add(new Point(300, 300));
		path.add(new Point(350, 300));
		path.add(new Point(600, 100));
		
		ArrayList<Point> path2 = new ArrayList<Point>();
		path2.add(new Point (50, 150));
		path2.add(new Point (100, 150));
		path2.add(new Point (100, 50));
		path2.add(new Point (200, 50));
		path2.add(new Point (400, 200));
		path2.add(new Point (600, 200));
		path2.add(new Point (600, 400));
		path2.add(new Point (300, 400));
		path2.add(new Point (150, 200));
		
  		MonsterPatroller patroler1 = new MonsterPatroller(150, 400, 62.5, 62.5);
		MonsterPatroller patroler2 = new MonsterPatroller(275, 200, 62.5, 62.5);
		MonsterPatroller patroler3 = new MonsterPatroller(425, 175, 62.5, 62.5);

		patroler1.setPath(path);
		patroler2.setPath(path2);
//		patroler3.setPath(path3);
		monsters.add(patroler1);
		monsters.add(patroler2);
//		monsters.add(patroler3);
		
		ArrayList<Object> objects = new ArrayList<Object>();
		
		Object box = new Object("robot head.jpg", 300, 400, 50, 50);
		objects.add(box);
		box = new Object("robot head.jpg", 45, 510, 50, 50);
		objects.add(box);

		Room room1 = new Room(this, RoomType.BOSS, player, monsters, objects);
		
		rooms.add(room1);

		monsters = new ArrayList<Monster>();
		monsters.add(new MonsterChaser(500, 250, 50, 50));
		monsters.add(new MonsterChaser(300, 300, 50, 50));
		monsters.add(new MonsterSentry(200, 375, 50, 50));
		monsters.add(new MonsterSentry(275, 200, 50, 50));
		monsters.add(new MonsterSentry(350, 325, 50, 50));

		MonsterPatroller patroler4 = new MonsterPatroller(500, 300, 62.5, 62.5);
		MonsterPatroller patroler5 = new MonsterPatroller(350, 250, 62.5, 62.5);
		patroler4.setPath(path);
		patroler5.setPath(path);
		monsters.add(patroler4);
		monsters.add(patroler5);

		objects = new ArrayList<Object>();
		box = new Object("robot head.jpg", 30, 40, 50, 50);
		objects.add(box);
		box = new Object("robot head.jpg", 45, 100, 50, 50);
		objects.add(box);
		
		Room room2 = new Room(this, RoomType.BOSS, player, monsters, objects);
		rooms.add(room2);
		
		Room[] neighbors1 = {null, room2, null, null};
		room1.setNeighbors(neighbors1);

		Room[] neighbors2 = {null, null, null, room1};
		room2.setNeighbors(neighbors2);

	}

	public void run() {
		System.out.println("Hello, world!");
		menu = new MenuPane(this);
		setting = new SettingPane(this);
		setupInteractions();
		switchToMenu();
		
		animate();
	}

	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}
	
	public void switchToSetting() {
		switchToScreen(setting);
	}

	public void switchToRoom() {
		playRandomSound();
		switchToScreen(player.getRoom());
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	public void save() {
		data.maxHP = player.getHealth();
		data.damage = player.getDamage();
//		data.shield = player.shield();
//		data.coin = player.getCoin();
//		data.timeOfComplete = player.time();
		data.writeSaveFile();
	}
	
	public void load() {
		int dataRecieved[] = new int[5];
		dataRecieved = data.readSaveFile();
		player.health = dataRecieved[0];
		player.damage = dataRecieved[1];
//		player.shield = dataRecieved[2];
//		player.coin = dataRecieved[3];
//		player.time = dataRecieved[4];
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
