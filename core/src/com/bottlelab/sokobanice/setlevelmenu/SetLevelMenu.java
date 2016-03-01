package com.bottlelab.sokobanice.setlevelmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.utils.Constants;

public class SetLevelMenu implements Screen {

	Stage stage;
	Skin skin;
	GameManager gameManager;
	Group levelsGroup;
	int box, level, maxLevelCount;
	int groupNum;
	ImageButton arrowRightBtn, arrowLeftBtn;
	
	public SetLevelMenu(int _box) {
		box = _box;
		Gdx.app.log(this.getClass().toString(), "SetLevelMenu()" + _box);
		
		switch (box) {
		case 2:
			maxLevelCount = Constants.MAX_COUNT_LEVELS_2BOX;
			break;
		case 3:
			maxLevelCount = Constants.MAX_COUNT_LEVELS_3BOX;
			break;
		case 4:
			maxLevelCount = Constants.MAX_COUNT_LEVELS_4BOX;
			break;
		}
	}
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		
		//gameManager.adsController.showBanner2();
		
		skin = gameManager.resManager.getSetLevelMenuSkin();
		
		
		
		arrowRightBtn = new ImageButton(skin, "arrowRightBtn");
		arrowRightBtn.setTransform(true);
		arrowRightBtn.setSize(60f, 60f);
		arrowRightBtn.setOrigin(arrowRightBtn.getWidth()/2, arrowRightBtn.getHeight()/2);
		arrowRightBtn.setPosition(520f, 30f);
		arrowRightBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "arrowRightBtn.clicked()");
				Gdx.input.setInputProcessor(null);
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								levelsGroup.addAction(Actions.sequence(
										Actions.moveBy(-Constants.APP_WIDTH, 0f, 0.5f),
										Actions.run(new Runnable() {
											@Override
											public void run() {
												Gdx.input.setInputProcessor(stage);
											}
										}))
							);
							}
						})));
			}
		});
		stage.addActor(arrowRightBtn);
		
		arrowLeftBtn = new ImageButton(skin, "arrowLeftBtn");
		arrowLeftBtn.setTransform(true);
		arrowLeftBtn.setSize(60f, 60f);
		arrowLeftBtn.setOrigin(arrowLeftBtn.getWidth()/2, arrowLeftBtn.getHeight()/2);
		arrowLeftBtn.setPosition(220f, 30f);
		arrowLeftBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "arrowLeftBtn.clicked()");
				Gdx.input.setInputProcessor(null);
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								levelsGroup.addAction(Actions.sequence(
										Actions.moveBy(Constants.APP_WIDTH, 0f, 0.5f),
										Actions.run(new Runnable() {
											@Override
											public void run() {
												Gdx.input.setInputProcessor(stage);
											}
										}))
							);
							}
						})));
			}
		});
		stage.addActor(arrowLeftBtn);
		
		ImageButton homeBtn = new ImageButton(skin, "homeBtn");
		homeBtn.setTransform(true);
		homeBtn.setSize(80f, 80f);
		homeBtn.setOrigin(homeBtn.getWidth()/2, homeBtn.getHeight()/2);
		homeBtn.setPosition(360f, 20f);
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
		stage.addActor(homeBtn);
		
		generateLevelBtns();
	}
	
	private void generateLevelBtns() {
		boolean flag = false;
        level = 1;
        
        levelsGroup = new Group();
        
        while (!flag) {
        	
        	groupNum = ((int) level/Constants.COUNT_LEVEL_IN_GROUP);
        	Gdx.app.log(this.getClass().toString(), "groupNum = " + groupNum);
        	boolean flag2 = false;
        	
        	for (int i = 1; i <= Constants.COUNT_ROWS; i++) {
        		
        		if(flag2) break;
        		
				for (int j = 1; j <= Constants.COUNT_COLUMS; j++) {
					TextButton levelBtn = new TextButton(String.valueOf(level), skin, "levelBtn");
					levelBtn.setTransform(true);
					levelBtn.setSize(60f, 60f);
					levelBtn.setOrigin(levelBtn.getWidth() / 2, levelBtn.getHeight() / 2);
					levelBtn.setPosition(70 + 120*(j-1) + Constants.APP_WIDTH * (groupNum), 280 - 120*(i-1));
					levelsGroup.addActor(levelBtn);
			        levelBtn.addListener(new ClickListener() {
			            @Override
			            public void clicked(InputEvent event, float x, float y) {
			                Gdx.app.log(this.getClass().toString(), "levelBtn.clicked()");
			                final Actor actor = event.getListenerActor();
			                actor.addAction(Actions.sequence(
			                        Actions.scaleTo(1.1f, 1.1f, 0.1f),
			                        Actions.scaleTo(1f, 1f, 0.1f),
			                        Actions.run(new Runnable() {
			                            @Override
			                            public void run() {
			                                gameManager.audioManager.playSound2();                             
			                                String levelStr = ((TextButton) actor).getLabel().getText().toString();    
			                                gameManager.screenManager.setPlayScreen(box, Integer.parseInt(levelStr));
			                            }
			                        })
			                ));
			            }
			        });
			        
			        int levelEnable = 0;
			        
			        switch (box) {
					case 2:
						levelEnable = gameManager.prefManager.MAX_LEVEL_2BOX_ENABLE;
						break;

					case 3:
						levelEnable = gameManager.prefManager.MAX_LEVEL_3BOX_ENABLE;
						break;
					case 4:
						levelEnable = gameManager.prefManager.MAX_LEVEL_4BOX_ENABLE;
						break;
					}
			        
			        if(level > levelEnable){
			        	levelBtn.setDisabled(true);
			        	levelBtn.setTouchable(Touchable.disabled);
			        }
			        
			        

			        if(level == maxLevelCount) {
			        	flag2 = true;
			        	flag = true;
			        	break;
			        }
			        
			        level++;
				}
			}
		}
        
        stage.addActor(levelsGroup);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(178/255f, 235/255f, 242/255f, 1f);
        
        cheakGroupPosition();
        
        stage.draw();
        stage.act(delta);
        
        
        
        stage.getBatch().begin();
    	gameManager.resManager.getFont26().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 450);
    	gameManager.resManager.getFont32().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 400);
    	stage.getBatch().end();

	}
	
	public void cheakGroupPosition() {
		int x = (int) levelsGroup.getX();
		//Gdx.app.log(null, "x = " + x);
		
		if(x >= 0) {
			arrowLeftBtn.setTouchable(Touchable.disabled);
		} else {
			arrowLeftBtn.setTouchable(Touchable.enabled);
		}
		
		if(x <= -Constants.APP_WIDTH*(groupNum)) {
			arrowRightBtn.setTouchable(Touchable.disabled);
		} else {
			arrowRightBtn.setTouchable(Touchable.enabled);
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {
		Gdx.app.log(this.getClass().toString(), "hide()");
		//gameManager.adsController.hideBanner2();
	}

	@Override
	public void dispose() {}

}
