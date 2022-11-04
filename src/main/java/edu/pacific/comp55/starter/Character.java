package edu.pacific.comp55.starter;

import java.util.ArrayList;

public class Character {
	private CharacterType charType;
	private FaceDirectionType faceDirection;
	private Point startPoint;
	private int width;
	private int height;
	private int health;
	private int rateOfFire;
	private int speed;
	private int speedOfBullet;
	private int damage;
	private ArrayList<Character> characters = new ArrayList();
	private Point[] collisionDetectionPoints = new Point[9];
	
	
	public Character(CharacterType charType, FaceDirectionType faceDirection, int startX, int startY, int width,
			int height, int health, int rateOfFire, int speed, int speedOfBullet, int damage) {
		super();
		this.charType = charType;
		this.faceDirection = faceDirection;
		this.startPoint = new Point(startX, startY);
		this.width = width;
		this.height = height;
		this.health = health;
		this.rateOfFire = rateOfFire;
		this.speed = speed;
		this.speedOfBullet = speedOfBullet;
		this.damage = damage;
	}

	public CharacterType getType() {
		return charType;
	}
	public void setType(CharacterType charType) {
		this.charType = charType;
	}
	public int getStartX() {
		return startPoint.getX();
	}
	public void setStartX(int startX) {
		this.startPoint.setX(startX);
	}
	public int getStartY() {
		return startPoint.getY();
	}
	public void setStartY(int startY) {
		this.startPoint.setY(startY);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getRateOfFire() {
		return rateOfFire;
	}
	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeedOfBullet() {
		return speedOfBullet;
	}
	public void setSpeedOfBullet(int speedOfBullet) {
		this.speedOfBullet = speedOfBullet;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public FaceDirectionType getFaceDirection() {
		return faceDirection;
	}
	public void setFaceDirection(FaceDirectionType faceDirection) {
		this.faceDirection = faceDirection;
	}
	
	public void move(int moveInX, int moveInY) {
		switch(moveInX) {
			case -1: this.setStartX(this.getStartX() - this.getSpeed());
			case 0: this.setStartX(this.getStartX());	
			case 1: this.setStartX(this.getStartX() + this.getSpeed());
		}
		switch(moveInY) {
			case -1: this.setStartY(this.getStartY() - this.getSpeed());
			case 0: this.setStartY(this.getStartY());
			case 1: this.setStartY(this.getStartY() + this.getSpeed());
		}
	}
	
	public boolean isDead() {
		if (this.health <= 0) {
			return true;
		}
		return false;
	}
	
	public void updateCollisionDetectionPoints() {
		collisionDetectionPoints[0] = this.startPoint;
		
	}
	
	@Override
	public String toString() {
		return "Character [charType=" + charType + ", faceDirection=" + faceDirection + ", startX=" + startPoint.getX()
				+ ", startY=" + startPoint.getY() + ", width=" + width + ", height=" + height + ", health=" + health
				+ ", rateOfFire=" + rateOfFire + ", speed=" + speed + ", speedOfBullet=" + speedOfBullet + ", damage="
				+ damage + "]";
	}

	public static void main(String[] args) {
		Character player = new Character(CharacterType.PLAYER, FaceDirectionType.RIGHT, 0, 0, 1000,
				1000, 100, 60, 500, 750, 25);
		System.out.println(player);
		player.move(1, 1);
		System.out.println(player.isDead());
		System.out.println(player);
	}
	
}
