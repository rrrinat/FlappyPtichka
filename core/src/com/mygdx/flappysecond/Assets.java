package com.mygdx.flappysecond;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

	public static Texture topPipeTexture;
	public static Texture bottomPipeTexture;
	public static Texture background;
	public static Texture nightBackground;
	public static Texture flappyBirdLabel;
	public static Texture gears2013;
	public static Texture googleButton;
	public static Texture rateButton;
	public static Texture playButton;
	public static Texture ground;
	public static Texture yellowTexture;
	public static TextureAtlas yellowPack;

	public static Sound wingSound;

    public Assets() {

		topPipeTexture = new Texture("pic/brent/toptube.png");
		bottomPipeTexture = new Texture("pic/brent/bottomtube.png");
		background = new Texture("pic/brent/bg.png");
		playButton = new Texture("pic/brent/playbtn.png");
		ground = new Texture("pic/brent/ground.png");
		nightBackground = new Texture("pic/background_night.png");
		flappyBirdLabel = new Texture("pic/flappy_bird.png");
		gears2013 = new Texture("pic/gears_2013.png");
		googleButton = new Texture("pic/google_button.png");
		rateButton = new Texture("pic/rate_button.png");
		yellowTexture = new Texture("pic/brent/bird.png");
		yellowPack = new TextureAtlas("pic/yellow.pack");

		wingSound = Gdx.audio.newSound(Gdx.files.internal("pic/brent/sfx_wing.ogg"));
    }    
    
	@Override
	public void dispose() {

		Gdx.app.log(Env.game.TAG, "Destroing resources");
		topPipeTexture.dispose();
		bottomPipeTexture.dispose();
		background.dispose();
		playButton.dispose();
		ground.dispose();
		nightBackground.dispose();
		flappyBirdLabel.dispose();
		gears2013.dispose();
		googleButton.dispose();
		rateButton.dispose();
		yellowTexture.dispose();
		yellowPack.dispose();
		wingSound.dispose();
	}

}
