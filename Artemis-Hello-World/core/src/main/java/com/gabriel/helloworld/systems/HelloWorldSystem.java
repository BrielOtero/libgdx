package com.gabriel.helloworld.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.gabriel.helloworld.components.Hello;

@All(Hello.class)
public class HelloWorldSystem extends IteratingSystem {

    protected ComponentMapper<Hello>mHello;

    @Override
    protected void process(int entityId) {
       System.out.print(mHello.get(entityId).message);
    }
}
