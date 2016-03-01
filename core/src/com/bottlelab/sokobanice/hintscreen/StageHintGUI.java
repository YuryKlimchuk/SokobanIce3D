package com.bottlelab.sokobanice.hintscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.utils.Constants;

public class StageHintGUI extends Stage {
	
	public World world;
	public Render render;
	GameManager gameManager;
	Skin skin;
	HintControllerManager contManager;
	
	public StageHintGUI(World _world, Render _render, HintControllerManager _contManager) {
		super(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		world = _world;
		render = _render;
		contManager = _contManager;
		
		Gdx.app.log(this.getClass().toString(), "StageGUI()");
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		skin = gameManager.resManager.getSetLevelMenuSkin();
		
		ImageButton exitBtn = new ImageButton(skin, "exitBtn");
		exitBtn.setTransform(true);
		exitBtn.setSize(60f, 60f);
		exitBtn.setOrigin(exitBtn.getWidth()/2, exitBtn.getHeight()/2);
		exitBtn.setPosition(30f, 390f);
		exitBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "exitBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								gameManager.screenManager.setPlayScreen(world.boxCount, world.level);
							}
						})));
			}
		});
		addActor(exitBtn);
		
		ImageButton arrowRightBtn = new ImageButton(skin, "arrowRightBtn");
		arrowRightBtn.setTransform(true);
		arrowRightBtn.setSize(60f, 60f);
		arrowRightBtn.setOrigin(arrowRightBtn.getWidth()/2, arrowRightBtn.getHeight()/2);
		arrowRightBtn.setPosition(520f, 30f);
		arrowRightBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "arrowRightBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								contManager.nextHint();
							}
						})));
			}
		});
		addActor(arrowRightBtn);
		
		ImageButton arrowLeftBtn = new ImageButton(skin, "arrowLeftBtn");
		arrowLeftBtn.setTransform(true);
		arrowLeftBtn.setSize(60f, 60f);
		arrowLeftBtn.setOrigin(arrowLeftBtn.getWidth()/2, arrowLeftBtn.getHeight()/2);
		arrowLeftBtn.setPosition(220f, 30f);
		arrowLeftBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "arrowLeftBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								contManager.backHint();
							}
						})));
			}
		});
		addActor(arrowLeftBtn);		
	}
	
	@Override
	public void draw() {
		super.draw();
		getBatch().begin();
    	gameManager.resManager.getFont32().draw(
    			getBatch(),
    			world.saver.getCountPos() + "/" + world.worldData.hints.size,
    			700, 430);
    	getBatch().end();
	}

}
