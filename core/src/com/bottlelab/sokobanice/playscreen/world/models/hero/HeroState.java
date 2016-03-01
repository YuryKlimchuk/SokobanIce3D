package com.bottlelab.sokobanice.playscreen.world.models.hero;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.utils.Array;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Node;
import com.bottlelab.sokobanice.utils.Constants;

public enum HeroState implements State<Hero> {

	STAY(){

		@Override
		public void enter(Hero entity) {
			
			entity.animController.setAnimation("Armature|dance",-1);
		}
		
		@Override
		public void exit(Hero entity) {
			//Gdx.app.log("HeroState.STAY", "exit()");
		}
	},
	
	MOVING_WITH_BOX() {

		@Override
		public void enter(Hero entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void exit(Hero entity) {
			// TODO Auto-generated method stub
			
		}

		
		
	},

	
	MOVING(){

		@Override
		public void enter(Hero entity) {
			//Gdx.app.log("HeroState.MOVING", "enter()");
			
			entity.animController.setAnimation("Armature|move",-1);
			
			entity.world.saver.savePos();
			entity.cb.stopAnim = false;
			entity.cb.model = null;
			Timeline outTimeline = Timeline.createSequence();
			int bufferAngleY = (int) entity.getAngleY();
			Array<Node> nodes = entity.path.nodes;
			
			
			for (int i = 1; i < nodes.size; i++) {
				// определ¤ем в какую сторону необходимо двигатьс¤
                int deltaX = (int) nodes.get(i).x - nodes.get(i - 1).x;
                int deltaZ = (int) nodes.get(i).z - nodes.get(i - 1).z;
                
                //Gdx.app.log(this.getClass().toString(), "node x = " + nodes.get(i).x + "; node z = " + nodes.get(i).z);
                 
                int time = 0;
                int targetAngle = 0;
                
                // движение по оси X
                if(deltaX != 0) {
                	// если не смотрим в направлении +Х, выполняем поворот
                	if(deltaX > 0 && bufferAngleY != 90) {
                		
                		switch (bufferAngleY) {
						case 0:
							time = 1;
							targetAngle = 90;
							break;

						case 180:
							time = 1;
							targetAngle = 90;
							break;
						case -90:
							time = 2;
							targetAngle = 90;
							break;
						}
                		outTimeline
                        .push(Tween.to(entity, Constants.ANGLE_Y, Constants.VELOSITY_ROT*time)
                        .target(targetAngle)
                        .ease(TweenEquations.easeNone)
                        .setCallback(entity.cb)
                        .setCallbackTriggers(TweenCallback.END));
                		bufferAngleY = 90;
                		          
                	} else if(deltaX < 0 && bufferAngleY != -90) {
                		switch (bufferAngleY) {
						case 0:
							time = 2;
							targetAngle = -90;
							break;

						case 90:
							time = 1;
							targetAngle = -90;
							break;
							
						case 180:
							time = 1;
							targetAngle = 270;
							break;
                		}
                		outTimeline
                        .push(Tween.to(entity, Constants.ANGLE_Y, Constants.VELOSITY_ROT*time)
                        .target(targetAngle)
                        .ease(TweenEquations.easeNone)
                        .setCallback(entity.cb)
                        .setCallbackTriggers(TweenCallback.END));
                		bufferAngleY = -90;
                	}
                	
                	outTimeline
                    .push(Tween.to(entity, Constants.POS_X, Constants.VELOSITY_MOVE)
                    .target(nodes.get(i).x)
                    .ease(TweenEquations.easeNone)
                    .setCallback(entity.cb)
                    .setCallbackTriggers(TweenCallback.END));
                	
                } else if(deltaZ != 0) {
                	// если не смотрим в направлении +Х, выполняем поворот
                	if(deltaZ > 0 && bufferAngleY != 0) {
                		switch (bufferAngleY) {
						case -90:
							time = 1;
							targetAngle = 0;
							break;

						case 180:
							time = 2;
							targetAngle = 0;
							break;
						case 90:
							time = 1;
							targetAngle = 0;
							break;
						}
                		outTimeline
                        .push(Tween.to(entity, Constants.ANGLE_Y, Constants.VELOSITY_ROT*time)
                        .target(targetAngle)
                        .ease(TweenEquations.easeNone)
                        .setCallback(entity.cb)
                        .setCallbackTriggers(TweenCallback.END));
                		bufferAngleY = 0;
                	} else if(deltaZ < 0 && bufferAngleY != 180) {
                		
                		switch (bufferAngleY) {
						case 0:
							time = 2;
							targetAngle = 180;
							break;

						case 90:
							time = 1;
							targetAngle = 180;
							break;
							
						case -90:
							time = 1;
							targetAngle = -180;
							break;
                		}
                		outTimeline
                        .push(Tween.to(entity, Constants.ANGLE_Y, Constants.VELOSITY_ROT*time)
                        .target(targetAngle)
                        .ease(TweenEquations.easeNone)
                        .setCallback(entity.cb)
                        .setCallbackTriggers(TweenCallback.END));
                		bufferAngleY = 180;
                	}
                	
                	outTimeline
                    .push(Tween.to(entity, Constants.POS_Z, Constants.VELOSITY_MOVE)
                    .target(nodes.get(i).z)
                    .ease(TweenEquations.easeNone)
                    .setCallback(entity.cb)
                    .setCallbackTriggers(TweenCallback.END));
                }
			}
			
			outTimeline
			.setCallback(entity.cb)
			.setCallbackTriggers(TweenCallback.COMPLETE)
            .start(entity.tweenManager);
			entity.path.clear();
			//Gdx.app.log(this.getClass().toString(), "startTween");
			
		}
		
		@Override
		public void exit(Hero entity) {
			//Gdx.app.log("HeroState.MOVING", "exit()");
			
		}

		
	};
	
	@Override
	public void update(Hero entity) {
		//Gdx.app.log("HeroState.MOVING", "update()");
	}

	

	@Override
	public boolean onMessage(Hero entity, Telegram telegram) {
		return false;
	}
}
