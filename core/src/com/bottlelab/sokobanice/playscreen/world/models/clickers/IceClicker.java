package com.bottlelab.sokobanice.playscreen.world.models.clickers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.hero.HeroState;

public class IceClicker implements ModelClicker {

	ColorAttribute colorUp; 
	ColorAttribute colorDown = new ColorAttribute(ColorAttribute.createDiffuse(Color.CHARTREUSE));
	World world;
	
	
	
	public IceClicker(World _world) {
		world = _world;
	}

	@Override
	public boolean touchDown(BaseModel model) {
		colorUp = (ColorAttribute) model.instance.materials.get(0).get(ColorAttribute.Diffuse);
		model.instance.materials.get(0).set(colorDown);
		return false;
	}

	@Override
	public boolean touchUp(BaseModel model) {
		model.instance.materials.get(0).set(colorUp);
		
		switch ((HeroState) world.hero.fsm.getCurrentState()) {
		case STAY:
			if(world.hero.calculatePath(model)) 
				world.hero.fsm.changeState(HeroState.MOVING);
			break;

		case MOVING:
			
			
			world.hero.fsm.changeState(HeroState.STAY);
			world.hero.cb.stopAnim = true;
			world.hero.cb.model = model;
			
			
			break;
		}
		
		return true;
	}

}
