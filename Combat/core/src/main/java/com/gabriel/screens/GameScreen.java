package com.gabriel.screens;

import static com.gabriel.Combat.BIT_GROUND;
import static com.gabriel.Combat.BIT_PLAYER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gabriel.Combat;

public class GameScreen extends AbstractScreen {
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Body player;

    public GameScreen(final Combat context) {
        super(context);
        addInputProcessor(new Stage(context.getScreenViewport()));

    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    protected void create() {
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        CircleShape cShape;

        //create player
        bodyDef.position.set(4.5f, 3);
        bodyDef.gravityScale = 0.5f;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        player = world.createBody(bodyDef);
        player.setUserData("PLAYER");

        fixtureDef.density = 1;
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;
        final PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        player.createFixture(fixtureDef);
        pShape.dispose();

        //create a room
        Body body;
        bodyDef.position.set(0, 0);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        body.setUserData("GROUND");


        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(new float[]{1, 1, 1, 15, 8, 15, 8, 1});
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        chainShape.dispose();


    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
//        final float speedX;
//        final float speedY;

        //Screen touch
        float xTouchDifference = 0;

        float yTouchDifference = 0;
        if (Gdx.input.justTouched()) {
            float xTouch = Gdx.input.getX();
            float yTouch = Gdx.input.getY();

            Vector2 touchPoint = new Vector2(xTouch, yTouch);
            touchPoint = viewport.unproject(touchPoint);

            Vector2 playerCenter =player.getPosition();

            float touchDistance = touchPoint.dst(playerCenter);

            xTouchDifference = touchDistance - playerCenter.x;
            yTouchDifference = touchPoint.y - playerCenter.y;

        }

        // Keyboard
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            speedX = -3;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            speedX = 3;
//        } else {
//            speedX = 0;
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            speedY = -3;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            speedY = 3;
//        } else {
//            speedY = 0;
//        }

        player.applyLinearImpulse(
                (xTouchDifference - player.getLinearVelocity().x) * player.getMass(),
                (yTouchDifference - player.getLinearVelocity().y) * player.getMass(),
                player.getWorldCenter().x, player.getWorldCenter().y, true
        );
        viewport.apply(true);
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
