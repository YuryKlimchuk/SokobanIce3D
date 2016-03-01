package com.bottlelab.sokobanice.playscreen.world.models.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;

public class HeroAnimationListener implements AnimationListener {

	@Override
	public void onEnd(AnimationDesc animation) {
		Gdx.app.log(this.getClass().toString(), "onEnd()");
	}

	@Override
	public void onLoop(AnimationDesc animation) {
		Gdx.app.log(this.getClass().toString(), "onLoop()");
	}

}
