package com.gabriel.helloworld.lwjgl3;


import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.gabriel.helloworld.components.Hello;
import com.gabriel.helloworld.systems.HelloWorldSystem;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
	public static void main(String[] args) {

		WorldConfiguration setup= new WorldConfigurationBuilder().with(new HelloWorldSystem()).build();

		World world = new World(setup);

		int entityId = world.create();
		world.edit(entityId).create(Hello.class).message="\n\rHello World\n\r";
		world.process();
	}
}