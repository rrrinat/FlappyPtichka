package com.mygdx.flappysecond.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.flappysecond.Assets;
import com.mygdx.flappysecond.Bird;
import com.mygdx.flappysecond.Env;
import com.mygdx.flappysecond.FlappySecond;
import com.mygdx.flappysecond.Hud;
import com.mygdx.flappysecond.Pipe;

import javafx.scene.input.KeyCode;

/**
 * Created by rinat on 15.04.2016.
 */
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private Hud hud;

    private float centerBottomPipe = 0.0f;

    private int step = 6;
    private float spacing = 30;
    private float gap = 20;
    private Image backgroundImage;
    private Bird bird;

    private Image groundImage;
    private Image groundImage2;

    private Array<Pipe> topPipes = new Array<Pipe>();
    private Array<Pipe> bottomPipes = new Array<Pipe>();

    @Override
    public void show() {

        batch    = Env.game.getBatch();
        camera   = Env.game.getCamera();
        viewport = Env.game.getViewport();
        stage    = new Stage(viewport, batch);
        hud      = new Hud();

        backgroundImage = new Image(Assets.background);
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.addActor(backgroundImage);

        creatingPipes();
        creatingGround();

        float birdHeight = Assets.yellowTexture.getHeight() * Env.game.TEX_RATIO;
        float birdWidth = birdHeight * 34.0f/24.0f;

        bird = new Bird();
        bird.setSize(birdWidth, birdHeight);
        bird.setPosition(viewport.getWorldWidth() / 3 - birdWidth / 2, viewport.getWorldHeight() / 2 - birdHeight / 2);
        stage.addActor(bird);

        Gdx.input.setInputProcessor(stage);
    }

    private void creatingPipes() {

        float pipeRatio = (float)Assets.bottomPipeTexture.getWidth()/(float)Assets.bottomPipeTexture.getHeight();
        float pipeHeight = Assets.bottomPipeTexture.getHeight() * Env.game.TEX_RATIO;
        float pipeWidth = pipeHeight * pipeRatio;

        centerBottomPipe = MathUtils.random(-24.0f, 4.0f);

        float firstOffset = spacing * 3;

        for (int i = 0; i < 3; ++i) {
            Pipe bottomPipe = new Pipe(Assets.bottomPipeTexture);
            bottomPipe.setSize(pipeWidth, pipeHeight);
            bottomPipe.setPosition(firstOffset + i * spacing, centerBottomPipe);
            bottomPipes.add(bottomPipe);
            stage.addActor(bottomPipe);
        }

        float centerTopPipe = centerBottomPipe + pipeHeight + gap;

        for (int i = 0; i < 3; ++i) {

            Pipe topPipe = new Pipe(Assets.topPipeTexture);
            topPipe.setSize(pipeWidth, pipeHeight);
            topPipe.setPosition(firstOffset + i * spacing, centerTopPipe);
            topPipes.add(topPipe);
            stage.addActor(topPipe);
        }
    }

    private void creatingGround() {

        groundImage = new Image(Assets.ground);
        groundImage.setSize(viewport.getWorldWidth(), viewport.getWorldWidth() / 3);
        groundImage.setX(0);

        groundImage2 = new Image(Assets.ground);
        groundImage2.setSize(viewport.getWorldWidth(), viewport.getWorldWidth() / 3);
        groundImage2.setX(groundImage.getWidth());

        stage.addActor(groundImage);
        stage.addActor(groundImage2);
    }

    private boolean checkCollision() {

        if (bird.getState().equals(Bird.State.HIT)) {

            return false;
        }

        for(int i = 0; i < 3; ++i) {
            if (topPipes.get(i).getBounds().overlaps(bird.getBounds())) {
                return true;
            }
            if (bottomPipes.get(i).getBounds().overlaps(bird.getBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = bird.getX() + viewport.getWorldWidth() / 3 - bird.getWidth() / 2;
        backgroundImage.setX(camera.position.x - camera.viewportWidth / 2);

        if ((camera.position.x + camera.viewportWidth / 2 ) >=
                (groundImage2.getX() + groundImage2.getWidth())) {
            groundImage.setX(groundImage2.getX());
            groundImage2.setX(groundImage.getX() + groundImage.getWidth());
        }

        for(int i = 0; i < 3; ++i) {
            if ((camera.position.x + camera.viewportWidth / 2) >=
                    (bottomPipes.get(i).getX() + bottomPipes.get(i).getWidth() + groundImage.getWidth())) {
                centerBottomPipe = MathUtils.random(-24.0f, 4.0f);
                float centerTopPipe = centerBottomPipe + bottomPipes.get(i).getHeight() + gap;
                topPipes.get(i).setX(step * spacing);
                bottomPipes.get(i).setX(step * spacing);
                topPipes.get(i).setY(centerTopPipe);
                bottomPipes.get(i).setY(centerBottomPipe);
                step++;
            }
        }

        //collisions
        if (checkCollision()) {
            bird.setState(Bird.State.HIT);
            Assets.hitSound.play();
        }

        //
        Array<Boolean> crossed = new Array<Boolean>();
        for(int i = 0; i < 3; ++i) {
            crossed.add(isCrossed(bottomPipes.get(i)));
        }

        stage.act(delta);

        for(int i = 0; i < 3; ++i) {
            if (!crossed.get(i) && isCrossed(bottomPipes.get(i))) {
                hud.incrementScore();
                Assets.pointSound.play();
            }
        }

        stage.draw();

        Stage hudStage = hud.getStage();
        hudStage.act(delta);
        hudStage.draw();

    }

    public boolean isCrossed(Pipe pipe) {
        return bird.getX() >= pipe.getX() + pipe.getWidth() / 2;
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {

        Gdx.app.log(Env.game.TAG, "GameScreen Dispose");
        stage.dispose();
        hud.dispose();
    }

}
