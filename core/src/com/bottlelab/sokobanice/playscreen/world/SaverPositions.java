package com.bottlelab.sokobanice.playscreen.world;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;
import com.bottlelab.sokobanice.playscreen.world.models.box.Box;
import com.bottlelab.sokobanice.playscreen.world.models.box.BoxState;
import com.bottlelab.sokobanice.playscreen.world.models.hero.HeroState;
import com.bottlelab.sokobanice.utils.Constants;


/**
 
 */
public class SaverPositions {

    World world;
    public Array<WorldUnitData> heroPos = new Array<WorldUnitData>();
    public Array<Array<WorldUnitData>> boxesPos = new Array<Array<WorldUnitData>>();

    public SaverPositions(World _world) {
        world = _world;
        for (int i = 0; i < world.box.size; i++) {
            boxesPos.add(new Array<WorldUnitData>());
        }
    }

    public void savePos() {
    	heroPos.add(new WorldUnitData(
                (int) world.hero.getX(),
                (int) world.hero.getY(),
                (int) world.hero.getZ(),
                (int) world.hero.getAngleY()));

        //Gdx.app.log("Penguin posion is saved ", " " + heroPos.get(heroPos.size - 1).getPosition());

        for (int i = 0; i < world.box.size; i++) {
            boxesPos.get(i).add(new WorldUnitData(
            		(int) world.box.get(i).getX(),
            		(int) world.box.get(i).getY(),
            		(int) world.box.get(i).getZ(),
            		(int) world.box.get(i).getAngleY()
            ));
            //Gdx.app.log("Box #" + i +" posion is saved ", " " + boxesPos.get(i).get(boxesPos.get(i).size - 1).getPosition());
        }

    }
    
    public int getCountPos() {
    	return heroPos.size;
    }

    public void removePos() {
        if(heroPos.size > 0) {
        	heroPos.removeIndex(heroPos.size - 1);
            for (int i = 0; i < world.box.size; i++) {
                boxesPos.get(i).removeIndex(boxesPos.get(i).size -1);
            }
        }
    }
    
    public void setLastStage() {
    	if(getCountPos() == 0) return;
    	Gdx.app.log(this.getClass().toString(), "setLastStage()");
    	
    	world.hero.fsm.changeState(HeroState.STAY);
    	for (Box box : world.box) 
    		box.fsm.changeState(BoxState.STAY);
    	
    	Timeline outTimeline = Timeline.createSequence();
    	outTimeline.beginParallel()
    		.push(Tween.to(world.hero, Constants.POS_XYZ, 0.5f)
    				.target(heroPos.get(heroPos.size -1).x, heroPos.get(heroPos.size -1).y, heroPos.get(heroPos.size -1).z)
					.ease(TweenEquations.easeNone)
    		);
    	Gdx.app.log(this.getClass().toString(), "Hero : " + world.hero.getPosistion());	
    		
    	for (int i = 0; i < world.box.size; i++) {
    			
    		Box box = world.box.get(i);
    		WorldUnitData data = boxesPos.get(i).get(boxesPos.get(i).size -1);
    			
    		outTimeline
    			.push(Tween.to(box, Constants.POS_XYZ, 0.5f)
    					.target(data.x, data.y, data.z)
    					.ease(TweenEquations.easeNone)
    			);
		}
    	
    	outTimeline.end();
    	outTimeline.start(world.tweenManager);
    	
    	heroPos.removeIndex(heroPos.size - 1);
    	for (Array<WorldUnitData> data : boxesPos) 
			data.removeIndex(data.size -1);
    
    }


}
