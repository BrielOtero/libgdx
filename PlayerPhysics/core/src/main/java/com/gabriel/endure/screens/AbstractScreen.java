package com.gabriel.endure.screens;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gabriel.endure.ProjectEndure;

import de.eskalon.commons.screen.ManagedScreen;

public abstract class AbstractScreen extends ManagedScreen {
    protected  final ProjectEndure context;
    protected final FitViewport viewport;
    protected final World world;
    protected final Box2DDebugRenderer box2DDebugRenderer;

    public AbstractScreen(final  ProjectEndure context){
        this.context = context;
        viewport = context.getScreenViewport();
        this.world=context.getWorld();
        this.box2DDebugRenderer= context.getBox2DDebugRenderer();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
