package com.bottlelab.sokobanice.playscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.playscreen.controller.ControllerManager;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.utils.Constants;

public class PlayScreen implements Screen {
	
	private World world;
	private Render render;
	private ControllerManager contrManager;
	
	
	GameManager gameManager;
	Stage stage;

	public PlayScreen(int _boxCount, int _level) {
		world = new World(_boxCount, _level);
		render = new Render(world);
		contrManager = new ControllerManager(world, render);
	}
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		gameManager = (GameManager) Gdx.app.getApplicationListener();
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(179/255f, 229/255f, 252/255f, 1f);
        
        world.update(delta);
        render.render(delta);
        contrManager.update(delta);
        
        
        stage.getBatch().begin();
    	gameManager.resManager.getFont26().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 450);
    	gameManager.resManager.getFont32().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 400);
    	stage.getBatch().end();
        
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().toString(), "dispouse()");
	}

}
