package com.bottlelab.sokobanice.playscreen.world.models;

import com.badlogic.gdx.math.Vector3;

public class WorldUnitData {
	
public float x,y,z,angleY;
    
    public WorldUnitData() {}
    
    public WorldUnitData(int _x, int _y, int _z, int _angleY) {
        x = (int) _x;
        y = (int) _y;
        z = (int) _z;
        angleY = (int) _angleY;
    }

    public Vector3 getPosition() {
        return new Vector3(x,y,z);
    }
    
    public float getAngleY(){
    	return angleY;
    }
}
