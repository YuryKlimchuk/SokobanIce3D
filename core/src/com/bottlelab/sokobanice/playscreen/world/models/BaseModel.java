package com.bottlelab.sokobanice.playscreen.world.models;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.clickers.ModelClicker;

public class BaseModel {
	public ModelInstance instance;
	protected String name;
	public World world;
	
	private ModelClicker clicker = null;
	
	public BaseModel() {}
	
	public BaseModel(ModelInstance _instance, String _name, WorldUnitData data) {
		//Gdx.app.log(this.getClass().toString(), _name);
    	instance = new ModelInstance(_instance);
    	name = _name;
    	setUnitData(data);
    	/*
    	Vector3 position = new Vector3();
    	instance.transform.getTranslation(position);
    	Gdx.app.log("POSITION", "" + position);
    	Vector3 scale = new Vector3();
    	instance.transform.getScale(scale);
    	Gdx.app.log("SCALE", "" + scale);
    	Gdx.app.log(null, "" + instance.transform);
    	*/
	}
	
	public BaseModel(ModelInstance _instance){
		instance = _instance;
	}
	
	public BoundingBox getBound() {
        BoundingBox bound = new BoundingBox();
        instance.calculateBoundingBox(bound);
        bound.mul(instance.transform);
        return bound;
    }
	
	public float getX() {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "getX() = " + position.x);
        return position.x;
    }

    
    public float getY() {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "getY() = " + position.y);
        return position.y;
    }

    
    public float getZ() {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "getZ() = " + position.z);
        return position.z;
    }
    
    public Vector3 getPosistion() {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        //Gdx.app.log(this.getClass().toString(), "position = " + position);
        return  position;
    }

    public float getAngleY() { 
    	Vector3 axisVec = new Vector3();
    	
    	float angle = (int) (instance.transform.getRotation(new Quaternion()).getAxisAngle(axisVec) * axisVec.nor().y);
    	//return angle = angle < 0 ? angle + 360 : angle;
    	return angle;
    }
    
    /*
    @Override
    // возвращает угол поворота вокруг оси Y
    public float getAngleY(){
        Quaternion quaternion = new Quaternion();
        instance.transform.getRotation(quaternion);
        //Gdx.app.log(this.getClass().getName(), "getAngle() = " + quaternion.getAngleAround(Vector3.Y));
        return quaternion.getAngleAround(Vector3.Y);
    }
*/
    
    public void setX(float x) {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        position.set(x, position.y, position.z);
        instance.transform.setTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "setX() = " + x);
    }

    
    public void setY(float y) {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        position.set(position.x, y, position.z);
        instance.transform.setTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "setY() = " + y);
    }
 
    public void setZ(float z) {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        position.set(position.x, position.y, z);
        instance.transform.setTranslation(position);
        //Gdx.app.log(this.getClass().getName(), "setZ() = " + z);
    }
  
    // устанавливает угол поворота вокруг оси Y
    public void setAngleY(float angle) {
        Quaternion quaternion = new Quaternion();
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        instance.transform.getRotation(quaternion).set(Vector3.Y, angle);
        instance.transform.set(position, quaternion);
        instance.calculateTransforms();
        //Gdx.app.log(this.getClass().getName(), "setAngle() = " + angle);
    }
    
    public void setPosition(Vector3 position) {
        instance.transform.setTranslation(position);
    }

    public void setUnitData(WorldUnitData data) {
    	//instance.transform.idt();
        instance.transform.rotate(Vector3.Y, (float) data.angleY);
        instance.transform.setTranslation(data.getPosition());
    }

    public void draw(ModelBatch mBatch, Environment env) {
        mBatch.render(instance, env);
    }
	
    public void addClicker(ModelClicker _clicker){
    	clicker = _clicker;
    }

    public boolean touchDown() {
    	if(clicker != null) clicker.touchDown(this);
		return false;
    }
    
    public boolean touchUp() {
    	if(clicker != null) clicker.touchUp(this);
		return false;
    }
}
