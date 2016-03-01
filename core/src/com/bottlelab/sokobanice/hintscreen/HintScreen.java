package com.bottlelab.sokobanice.hintscreen;

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

public class HintScreen implements Screen {

	GameManager gameManager;
	HintControllerManager contrManager;
	World world;
	Render render;
	Stage stage;
	
	public HintScreen(int _boxCount, int _level) {
		world = new World(_boxCount, _level);
		render = new Render(world);
		contrManager = new HintControllerManager(world, render);
	}
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(215/255f, 204/255f, 200/255f, 1f);
        
        world.update(delta);
        render.render(delta);
        contrManager.update(delta);
        
        
        stage.getBatch().begin();
    	gameManager.resManager.getFont26().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 300);
    	gameManager.resManager.getFont32().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 250);
    	stage.getBatch().end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
