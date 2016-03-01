package com.bottlelab.sokobanice.hintscreen;

import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;

public class HintControllerManager {
	
	
	public StageHintGUI stage;
	World world;
	
	public HintControllerManager(World _world, Render _render) {
		
		world = _world;
		stage = new StageHintGUI(_world, _render, this);
		Gdx.input.setInputProcessor(stage);
		
	}
	
	public void update(float delta) {
		stage.draw();
		stage.act(delta);
	}
	
	public void nextHint(){
		if(world.saver.getCountPos() >= world.worldData.hints.size) return;
		
		WorldUnitData data = world.worldData.hints.get(world.worldData.hints.size - (world.worldData.hints.size - world.saver.heroPos.size));
		
		int flag = (int) data.getAngleY();
		BaseModel object;
		float time = 0;
		
		switch (flag) {
		
		case 0:
			object = world.getIceInPosition((int) data.x, (int) data.z);
			if(object == null) return;
			object.touchDown();
			
			while(time < 1) time += Gdx.graphics.getDeltaTime();
			
			object.touchUp();
			
			break;

		case 1:
			object = world.getBoxInPosition((int) data.x, (int) data.z);
			if(object == null) return;
			object.touchDown();
			
			while(time < 3) time += Gdx.graphics.getDeltaTime();
			
			object.touchUp();

			break;
		}
	}
	
	public void backHint(){
		if(world.saver.getCountPos() == 0) return;
		
		world.saver.setLastStage();

	}

}
