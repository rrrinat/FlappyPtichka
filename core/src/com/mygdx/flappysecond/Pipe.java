package com.mygdx.flappysecond;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by rinat on 19.04.2016.
 */
public class Pipe extends Actor {

    public static final float FRAME_DURATION = 0.06f;

    private Texture texture;
    private Rectangle bounds;

    public Pipe(Texture texture) {

        this.texture = texture;
        bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public void act(float delta) {

        super.act(delta);
        bounds.set(getX(),getY(), getWidth(), getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        /*
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0,
                (int)getWidth(), (int)getHeight(),
                false, false);
          */
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
