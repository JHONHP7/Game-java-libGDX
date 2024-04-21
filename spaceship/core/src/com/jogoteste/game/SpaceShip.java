package com.jogoteste.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SpaceShip extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, tNave;
	private Sprite nave;
	private Float posX, posY, velocity;

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
	}

	/**
	 * Método de renderização
	 */
	@Override
	public void render() {

		this.moveNave();

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(nave, posX, posY);
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
}
