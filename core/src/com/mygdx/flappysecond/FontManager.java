package com.mygdx.flappysecond;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by rinat on 20.04.2016.
 */
public class FontManager implements Disposable {

    private String table = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэёячсмитьбю!#$%&'()*+,./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^-`abcdefghijklmnopqrstuvwxyz{|}~";

    private BitmapFont font;

    public FontManager() {

        generate();
    }

    private void generate()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/04B_19.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = table;
        //parameter.minFilter = TextureFilter.Linear;
        //parameter.magFilter = TextureFilter.Linear;

        parameter.size = 54;
        font = generator.generateFont(parameter);

        //parameter.size = 34;
        //font2 = generator.generateFont(parameter);

        generator.dispose();
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void dispose() {

        Gdx.app.log(Env.game.TAG, "Font Manager Dispose");
        font.dispose();
    }
}
