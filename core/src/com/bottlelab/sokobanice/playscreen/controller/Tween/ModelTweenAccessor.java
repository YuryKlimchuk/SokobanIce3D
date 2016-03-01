package com.bottlelab.sokobanice.playscreen.controller.Tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.utils.Constants;

/**
 * Created by limit on 30.08.2015.
 */
public class ModelTweenAccessor implements TweenAccessor<BaseModel> {

    @Override
    public int getValues(BaseModel target, int tweenType, float[] returnValues) {
        //Gdx.app.log(this.getClass().getName(), "getValues()");
        switch (tweenType) {

            case Constants.POS_X:
                returnValues[0] = target.getX();
                return 1;

            case Constants.POS_Z:
                returnValues[0] = target.getZ();
                return 1;

            case Constants.POS_XYZ:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getZ();
                return 3;

            case Constants.ANGLE_Y:
                returnValues[0] = target.getAngleY();
                return 1;

            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(BaseModel target, int tweenType, float[] newValues) {
        //Gdx.app.log(this.getClass().getName(), "setValues()");
        switch (tweenType) {

            case Constants.POS_X:
                target.setX(newValues[0]);
                break;

            case Constants.POS_Z:
                target.setZ(newValues[0]);
                break;

            case Constants.POS_XYZ:
                target.setX(newValues[0]);
                target.setY(newValues[1]);
                target.setZ(newValues[2]);
                break;

            case Constants.ANGLE_Y:
                target.setAngleY(newValues[0]);
                break;

            default:
                assert false;
                break;
        }

    }
}
