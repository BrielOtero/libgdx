package com.gabriel.screens;

import com.gabriel.Combat;

public class BlankScreen extends AbstractScreen {

    public BlankScreen(Combat context) {
        super(context);
    }

    @Override
    protected void create() {
        // do nothing
    }

    @Override
    public void hide() {
        // do nothing
    }

    @Override
    public void render(float delta) {
        // do nothing except having the screen cleared
    }

    @Override
    public void resize(int width, int height) {
        // do nothing
    }

    @Override
    public void dispose() {
        // do nothing
    }

}