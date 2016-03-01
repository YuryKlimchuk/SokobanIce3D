package com.bottlelab.sokobanice.playscreen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;

public class ClickController implements InputProcessor {

	World world;
	PerspectiveCamera camera;
	private BaseModel model;

	public ClickController(World _world, Render _render) {
		world = _world;
		camera = _render.customCamera.camera;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		BaseModel model = getModel(screenX, screenY);
		if(model != null) {
			//Gdx.app.log(this.getClass().toString(), "попали по плитке!!!");
			model.touchDown();
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(model != null) {
			//Gdx.app.log(this.getClass().toString(), "попали по плитке!!!");
			model.touchUp();
			return true;
		}
		return false;
	}
	
	public BaseModel getModel(int screenX, int screenY) {
		//Gdx.app.log(this.getClass().toString(), "getModel(), X = " + screenX + "; Y = " + screenY);
        Ray ray = camera.getPickRay(screenX, screenY);
        model = null;
        float distance = -1;
        
        // проверяем был ли клик по льду
        for (int i = 0; i < world.ice.size; i++) {
            //Gdx.app.log(this.getClass().toString(), "setIndexSelectedInst(),i = " + i);

            Vector3 position = world.ice.get(i).getPosistion();
            BoundingBox bound = world.ice.get(i).getBound();

            float dist2 = ray.origin.dst2(position);
            if (distance >= 0f && dist2 > distance) {
                //Gdx.app.log(this.getClass().toString(), "continue");
                continue;
            }
            if (Intersector.intersectRayBoundsFast(ray, bound)) {
                model = world.ice.get(i);
                distance = dist2;
                //Gdx.app.log(this.getClass().toString(), "dist ");
            }
        }
        
        for (int i = 0; i < world.box.size; i++) {
            //Gdx.app.log(this.getClass().toString(), "setIndexSelectedInst(),i = " + i);

            Vector3 position = world.box.get(i).getPosistion();
            BoundingBox bound = world.box.get(i).getBound();

            float dist2 = ray.origin.dst2(position);
            if (distance >= 0f && dist2 > distance) {
                //Gdx.app.log(this.getClass().toString(), "continue");
                continue;
            }
            if (Intersector.intersectRayBoundsFast(ray, bound)) {
                model = world.box.get(i);
                distance = dist2;
                //Gdx.app.log(this.getClass().toString(), "dist ");
            }
        }
		return model;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

}
