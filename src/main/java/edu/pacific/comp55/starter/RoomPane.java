package edu.pacific.comp55.starter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.google.common.base.Objects;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class RoomPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public static final int BREAK_MS = 100;

	private Room room;
	private GParagraph para;
	
	private MonsterChaser chaser;
	private MonsterPatroller patroler;
	private MonsterSentry sentry;
	private Bullet bullet;
	private Player player;
	
	private ArrayList<AnimatedObject> animatedObjects;

	public RoomPane(MainApplication app, Player player) {
		this.program = app;
		this.player = player;
//		img = new GImage("robot head.jpg", 100, 100);
//		para = new GParagraph("welcome\nto my\nsecret room!", 150, 300);
//		para.setFont("Arial-24");
		ArrayList<Monster> monsters = new ArrayList<Monster>();

		chaser = new MonsterChaser(100, 100, 60, 60);
		patroler = new MonsterPatroller(100, 100, 60, 60);
		sentry = new MonsterSentry(200, 200, 60, 60);
		bullet = new Bullet(100, 100, 10, DirectionType.RIGHT);
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
		patroler.setPath(path);
		monsters.add(chaser);
		monsters.add(patroler);
		monsters.add(sentry);
		ArrayList<Object> objects = new ArrayList<Object>();

		
		room = new Room(program, RoomType.BOSS, player, monsters, objects);
	}

	@Override
	public void showContents() {
		System.out.println("showContent RoomPane");
		room.showContents();
	}

	@Override
	public void hideContents() {
		System.out.println("hideContent RoomPane");
		room.hideContents();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		//if (obj == room) {
			//program.switchToMenu();
		//}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		room.setPlayer(player);
	}
	
	public void removePlayer() {
//		room.removePlayer();
		this.player = null;
	}
	
	@Override
	public void animate() {
		room.animate();
	}
	
	private void checkCollisions(AnimatedObject object) {
	}

	public void keyPressed(KeyEvent e) {
		if (player != null) {
			player.keyPressed(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (player != null) {
			player.keyReleased(e);
		}		
	}
	public void keyTyped(KeyEvent e) {
		System.out.println("-----------------------");
		switch (e.getKeyChar()) {
		case 27:
			program.switchToMenu();
			break;
		default: 
			if (player != null) {
				player.keyTyped(e);
			}
			break;
		}
	}
}
