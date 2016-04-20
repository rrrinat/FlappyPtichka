package com.mygdx.flappysecond;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rinat on 20.04.2016.
 */
public class Hud implements Disposable {

    private Viewport viewport;
    private Batch batch;
    private Stage stage;

    private BitmapFont font;

    private int score = 0;

    private Label.LabelStyle labelStyle;
    private Label scoreLabel;

    public Hud() {

        batch = Env.game.getBatch();
        viewport = Env.game.getHudViewport();
        stage = new Stage(viewport, batch);

        FontManager manager = Env.game.getFontManager();
        font = manager.getFont();

        creatingTable();
    }

    public void creatingTable() {

        Table table = new Table();
        table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        //table.debug();

        labelStyle = new Label.LabelStyle(font, Color.WHITE);
        scoreLabel = new Label(String.format("%d", score), labelStyle);

        table.add(scoreLabel).expandY().padBottom(450);
        stage.addActor(table);
    }

    public void incrementScore() {

        scoreLabel.setText(String.format("%d", ++score));
    }

    @Override
    public void dispose() {

        Gdx.app.log(Env.game.TAG, "Hud Dispose");
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}


