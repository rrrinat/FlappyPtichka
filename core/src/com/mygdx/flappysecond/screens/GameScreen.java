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

import javafx.scene.input.KeyCode;

/**
 * Created by rinat on 15.04.2016.
 */
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;

    private float centerBottomPipe = 0.0f;

    private int step = 6;
    private float spacing = 30;
    private Image backgroundImage;
    private Bird bird;

    private Image groundImage;
    private Image groundImage2;

    private Array<Image> topPipes = new Array<Image>();
    private Array<Image> bottomPipes = new Array<Image>();

    @Override
    public void show() {

        batch    = Env.game.getBatch();
        camera   = Env.game.getCamera();
        viewport = Env.game.getViewport();

        stage = new Stage(viewport, batch);

        backgroundImage = new Image(Assets.background);
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.addActor(backgroundImage);

        creatingPipes();
        creatingGround();

        float birdHeight = Assets.yellowTexture.getHeight() * Env.game.TEX_RATIO;
        float birdWidth = birdHeight * 34.0f/24.0f;

        bird = new Bird();
        bird.setSize(birdWidth, birdHeight);
        bird.setBounds(0, 0, birdWidth, birdHeight);
        bird.setPosition(viewport.getWorldWidth() / 3 - birdWidth / 2, viewport.getWorldHeight() / 2 - birdHeight / 2);
        stage.addActor(bird);

        Gdx.input.setInputProcessor(stage);

        Gdx.app.log(Env.game.TAG, "Test Succesful");
    }

    private void creatingPipes() {

        float pipeRatio = (float)Assets.bottomPipeTexture.getWidth()/(float)Assets.bottomPipeTexture.getHeight();
        float pipeHeight = Assets.bottomPipeTexture.getHeight() * Env.game.TEX_RATIO;
        float pipeWidth = pipeHeight * pipeRatio;

        //centerBottomPipe = viewport.getWorldHeight()/2 - pipeHeight;
        centerBottomPipe = -24.0f;

        float firstOffset = spacing * 3;

        for (int i = 0; i < 3; ++i) {
            Image bottomPipe = new Image(Assets.bottomPipeTexture);
            bottomPipe.setSize(pipeWidth, pipeHeight);
            bottomPipe.setBounds(0, 0, pipeWidth, pipeHeight);
            bottomPipe.setPosition(firstOffset + i * spacing, centerBottomPipe);
            bottomPipes.add(bottomPipe);
            stage.addActor(bottomPipe);
        }

        float centerTopPipe = centerBottomPipe + pipeHeight;

        for (int i = 0; i < 3; ++i) {

            Image topPipe = new Image(Assets.topPipeTexture);
            topPipe.setSize(pipeWidth, pipeHeight);
            topPipe.setBounds(0, 0, pipeWidth, pipeHeight);
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

        /*
        float x, y, randomHeight;
        for(int i = 0; i < 2; ++i) {
            if ((camera.position.x + camera.viewportWidth / 2 ) >=
                    (bottomPipes.get(i).getX()+ bottomPipes.get(i).getWidth() + groundImage.getWidth())) {

                x = step * (TUBE_SPACING + Tube.TREE_WIDTH);
                randomHeight = MathUtils.random(TUBE_FLUCTUATION);
                y = LOW_BOTTOM_TUBE + randomHeight;
                bottomTubes.get(i).getBody().setTransform(x, y, 0);

                y = y + TUBE_HEIGHT + TUBE_GAP;
                topTubes.get(i).getBody().setTransform(x, y, 0);

                step++;
            }
        }
        */
        for(int i = 0; i < 3; ++i) {
            if ((camera.position.x + camera.viewportWidth / 2) >=
                    (bottomPipes.get(i).getX() + bottomPipes.get(i).getWidth() + groundImage.getWidth())) {
                topPipes.get(i).setX(step * spacing);
                bottomPipes.get(i).setX(step * spacing);
                step++;
            }
        }

        //collisions
        for(int i = 0; i < 3; ++i) {
            if (topPipes.get(i).)
        }
        //if (bird.)

        // Update stage
        stage.act(delta);
        stage.draw();
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
        // TODO Auto-generated method stub

    }

}
