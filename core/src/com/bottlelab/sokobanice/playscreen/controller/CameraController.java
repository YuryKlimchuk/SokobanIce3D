package com.bottlelab.sokobanice.playscreen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ASG on 02.09.2015.
 */
public class CameraController implements InputProcessor {

    PerspectiveCamera camera;

    public CameraController(PerspectiveCamera _camera) {
        camera = _camera;
    }

    // for pinch-to-zoom
    int numberOfFingers = 0;
    int fingerOnePointer;
    int fingerTwoPointer;
    float lastDistance = 0;
    Vector3 fingerOne = new Vector3();
    Vector3 fingerTwo = new Vector3();


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Gdx.app.log(this.getClass().toString(), "touchDown()");
        // for pinch-to-zoom
        numberOfFingers++;
        if(numberOfFingers == 1)
        {
            fingerOnePointer = pointer;
            fingerOne.set(x, y, 0);
        }
        else if(numberOfFingers == 2)
        {
            fingerTwoPointer = pointer;
            fingerTwo.set(x, y, 0);

            float distance = fingerOne.dst(fingerTwo);
            lastDistance = distance;
        
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        Gdx.app.log(this.getClass().toString(), "touchUp()");
        // for pinch-to-zoom
        if(numberOfFingers == 1)
        {
            Vector3 touchPoint = new Vector3(x, y, 0);
            camera.unproject(touchPoint);
        }
        numberOfFingers--;

// just some error prevention... clamping number of fingers (ouch! :-)
        if(numberOfFingers<0){
            numberOfFingers = 0;
        }


        lastDistance = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Gdx.app.log(this.getClass().toString(), "touchDragged()");
        // for pinch-to-zoom
        if(numberOfFingers == 2) {
            if (pointer == fingerOnePointer) {
                fingerOne.set(x, y, 0);
            }
            if (pointer == fingerTwoPointer) {
                fingerTwo.set(x, y, 0);
            }

            float distance = fingerOne.dst(fingerTwo);
            float factor = distance / lastDistance;


            if (lastDistance > distance) {
                camera.fieldOfView += factor;
            } else if (lastDistance < distance) {
                camera.fieldOfView -= factor;
            }


            // clamps field of view to common angles...
            if (camera.fieldOfView < 10f) camera.fieldOfView = 10f;
            if (camera.fieldOfView > 120f) camera.fieldOfView = 120f;


            lastDistance = distance;


            camera.update();
            //camera.apply(gl);

        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
