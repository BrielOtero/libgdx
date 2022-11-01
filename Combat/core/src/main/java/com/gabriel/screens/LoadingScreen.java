package com.gabriel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gabriel.Combat;

public class LoadingScreen extends AbstractScreen {
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;

    public LoadingScreen(Combat context) {
        super(context);
    }

    @Override
    protected void create() {
        this.shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(500,500);
    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        /*
         * Render a green circle on a gray background.
         */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(-2000, -1000, 4000, 2000);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(1024 / 2, 720 / 2, 185);
        shapeRenderer.end();

        if(Gdx.input.justTouched()){
            Combat game = (Combat) Gdx.app.getApplicationListener();
            game.pushScreen("GameScreen","slicing_transition");
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
