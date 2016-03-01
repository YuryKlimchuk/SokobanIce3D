package com.bottlelab.sokobanice.playscreen.controller.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.playscreen.controller.ControllerManager;
import com.bottlelab.sokobanice.playscreen.controller.ControllerManagerState;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.utils.Constants;

public class StageGUI extends Stage {
	
	GameManager gameManager;
	Skin skin, skin1;
	
	Group option, win;
	
	public ImageButton
		stepBackBtn,
		showHideBtn;
	
	boolean 
		isOptionHide = true,
		isWinHide = true;
	
	World world;
	Render render;
	ControllerManager contrManager;
	
	int currentLevelEnable = 0;
	int currentMaxLevelCount = 0;
	
	public StageGUI(World _world, Render _render, ControllerManager _contrManager) {
		
		super(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		
		Gdx.app.log(this.getClass().toString(), "StageGUI()");
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		
		world = _world;
		render = _render;
		contrManager = _contrManager;
		
		switch (world.boxCount) {
		case 2:
			currentLevelEnable = gameManager.prefManager.MAX_LEVEL_2BOX_ENABLE;
			currentMaxLevelCount = Constants.MAX_COUNT_LEVELS_2BOX;
			break;
		case 3:
			currentLevelEnable = gameManager.prefManager.MAX_LEVEL_3BOX_ENABLE;
			currentMaxLevelCount = Constants.MAX_COUNT_LEVELS_3BOX;
			break;
		case 4:
			currentLevelEnable = gameManager.prefManager.MAX_LEVEL_4BOX_ENABLE;
			currentMaxLevelCount = Constants.MAX_COUNT_LEVELS_4BOX;
			break;
		}
		
		
		skin = gameManager.resManager.getPlayMenuSkin();
		skin1 = gameManager.resManager.getWinMenuSkin();
		
		
		
		
		option = new Group();
		win = new Group();
		
		
		Image backgroundOptionGroup = new Image(skin.getRegion("background"));
		backgroundOptionGroup.setSize(200, 360);
		backgroundOptionGroup.setPosition(600, 60f);
		option.addActor(backgroundOptionGroup);
		
		ImageButton regameBtn = new ImageButton(skin, "regameBtn");
		regameBtn.setTransform(true);
		regameBtn.setSize(60f, 60f);
		regameBtn.setOrigin(regameBtn.getWidth()/2, regameBtn.getHeight()/2);
		regameBtn.setPosition(620f, 320f);
		regameBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "regameBtn.clicked()");
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
		option.addActor(regameBtn);
		
		ImageButton helpBtn = new ImageButton(skin, "helpBtn");
		helpBtn.setTransform(true);
		helpBtn.setSize(60f, 60f);
		helpBtn.setOrigin(helpBtn.getWidth()/2, helpBtn.getHeight()/2);
		helpBtn.setPosition(720f, 320f);
		helpBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "helpBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								gameManager.screenManager.setHintScren(world.boxCount, world.level);
							}
						})));
			}
		});
		option.addActor(helpBtn);
		
		ImageButton homeBtn = new ImageButton(skin, "homeBtn");
		homeBtn.setTransform(true);
		homeBtn.setSize(60f, 60f);
		homeBtn.setOrigin(homeBtn.getWidth()/2, homeBtn.getHeight()/2);
		homeBtn.setPosition(670f, 200f);
		homeBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "homeBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								gameManager.screenManager.setMainMenuScreen();
							}
						})));
			}
		});
		option.addActor(homeBtn);
		
		ImageButton d23dBtn = new ImageButton(skin, "2d3dBtn");
		d23dBtn.setTransform(true);
		d23dBtn.setSize(60f, 60f);
		d23dBtn.setOrigin(d23dBtn.getWidth()/2, d23dBtn.getHeight()/2);
		d23dBtn.setPosition(620f, 80f);
		d23dBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "d23dBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								render.customCamera.setCameraOrto();
							}
						})));
			}
		});
		option.addActor(d23dBtn);
		
		ImageButton rotCamBtn = new ImageButton(skin, "rotCamBtn");
		rotCamBtn.setTransform(true);
		rotCamBtn.setSize(60f, 60f);
		rotCamBtn.setOrigin(rotCamBtn.getWidth()/2, rotCamBtn.getHeight()/2);
		rotCamBtn.setPosition(720f, 80f);
		rotCamBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "rotCamBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								render.customCamera.rotateCamera();
							}
						})));
			}
		});
		option.addActor(rotCamBtn);
		
		showHideBtn = new ImageButton(skin, "showHideBtn");
		showHideBtn.setTransform(true);
		showHideBtn.setSize(60f, 60f);
		showHideBtn.setOrigin(showHideBtn.getWidth()/2, showHideBtn.getHeight()/2);
		showHideBtn.setPosition(570f, 200f);
		showHideBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "showHideBtn.clicked()");
				final Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								actor.addAction(Actions.rotateBy(180f, 0.5f));
								
								if(isOptionHide) {
									option.addAction(Actions.moveBy(-180f, 0f, 0.5f));
									isOptionHide = false;
									contrManager.fsm.changeState(ControllerManagerState.GUI_ACTIVE);
								} else {
									option.addAction(Actions.moveBy(180f, 0f, 0.5f));
									isOptionHide = true;
									contrManager.fsm.changeState(ControllerManagerState.ALL_ENABLE);
								}
							}
						})));
			}
		});
		option.addActor(showHideBtn);
		
		option.setX(option.getX() + 180f);
		addActor(option);
		
		
		stepBackBtn = new ImageButton(skin, "stepBackBtn");
		stepBackBtn.setTransform(true);
		stepBackBtn.setSize(60f, 60f);
		stepBackBtn.setOrigin(stepBackBtn.getWidth()/2, stepBackBtn.getHeight()/2);
		stepBackBtn.setPosition(40f, 380f);
		stepBackBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "stepBackBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								world.saver.setLastStage();
							}
						})));
			}
		});
		addActor(stepBackBtn);
		
		
		createWinGroup();
	}
	
	public void showWingroup() {
		Gdx.app.log(this.getClass().toString(), "showWingroup()");
		win.addAction(Actions.moveBy(0, -Constants.APP_HEIGHT, 0.5f));
		
		contrManager.fsm.changeState(ControllerManagerState.WIN_CONTROL);
		
		if(world.level == currentLevelEnable && world.level < currentMaxLevelCount) {
			Gdx.app.log(this.getClass().toString(), "showWingroup() 1");
			switch (world.boxCount) {
			case 2:
				gameManager.prefManager.MAX_LEVEL_2BOX_ENABLE++;
				break;
				
			case 3	:
				gameManager.prefManager.MAX_LEVEL_3BOX_ENABLE++;
				break;
				
			case 4:
				gameManager.prefManager.MAX_LEVEL_4BOX_ENABLE++;
				break;
			}
			
		} else if(world.level == currentLevelEnable && world.level == currentMaxLevelCount) {
			Gdx.app.log(this.getClass().toString(), "showWingroup 2()");
			switch (world.boxCount) {
			case 2:
				gameManager.prefManager.MAX_BOX_COUNT_ENABLE = 3;
				break;
				
			case 3	:
				gameManager.prefManager.MAX_BOX_COUNT_ENABLE = 4;
				break;
				
			case 4:
				Gdx.app.log(this.getClass().toString(), "all levels complited");
				break;
			}
		}
		
		
	}
	
	public void createWinGroup() {
		
		
		ImageButton regameBtn = new ImageButton(skin1, "regameBtn");
		regameBtn.setTransform(true);
		regameBtn.setSize(80f, 80f);
		regameBtn.setOrigin(regameBtn.getWidth()/2, regameBtn.getHeight()/2);
		regameBtn.setPosition(200f, 220f);
		regameBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "regameBtn.clicked()");
				gameManager.audioManager.playSound2();
				gameManager.screenManager.setPlayScreen(world.boxCount, world.level);
			}
		});
		win.addActor(regameBtn);
		
		ImageButton playBtn = new ImageButton(skin1, "playBtn");
		playBtn.setTransform(true);
		playBtn.setSize(120f, 120f);
		playBtn.setOrigin(playBtn.getWidth()/2, playBtn.getHeight()/2);
		playBtn.setPosition(340f, 200f);
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "playBtn.clicked()");
				gameManager.audioManager.playSound2();
				
				
				
				//gameManager.adsController.showInterstitialAd();
				
				
				
				if(world.level == currentMaxLevelCount) {
					Gdx.app.log(this.getClass().toString(), "showWingroup() 3");
					switch (world.boxCount) {
					case 2:
						gameManager.screenManager.setPlayScreen(3, 1);
						break;
						
					case 3:
						gameManager.screenManager.setPlayScreen(4, 1);
						break;
						
					case 4:
						Gdx.app.log(this.getClass().toString(), "all levels complited");
						break;

					}
				} else {
						Gdx.app.log(this.getClass().toString(), "yyyyy");
						gameManager.screenManager.setPlayScreen(world.boxCount, (world.level + 1));
				}
				
				
			}
		});
		win.addActor(playBtn);
		
		ImageButton homeBtn = new ImageButton(skin1, "homeBtn");
		homeBtn.setTransform(true);
		homeBtn.setSize(80f, 80f);
		homeBtn.setOrigin(homeBtn.getWidth()/2, homeBtn.getHeight()/2);
		homeBtn.setPosition(520f, 220f);
		homeBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "homeBtn.clicked()");
				gameManager.audioManager.playSound2();
				gameManager.screenManager.setMainMenuScreen();
			}
		});
		win.addActor(homeBtn);
		win.setY(Constants.APP_HEIGHT);
		
		addActor(win);
	}
	
	public void update(float delta){
		if(world.saver.getCountPos() == 0) {
			stepBackBtn.setDisabled(true);
			stepBackBtn.setTouchable(Touchable.disabled);
		} else {
			stepBackBtn.setDisabled(false);
			stepBackBtn.setTouchable(Touchable.enabled);
		}
			
	}
	
	
	

}
