package com.bottlelab.sokobanice.playscreen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public enum ControllerManagerState implements State<ControllerManager> {
	
	
	ALL_DISABLE(){

		@Override
		public void enter(ControllerManager entity) {
			Gdx.input.setInputProcessor(null);
		}

		@Override
		public void update(ControllerManager entity) {}

		@Override
		public void exit(ControllerManager entity) {}

		@Override
		public boolean onMessage(ControllerManager entity, Telegram telegram) {
			return false;
		}
		
	},
	
	ALL_ENABLE(){

		@Override
		public void enter(ControllerManager entity) {
			
			
			
			entity.inputs.clear();
			entity.inputs.addProcessor(entity.stage);
			entity.inputs.addProcessor(entity.clickController);
			entity.inputs.addProcessor(entity.camController);
			
			Gdx.input.setInputProcessor(entity.inputs);
		}

		@Override
		public void update(ControllerManager entity) {}

		@Override
		public void exit(ControllerManager entity) {}

		@Override
		public boolean onMessage(ControllerManager entity, Telegram telegram) {
			return false;
		}
	},
	
	WIN_CONTROL(){

		@Override
		public void enter(ControllerManager entity) {
			
			Gdx.app.log(this.getClass().toString(), "wINNNN");
			
			entity.stage.stepBackBtn.setTouchable(Touchable.disabled);
			entity.stage.showHideBtn.setTouchable(Touchable.disabled);
			
			
			entity.inputs.clear();
			entity.inputs.addProcessor(entity.stage);
			
			Gdx.input.setInputProcessor(entity.inputs);
			
			
			
		}

		@Override
		public void update(ControllerManager entity) {}

		@Override
		public void exit(ControllerManager entity) {
			entity.stage.showHideBtn.setTouchable(Touchable.enabled);
			entity.stage.stepBackBtn.setTouchable(Touchable.enabled);
		}

		@Override
		public boolean onMessage(ControllerManager entity, Telegram telegram) {
			return false;
		}
		
	},
	
	GUI_ACTIVE(){

		@Override
		public void enter(ControllerManager entity) {
			entity.inputs.clear();
			entity.inputs.addProcessor(entity.stage);
			
			Gdx.input.setInputProcessor(entity.inputs);
			
			
		}

		@Override
		public void update(ControllerManager entity) {}

		@Override
		public void exit(ControllerManager entity) {}

		@Override
		public boolean onMessage(ControllerManager entity, Telegram telegram) {
			return false;
		}
		
	},
	

}
