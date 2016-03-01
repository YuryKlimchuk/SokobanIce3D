package com.bottlelab.sokobanice.playscreen.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;

public class CustomCamera extends BaseModel {
	
	public PerspectiveCamera camera;
	World world;
	boolean isCameraOrto = false;
    Vector3 bufferPosition = new Vector3();
	
	public CustomCamera(World _world) {
		
		world = _world;
		
		camera = new PerspectiveCamera(67f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(new Vector3(16f, 16f, 18f));
		camera.up.set(Vector3.Y);
        camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
        camera.far = 3000f;
        camera.near = 0.1f;
        camera.update(); 
        
       
        
	}
	
	
	@Override
	public float getX() {
		return camera.position.x;
	}
	
	@Override
	public float getY() {
		return camera.position.z;
	}
	
	@Override
	public float getZ() {
		return camera.position.z;
	}

	@Override
	public void setX(float x) {
		camera.position.set(x, camera.position.y, camera.position.z);
		camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
		camera.up.set(Vector3.Y);
		//camera.update();
	}
	
	@Override
	public void setY(float y) {
		camera.position.set(camera.position.x, y, camera.position.z);
		camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
		camera.up.set(Vector3.Y);
		//camera.update();
	}
	
	@Override
	public void setZ(float z) {
		camera.position.set(camera.position.x, camera.position.y, z);
		camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
		camera.up.set(Vector3.Y);
		//camera.update();
	}
	
	public void rotateCamera() {
        camera.rotateAround(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2), Vector3.Y, 30f);
        camera.update();
    }
	
	public void setCameraOrto(){
        if(!isCameraOrto) {
            bufferPosition.set(camera.position);
            camera.position.set(new Vector3(world.worldData.maxX/2, 6f, world.worldData.maxZ/2));
            camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
            camera.update();
            isCameraOrto = true;
        } else {
            camera.position.set(bufferPosition);
            camera.lookAt(new Vector3(world.worldData.maxX/2, 0f, world.worldData.maxZ/2));
            camera.up.set(new Vector3().Y);
            camera.update();
            isCameraOrto = false;
        }
    }
	
	public void update(float delta) {
		camera.update();
	}
	
}
