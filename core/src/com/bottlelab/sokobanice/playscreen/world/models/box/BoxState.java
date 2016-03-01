package com.bottlelab.sokobanice.playscreen.world.models.box;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Graph;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Node;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;
import com.bottlelab.sokobanice.playscreen.world.models.hero.Hero;
import com.bottlelab.sokobanice.utils.Constants;



// перерабоать, много лишнего
public enum BoxState implements State<Box> {
	
	GLOABAL_STATE_WITH_MARK() {

		@Override
		public void enter(Box box) {
			box.instance.materials.get(0).set(box.onMarkColor);	
			//Gdx.app.log(this.getClass().toString(), "GLOABAL_STATE_WITH_MARK().enter");
		}

		@Override
		public void update(Box entity) {
			setGlobalState(entity);
			//Gdx.app.log(this.getClass().toString(), "GLOABAL_STATE_WITH_MARK");
			
		}

		@Override
		public void exit(Box entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onMessage(Box entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	
	GLOABAL_STATE_WITHOUT_MARK() {

		@Override
		public void enter(Box box) {
			box.instance.materials.get(0).set(box.withoutMarkColor);	
			//Gdx.app.log(this.getClass().toString(), "GLOABAL_STATE_WITHOUT_MARK().enter");
		}

		@Override
		public void update(Box entity) {
			setGlobalState(entity);
			//Gdx.app.log(this.getClass().toString(), "GLOABAL_STATE_WITHOUT_MARK");
			
		}

		@Override
		public void exit(Box entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onMessage(Box entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	
	STAY() {

		@Override
		public void enter(Box entity) {
			
		}

		@Override
		public void update(Box entity) {
			
		}

		@Override
		public void exit(Box entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onMessage(Box entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	
	MOVE() {

		@Override
		public void enter(Box box) {
			//Gdx.app.log("BoxState.MOVE", "enter()");
			box.world.saver.savePos();
			Hero hero = box.hero;
			Timeline outTimeline = Timeline.createSequence();
			Graph graph = box.world.getGraph();
			int angleY = (int) box.hero.getAngleY();
			
			
			switch (setDirection(hero, box)) {
			case Constants.DIR_X_POS:
				if(graph.getNode((int) (box.getX() + 1), (int) box.getZ()).type == Node.TILE_FLOOR ) {
					//Gdx.app.log("BoxState.MOVE", "enter() DIR_X_POS");
					switch (angleY) {
					
					case -90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(90)
		                        .ease(TweenEquations.easeNone)
						);
						break;
					
					case 0:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(90)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 90:
						break;
						
					case 180:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*2)
								.target(90)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					default:
						break;
					}
					
					outTimeline
						.beginParallel()
							.push(Tween
									.to(box, Constants.POS_X, Constants.VELOSITY_MOVE)
									.target(box.getX() + 1)
									.ease(TweenEquations.easeNone))
							.push(Tween
									.to(hero, Constants.POS_X, Constants.VELOSITY_MOVE)
									.target(hero.getX() + 1)
									.ease(TweenEquations.easeNone))
						.end()
						.setCallback(box.cb)
						.setCallbackTriggers(TweenCallback.COMPLETE)
						.start(box.hero.tweenManager);
				}
				break;
			
			case Constants.DIR_X_NEG:
				if(graph.getNode((int) (box.getX() - 1), (int) box.getZ()).type == Node.TILE_FLOOR ) {
					//Gdx.app.log("BoxState.MOVE", "enter() DIR_X_NEG");
					switch (angleY) {
					
					case -90:
						break;
					
					case 0:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(-90)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(-90)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 180:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*2)
								.target(270)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					default:
						break;
					}
					
					outTimeline
						.beginParallel()
							.push(Tween
									.to(box, Constants.POS_X, Constants.VELOSITY_MOVE)
									.target(box.getX() - 1)
									.ease(TweenEquations.easeNone))
							.push(Tween
									.to(hero, Constants.POS_X, Constants.VELOSITY_MOVE)
									.target(hero.getX() - 1)
									.ease(TweenEquations.easeNone))
						.end()
						.setCallback(box.cb)
						.setCallbackTriggers(TweenCallback.COMPLETE)
						.start(box.hero.tweenManager);;
				}
				break;
			
			case Constants.DIR_Z_POS:
				if(graph.getNode((int) box.getX(), (int) (box.getZ() + 1)).type == Node.TILE_FLOOR ) {
					switch (angleY) {
					
					case -90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(0)
		                        .ease(TweenEquations.easeNone)
						);
						break;
					
					case 0:
						break;
						
					case 90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(0)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 180:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*2)
								.target(0)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					default:
						break;
					}
					
					outTimeline
						.beginParallel()
							.push(Tween
									.to(box, Constants.POS_Z, Constants.VELOSITY_MOVE)
									.target(box.getZ() + 1)
									.ease(TweenEquations.easeNone))
							.push(Tween
									.to(hero, Constants.POS_Z, Constants.VELOSITY_MOVE)
									.target(hero.getZ() + 1)
									.ease(TweenEquations.easeNone))
						.end()
						.setCallback(box.cb)
						.setCallbackTriggers(TweenCallback.COMPLETE)
						.start(box.hero.tweenManager);;
				}
				break;
			
			case Constants.DIR_Z_NEG:
	
				if(graph.getNode((int) box.getX(), (int) (box.getZ() - 1)).type == Node.TILE_FLOOR ) {
					switch (angleY) {
					
					case -90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(-180)
		                        .ease(TweenEquations.easeNone)
						);
						break;
					
					case 0:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(180)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 90:
						outTimeline.push(Tween.to(box.hero, Constants.ANGLE_Y, Constants.VELOSITY_ROT*1)
								.target(180)
		                        .ease(TweenEquations.easeNone)
						);
						break;
						
					case 180:
						break;
						
					default:
						break;
					}
					
					outTimeline
						.beginParallel()
							.push(Tween
									.to(box, Constants.POS_Z, Constants.VELOSITY_MOVE)
									.target(box.getZ() - 1)
									.ease(TweenEquations.easeNone))
							.push(Tween
									.to(hero, Constants.POS_Z, Constants.VELOSITY_MOVE)
									.target(hero.getZ() - 1)
									.ease(TweenEquations.easeNone))
						.end()
						.setCallback(box.cb)
						.setCallbackTriggers(TweenCallback.COMPLETE)
						.start(box.hero.tweenManager);
				}
				break;

			default:
				break;
			}
			
		}

		@Override
		public void update(Box entity) {	
		}

		@Override
		public void exit(Box entity) {
		}

		@Override
		public boolean onMessage(Box entity, Telegram telegram) {
			return false;
		}	
	};
	
	
	protected void setGlobalState(Box box) {
		
		int x = (int) box.getX();
		int y = (int) box.getY();
		int z = (int) box.getZ();
		
		boolean isOnMark = false;
		
		for (WorldUnitData data : box.goalPosition) {
			int x1 = (int) data.x;
			int y1 = (int) data.y;
			int z1 = (int) data.z;
			//Gdx.app.log(this.getClass().toString(), "x = " + x + "; z = " + z);
			//Gdx.app.log(this.getClass().toString(), "x1 = " + x1 + "; z1 = " + z1);
			//Gdx.app.log(this.getClass().toString(), "" + isOnMark);
			if(x == x1 && y == y1 && z == z1) {
				isOnMark = true;
				//Gdx.app.log(this.getClass().toString(), "" + isOnMark);
				//continue;
			}
		}
		
		if(isOnMark) {
			box.fsm.setGlobalState(BoxState.GLOABAL_STATE_WITH_MARK);
		} else {
			box.fsm.setGlobalState(BoxState.GLOABAL_STATE_WITHOUT_MARK);
		}
		
		
	}


	protected int setDirection(Hero hero, Box box) {
		
		int DIRECTION = Constants.DIR_NULL;
		
		int deltaX = (int) hero.getX() - (int) box.getX();
		int deltaZ = (int) hero.getZ() - (int) box.getZ();
		
		if (deltaX != 0) {
			//движение по X
			if(deltaX < 0) DIRECTION = Constants.DIR_X_POS;
			else if (deltaX > 0) DIRECTION = Constants.DIR_X_NEG;
		} else if(deltaZ != 0) {
			//движение по Z
			if(deltaZ < 0) DIRECTION = Constants.DIR_Z_POS;
			else if (deltaZ > 0) DIRECTION = Constants.DIR_Z_NEG;
		}
		return DIRECTION;
	}

}
