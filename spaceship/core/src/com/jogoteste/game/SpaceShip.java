package com.jogoteste.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;

public class SpaceShip extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, tNave, tMissile, tEnemy;
	private Sprite nave;
	private Sprite missile;
	private Float posX, posY, velocity, xMissile, yMissile;
	private boolean shoot = false;
	private Array<Rectangle> enemies;
	private Long lastEnemyTime;

	/**
	 * Construtor para libGDX
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("bg.png");
		tNave = new Texture("spaceship.png");
		nave = new Sprite(tNave);
		posX = 0f;
		posY = 0f;
		velocity = 10f;

		tMissile = new Texture("missile.png");
		missile = new Sprite(tMissile);
		xMissile = posX;
		yMissile = posY;
		shoot = false;

		tEnemy = new Texture("enemy.png");
		enemies = new Array<Rectangle>();
		lastEnemyTime = 0L;
	}

	/**
	 * Método de renderização
	 */
	@Override
	public void render() {

		this.moveNave();
		this.moveMissile();
		this.moveEnimies();

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		if (shoot) {
			batch.draw(missile, xMissile + nave.getWidth() / 2, yMissile + nave.getHeight() / 2 - 12);
		}
		batch.draw(nave, posX, posY);
		for (Rectangle enemy : enemies) {
			batch.draw(tEnemy, enemy.x, enemy.y);
		}
		batch.end();
	}

	/**
	 * Método de limpeza de recursos
	 */
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		tNave.dispose();
	}

	private void moveNave() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (posX < Gdx.graphics.getWidth() - nave.getWidth()) {
				posX += velocity;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (posX > 0) {
				posX -= velocity;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (posY < Gdx.graphics.getHeight() - nave.getHeight()) {
				posY += velocity;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (posY > 0) {
				posY -= velocity;
			}
		}
	}

	private void moveMissile() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !shoot) {
			shoot = true;
			yMissile = posY;

		}
		if (shoot) {
			if (xMissile < Gdx.graphics.getWidth()) {
				xMissile += 40;
			} else {
				xMissile += 40;
				shoot = false;
			}
		} else {
			yMissile = posY;
			xMissile = posX;
		}

	}

	private void spawnEnemies() {
		Rectangle enemy = new Rectangle(Gdx.graphics.getWidth(),
				MathUtils.random(0, Gdx.graphics.getHeight() - tEnemy.getHeight()),
				tEnemy.getWidth(), tEnemy.getHeight());

		enemies.add(enemy);
		lastEnemyTime = TimeUtils.nanoTime();

	}

	private void moveEnimies() {
		if (TimeUtils.nanoTime() - lastEnemyTime > 999999999) {
			this.spawnEnemies();
		}

		for (Iterator<Rectangle> iter = enemies.iterator(); iter.hasNext();) {
			Rectangle enemy = iter.next();
			enemy.x -= 400 * Gdx.graphics.getDeltaTime();
			if (enemy.x + tEnemy.getWidth() < 0) {
				iter.remove();
			}
		}

	}
}
