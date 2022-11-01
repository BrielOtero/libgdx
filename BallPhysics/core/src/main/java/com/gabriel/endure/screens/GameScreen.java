package com.gabriel.endure.screens;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.gabriel.endure.ProjectEndure.BIT_BOX;
import static com.gabriel.endure.ProjectEndure.BIT_CIRCLE;
import static com.gabriel.endure.ProjectEndure.BIT_GROUND;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gabriel.endure.Assets;
import com.gabriel.endure.ProjectEndure;

import java.awt.Polygon;
import java.util.Random;

import de.eskalon.commons.screen.ManagedScreen;

public class GameScreen extends AbstractScreen {
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public GameScreen(final ProjectEndure context) {
        super(context);

    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    protected void create() {
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        Body body;
        CircleShape cShape;

        //create a circle
        bodyDef.position.set(viewport.getScreenX() / 2+1f, viewport.getScreenY() / 2f + 20f);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData("CIRCLE");

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_CIRCLE;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_BOX;
        cShape = new CircleShape();
        cShape.setRadius(0.6f);
        fixtureDef.shape = cShape;
        body.createFixture(fixtureDef);
        cShape.dispose();

        fixtureDef.isSensor = true;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_CIRCLE;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_BOX;

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new float[]{-0.5f, -0.7f, 0.5f, -0.7f});
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        chainShape.dispose();


        //create a box
        bodyDef.position.set(viewport.getScreenX() / 2f - 0.75f, viewport.getScreenY() / 2f - 1f);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData("BOX");


        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_BOX;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_CIRCLE;
        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        body.createFixture(fixtureDef);
        pShape.dispose();

        //create a platform
        bodyDef.position.set(viewport.getScreenX() / 2f, viewport.getScreenY() / 2f - 4f);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        body.setUserData("PLATFORM");


        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        pShape = new PolygonShape();
        pShape.setAsBox(4, 0.5f);
        fixtureDef.shape = pShape;
        body.createFixture(fixtureDef);
        pShape.dispose();

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {

//        if (Gdx.input.justTouched()) {
//            ProjectEndure game = (ProjectEndure) Gdx.app.getApplicationListener();
//            game.pushScreen("blank", "slicing_transition");
//            game.pushScreen("LoadingScreen", "sliding_out_transition");
//        }
        viewport.apply();
        box2DDebugRenderer.render(world, viewport.getCamera().combined);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
    }
}
