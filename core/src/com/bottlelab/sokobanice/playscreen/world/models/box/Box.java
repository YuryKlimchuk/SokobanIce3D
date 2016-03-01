package com.bottlelab.sokobanice.playscreen.world.models.box;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;
import com.bottlelab.sokobanice.playscreen.world.models.hero.Hero;

public class Box extends BaseModel {
	


	
	ColorAttribute withoutMarkColor, onMarkColor;
	Hero hero;
	BoxTwenCallBack cb;
	
	public DefaultStateMachine<Box> fsm = new DefaultStateMachine<Box>(this, BoxState.STAY, BoxState.GLOABAL_STATE_WITHOUT_MARK);
	
	Array<WorldUnitData> goalPosition;
	
	public Box (ModelInstance _instance, String _name, WorldUnitData data, Array<WorldUnitData> _goalPosition, Hero _hero, World _world) {
		Gdx.app.log(this.getClass().toString(), _name);
    	instance = new ModelInstance(_instance);
    	name = _name;
    	goalPosition = _goalPosition;
    	hero = _hero;
    	setUnitData(data);
    	world = _world;
    	cb = new BoxTwenCallBack(world);
    	
    	/*
    	Vector3 position = new Vector3();
    	instance.transform.getTranslation(position);
    	Gdx.app.log("POSITION", "" + position);
    	Vector3 scale = new Vector3();
    	instance.transform.getScale(scale);
    	Gdx.app.log("SCALE", "" + scale);
    	Gdx.app.log(null, "" + instance.transform);
    	*/
    	
    	setAngleY(MathUtils.random(0, 10) * 90f);
    	
    	initColor();
    	instance.materials.get(0).set(onMarkColor);
	}
	
	private void initColor(){
		withoutMarkColor = (ColorAttribute) instance.materials.get(0).get(ColorAttribute.Diffuse);
		onMarkColor = new ColorAttribute(ColorAttribute.createDiffuse(Color.RED));
	}


	

	@Override
	public void draw(ModelBatch mBatch, Environment env) {
		
		fsm.update();
		
		switch ((BoxState) fsm.getGlobalState()) {
		case GLOABAL_STATE_WITH_MARK:
			instance.materials.get(0).set(onMarkColor);
			break;

		case GLOABAL_STATE_WITHOUT_MARK:
			instance.materials.get(0).set(withoutMarkColor);
			break;
		default:
			break;
		}
		
		super.draw(mBatch, env);
	}
}
