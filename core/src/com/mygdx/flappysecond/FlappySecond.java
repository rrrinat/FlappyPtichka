package com.mygdx.flappysecond;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.flappysecond.screens.GameScreen;

public class FlappySecond extends Game {

	public static final String TAG = "FlappySecondApplication";

	public static final float SCENE_WIDTH = 540.0f;
	public static final float SCENE_HEIGHT = 960.0f;

	public static final float ASPECT_RATIO = 54.0f/96.0f;
	public static final float TEX_RATIO = 96.0f/512.0f;

	// Constants
	public static final float	PIX_TO_METER = 0.1f;			// Pixels to Meters
	public static final float	METER_TO_PIX = 10f;				// Meters to Pixels

	private SpriteBatch batch;
	private OrthographicCamera camera;

	private Viewport viewport;
	private Viewport hudViewport;

	private Assets assets;
	private FontManager fontManager;

	private GameScreen gameScreen;

	@Override
	public void create () {

		Env.init(this);

		batch = new SpriteBatch();

		camera   = new OrthographicCamera();
		viewport = new FitViewport(SCENE_WIDTH * PIX_TO_METER, SCENE_HEIGHT * PIX_TO_METER, camera);
		hudViewport = new FitViewport(480, 800);

		assets = new Assets();
		fontManager = new FontManager();

		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {

		super.dispose();
		gameScreen.dispose();
		fontManager.dispose();
		assets.dispose();
	}

	@Override
	public void render () {

		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public FontManager getFontManager() {
		return fontManager;
	}

	public Viewport getHudViewport() {
		return hudViewport;
	}

	public void setHudViewport(Viewport hudViewport) {
		this.hudViewport = hudViewport;
	}
}