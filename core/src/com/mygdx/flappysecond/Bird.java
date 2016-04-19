package com.mygdx.flappysecond;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by rinat on 19.04.2016.
 */
public class Bird extends Actor {

    public static final float FRAME_DURATION = 0.06f;

    public enum State {ALIVE, HIT};
    private State state = State.ALIVE;

    private float stateTime = 0;

    private float speed = 15;
    private float gravity = 0;

    private TextureRegion playerFrame;

    private TextureRegion playerIdle;
    private Animation flightAnimation;

    public Bird() {
        loadTextures();
    }

    private void loadTextures() {

        //setOrigin(Align.bottomLeft);
        setOrigin(Align.topRight);
        //setOrigin(Align.center);

        playerIdle = new TextureRegion(Assets.yellowTexture);
        playerFrame = playerIdle;

        TextureRegion[] flightFrames = new TextureRegion[3];
        for (int i = 0; i < 3; ++i) {
            flightFrames[i] = Assets.yellowPack.getRegions().get(i);
        }
        flightAnimation = new Animation(FRAME_DURATION, flightFrames);
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        stateTime += delta;

        setPosition(getX() + delta * speed, getY() - delta * gravity);

        if (getState().equals(Bird.State.ALIVE)) {
            if (Gdx.input.justTouched()) {
                gravity = -60.0f;
            }
            else {
                gravity += 3;
            }
        }

        setRotation(-gravity * 0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (state.equals(State.ALIVE)) {
            playerFrame = flightAnimation.getKeyFrame(stateTime, true);
        }
        else if (state.equals(State.HIT)) {
            playerFrame = playerIdle;
        }

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(playerFrame, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
