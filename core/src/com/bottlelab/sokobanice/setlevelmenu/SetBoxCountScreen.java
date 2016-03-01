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

public class SetBoxCountScreen implements Screen {
	
	Stage stage;
	Skin skin;
	GameManager gameManager;
	Group boxesGroup;
	ImageButton arrowRightBtn, arrowLeftBtn;
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		
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
								boxesGroup.addAction(Actions.sequence(
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
								boxesGroup.addAction(Actions.sequence(
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
		
		generateBoxBtns();
		
	}
	
	
	
	private void generateBoxBtns() {
		boxesGroup = new Group();
        for (int i = Constants.MIN_BOX_COUNT; i <= Constants.MAX_BOX_COUNT; i++) {
        	final int boxCount = i;
        	TextButton btn = new TextButton(String.valueOf(i) + "BOX", skin, "boxBtn");
            btn.setTransform(true);
            btn.setSize(160f, 60f);
            btn.setOrigin(btn.getWidth() / 2, btn.getHeight() / 2);
            btn.setPosition(320f + (i - Constants.MIN_BOX_COUNT) * Constants.APP_WIDTH, 230f);
            boxesGroup.addActor(btn);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.log(this.getClass().toString(), "btn.clicked()");
                    final Actor actor = event.getListenerActor();
                    actor.addAction(Actions.sequence(
                            Actions.scaleTo(1.1f, 1.1f, 0.1f),
                            Actions.scaleTo(1f, 1f, 0.1f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    gameManager.audioManager.playSound2();
                                    Gdx.app.log(this.getClass().toString(), "btn.clicked(), BOX_COUNT = " + boxCount);
                                    gameManager.screenManager.setSetLevelMenu(boxCount);
                                }
                            })
                    ));
                }
            });
            
            if(i > gameManager.prefManager.MAX_BOX_COUNT_ENABLE){
            	btn.setDisabled(true);
                btn.setTouchable(Touchable.disabled);
            }
            
		}
        stage.addActor(boxesGroup);
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
		int x = (int) boxesGroup.getX();
		Gdx.app.log(null, "x = " + x);
		
		if(x >= 0) {
			arrowLeftBtn.setTouchable(Touchable.disabled);
		} else {
			arrowLeftBtn.setTouchable(Touchable.enabled);
		}
		
		if(x <= -Constants.APP_WIDTH*2) {
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
	public void hide() {}

	@Override
	public void dispose() {}

}
