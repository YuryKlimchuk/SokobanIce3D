package com.bottlelab.sokobanice.playscreen.world.models.clickers;

import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Graph;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Node;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.box.Box;
import com.bottlelab.sokobanice.playscreen.world.models.box.BoxState;



// граф считаеться 2 раза, здесь и в стейте

public class BoxClicker implements ModelClicker {
	
	World world;
	
	public BoxClicker(World _world) {
		Gdx.app.log(this.getClass().toString(), "BoxClicker()");
		world = _world;
	}

	@Override
	public boolean touchDown(BaseModel model) {
		//Gdx.app.log(this.getClass().toString(), "touchDown()");
		return false;
	}

	@Override
	public boolean touchUp(BaseModel model) {
		//Gdx.app.log(this.getClass().toString(), "touchUp()");
		
		if(cheakHeroNearBox(model)) {
			//Gdx.app.log(this.getClass().toString(), "touchUp() внтури if");
			Box box = (Box) model;
			switch ((BoxState) box.fsm.getCurrentState()) {
			case STAY:
				box.fsm.changeState(BoxState.MOVE);
				break;

			case MOVE:
				
				break;
				
			default:
				break;
			}
		}
		
		return false;
	}
	
	private boolean cheakHeroNearBox(BaseModel model){
		Graph graph = world.getGraph();
		
		Node heroNode = graph.getNode(
                (int) world.hero.getX(),
                (int) world.hero.getZ());
        Node boxNode = graph.getNode(
                (int) model.getX(),
                (int) model.getZ());
        
        boolean heroNearBox = false;	// определяет рядом ли hero c ящиком
        for(int i = 0; i < boxNode.connections.size; i++) {
            if(heroNode.getIndex() == boxNode.connections.get(i).getToNode().getIndex()) 
            	heroNearBox = true;   
        }
        
        return heroNearBox;
	}

}
