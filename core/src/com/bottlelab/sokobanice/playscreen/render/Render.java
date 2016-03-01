package com.bottlelab.sokobanice.playscreen.render;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.utils.Constants;

public class Render {
	
	private World world;
	private Environment env;
	private ModelBatch mBatch;
	//public PerspectiveCamera camera;
	public CustomCamera customCamera;
	
	public Render(World _world) {
		
		world = _world;
		//createCamera();
		createEnv();
		createModelBatch();
		customCamera = new CustomCamera(world);
		
		
	}
	
	/*
	private void createCamera() {	
		camera = new PerspectiveCamera(67f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());     
		camera.position.set(new Vector3(16f, 16f, 18f));
		camera.up.set(Vector3.Y);
        camera.lookAt(new Vector3(0f, 0f, 0f));
        camera.far = 300000f;
        camera.near = 0.1f;
        camera.update();  
	}
	*/
	private void createEnv() {
		env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
        env.set(new ColorAttribute(ColorAttribute.Fog, .3f, .55f, 1, 1));
        env.add(new DirectionalLight().set(.3f, .3f, .3f, -.2f, 0.6f, .8f));
        env.add(new DirectionalLight().set(1f, 1f, 1f, .2f, -0.6f, -.8f));
        env.add(new PointLight().set(1, 1, 1, new Vector3(10f, 10f, -10f), 200));
    }
	
	private void createModelBatch() {
        mBatch = new ModelBatch();
    }


	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(0.3f, 0.13f, 0.991f, 1f);
		
        customCamera.update(delta);
        
		mBatch.begin(customCamera.camera);
			for (BaseModel model : world.ice) model.draw(mBatch, env);
			for (BaseModel model : world.box) model.draw(mBatch, env);
			//world.iceberg.draw(mBatch, env);
			world.hero.draw(mBatch, env);	
		mBatch.end();
	}
}
