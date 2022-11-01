package com.gabriel.endure;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gabriel.endure.screens.BlankScreen;
import com.gabriel.endure.screens.GameScreen;
import com.gabriel.endure.screens.LoadingScreen;

import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.transition.ScreenTransition;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;
import de.eskalon.commons.screen.transition.impl.HorizontalSlicingTransition;
import de.eskalon.commons.screen.transition.impl.ShaderTransition;
import de.eskalon.commons.screen.transition.impl.SlidingDirection;
import de.eskalon.commons.screen.transition.impl.SlidingOutTransition;

public class ProjectEndure extends ManagedGame<ManagedScreen, ScreenTransition> {
    public static final String TITLE = "MyGdxGame";
    private SpriteBatch batch;
    private FitViewport screenViewport;

    public static  final short BIT_PLAYER = 1 << 0;
    public static  final short BIT_BOX = 1 << 1;
    public static  final short BIT_GROUND = 1 << 2;

    private World world;
    private WorldContactListener worldContactListener;
    private Box2DDebugRenderer box2DDebugRenderer;

    private static final float FIXED_TIME_STEP = 1/60f;
    private float accumulator;

    private BlendingTransition blendingTransition;
    private SlidingOutTransition slidingOutTransition;
    private HorizontalSlicingTransition slicingTransition;
    private ShaderTransition shaderTransition;

    @Override
    public void create() {
        super.create();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        accumulator=0;
        // Load Assets
        Assets.load();

        Box2D.init();
        world = new World(new Vector2(0,0),true);
        worldContactListener=new WorldContactListener();
        world.setContactListener(worldContactListener);
        box2DDebugRenderer = new Box2DDebugRenderer();

        screenViewport= new FitViewport(9,16);

        // Do some basic stuff
        this.batch = new SpriteBatch();

        // Add screens
        this.screenManager.addScreen("GameScreen", new GameScreen(this));
        this.screenManager.addScreen("LoadingScreen", new LoadingScreen(this));
        this.screenManager.addScreen("blank", new BlankScreen(this));

        // Add transitions
        blendingTransition = new BlendingTransition(batch, 1F, Interpolation.pow2In);
        screenManager.addScreenTransition("blending_transition", blendingTransition);
        slidingOutTransition = new SlidingOutTransition(batch, SlidingDirection.DOWN, 0.35F);
        screenManager.addScreenTransition("sliding_out_transition", slidingOutTransition);
        slicingTransition = new HorizontalSlicingTransition(batch, 5, 1F);
        screenManager.addScreenTransition("slicing_transition", slicingTransition);
        shaderTransition = new ShaderTransition(1f);
        screenManager.addScreenTransition("shader_transition", shaderTransition);


        // Push the first screen using a blending transition
//        pushScreen("blank", "slicing_transition");
        pushScreen("GameScreen", "blending_transition");


        Gdx.app.debug("Game", "Initialization finished.");
    }

    public World getWorld(){
        return world;
    }
    public FitViewport getScreenViewport(){
        return screenViewport;
    }

    public void pushScreen(String screenName, String transitionName) {
        this.screenManager.pushScreen(screenName, transitionName);
    }



    @Override
    public de.eskalon.commons.screen.ScreenManager<ManagedScreen, ScreenTransition> getScreenManager() {
        return super.getScreenManager();
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    @Override
    public void dispose() {
        super.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
    }

    @Override
    public void render() {
    super.render();
    accumulator+= Math.min(0.25f,Gdx.graphics.getDeltaTime());
        while (accumulator >= FIXED_TIME_STEP) {
            world.step(FIXED_TIME_STEP,6,2);
            accumulator-=FIXED_TIME_STEP;
        }

        //final float alpha = accumulator/FIXED_TIME_STEP;
    }
}