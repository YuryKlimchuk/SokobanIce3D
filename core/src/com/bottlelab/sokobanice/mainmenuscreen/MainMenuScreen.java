package com.bottlelab.sokobanice.mainmenuscreen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.playscreen.controller.Tween.ModelTweenAccessor;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.utils.Constants;
import com.sun.prism.PixelFormat;

public class MainMenuScreen implements Screen {
	
	
	Stage stage;
	Skin skin;
	GameManager gameManager;
	Group settingsBtnGroup;
	
	
	PerspectiveCamera camera;
	ModelBatch mBatch;
	Environment env;
	BaseModel scene;
	TweenManager tweenManager;
	
	private ImageButtonStyle
		musicStyle,
		musicOffStyle,
		soundStyle,
		soundOffStyle;
		
	

	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		skin = gameManager.resManager.getMainMenuSkin();
		
		gameManager.audioManager.playBensoundCute();
		
	    
		//gameManager.adsController.showBanner1();
		
		
		Image logo = new Image(skin, "img_sokobanice3d");
		logo.setSize(800f, 80);
		logo.setPosition(0f, 400f);
		stage.addActor(logo);
		
		ImageButton btnPlay = new ImageButton(skin, "playBtn");
		btnPlay.setTransform(true);
		btnPlay.setSize(200f, 80f);
		btnPlay.setOrigin(btnPlay.getWidth()/2, btnPlay.getHeight()/2);
		btnPlay.setPosition(200f, 160f);
		btnPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "btnPlay.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
								gameManager.screenManager.setSetBoxCountScreen();
							}
						})));
			}
		});
		stage.addActor(btnPlay);
		
		ImageButton htpBtn = new ImageButton(skin, "htpBtn");
		htpBtn.setTransform(true);
		htpBtn.setSize(60f, 60f);
		htpBtn.setOrigin(htpBtn.getWidth()/2, htpBtn.getHeight()/2);
		htpBtn.setPosition(30f, 220f);
		htpBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "htpBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
							}
						})));
			}
		});
		stage.addActor(htpBtn);
		
		ImageButton scoreBtn = new ImageButton(skin, "scoreBtn");
		scoreBtn.setTransform(true);
		scoreBtn.setSize(60f, 60f);
		scoreBtn.setOrigin(scoreBtn.getWidth()/2, scoreBtn.getHeight()/2);
		scoreBtn.setPosition(30f, 300f);
		scoreBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "scoreBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
							}
						})));
			}
		});
		stage.addActor(scoreBtn);
		
		ImageButton infoBtn = new ImageButton(skin, "infoBtn");
		infoBtn.setTransform(true);
		infoBtn.setSize(50f, 50f);
		infoBtn.setOrigin(infoBtn.getWidth()/2, infoBtn.getHeight()/2);
		infoBtn.setPosition(710f, 30f);
		infoBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "infoBtn.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.audioManager.playSound2();
							}
						})));
			}
		});
		stage.addActor(infoBtn);
		
		
		settingsBtnGroup = new Group();
		
		ImageButton settingsBtn = new ImageButton(skin, "settingsBtn");
		settingsBtn.setTransform(true);
		settingsBtn.setSize(70f, 70f);
		settingsBtn.setOrigin(settingsBtn.getWidth()/2, settingsBtn.getHeight()/2);
		settingsBtn.setPosition(20f, 20f);
		settingsBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "settingsBtn.clicked()");
				gameManager.audioManager.playSound2();
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								settingsBtnGroup.addAction(Actions.sequence(
										Actions.rotateBy(180f, 0.5f),
										Actions.run(new Runnable() {
											@Override
											public void run() {
												
											}
										})));
							}
						})));
			}
		});
		settingsBtnGroup.addActor(settingsBtn);
		
		generateSettingsStyles();
		
		ImageButtonStyle style1 = gameManager.prefManager.SOUND_ENABLE ? soundStyle : soundOffStyle;
		ImageButton soundBtn = new ImageButton(style1);
		soundBtn.setTransform(true);
		soundBtn.setSize(50f, 50f);
		soundBtn.setOrigin(soundBtn.getWidth()/2, soundBtn.getHeight()/2);
		soundBtn.setPosition(30f, 130f);
		soundBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "soundBtn.clicked()");
				final Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								if(gameManager.prefManager.SOUND_ENABLE){
									((ImageButton) actor).setStyle(soundOffStyle);
									gameManager.prefManager.SOUND_ENABLE = false;
								} else {
									((ImageButton) actor).setStyle(soundStyle);
									gameManager.prefManager.SOUND_ENABLE = true;
								}
								gameManager.audioManager.playSound2();
							}
						})));
			}
		});
		settingsBtnGroup.addActor(soundBtn);
		
		ImageButtonStyle style2 = gameManager.prefManager.MUSIC_ENABLE ? musicStyle : musicOffStyle;
		ImageButton musicBtn = new ImageButton(style2);
		musicBtn.setTransform(true);
		musicBtn.setSize(50f, 50f);
		musicBtn.setOrigin(musicBtn.getWidth()/2, musicBtn.getHeight()/2);
		musicBtn.setPosition(130f, 30f);
		musicBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "musicBtn.clicked()");
				final Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						Actions.scaleTo(1.05f, 1.05f, 0.1f),
						Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								if(gameManager.prefManager.MUSIC_ENABLE){
									((ImageButton) actor).setStyle(musicOffStyle);
									gameManager.prefManager.MUSIC_ENABLE = false;
									gameManager.audioManager.stopBensoundCute();
								} else {
									((ImageButton) actor).setStyle(musicStyle);
									gameManager.prefManager.MUSIC_ENABLE = true;
									gameManager.audioManager.playBensoundCute();
								}
							}
						})));
			}
		});
		settingsBtnGroup.addActor(musicBtn);
		
		settingsBtnGroup.setOrigin(55f, 55f);
		settingsBtnGroup.setRotation(180f);
		stage.addActor(settingsBtnGroup);
		
		Gdx.input.setInputProcessor(stage);
		
		init3DScene();
	}
	
	private void generateSettingsStyles() {
		soundStyle = skin.get("soundBtn", ImageButtonStyle.class);
		soundOffStyle = skin.get("soundOffBtn", ImageButtonStyle.class);
		musicStyle = skin.get("musicBtn", ImageButtonStyle.class);
		musicOffStyle = skin.get("musicOffBtn", ImageButtonStyle.class);
	}
	
	private void init3DScene() {
		
		camera = new PerspectiveCamera(67f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());     
		camera.position.set(new Vector3(150f, 20f, -150f));
		camera.up.set(Vector3.Y);
        camera.lookAt(new Vector3(80f, -30f, 50f));
        camera.far = 300000f;
        camera.near = 0.1f;
        camera.update();  
        
        env = new Environment();
		env.set(new ColorAttribute(ColorAttribute.AmbientLight, 120 / 255f, 120 / 255f, 120 / 255f, 0.01f));
		env.add(new DirectionalLight().set(120 / 255f, 120 / 255f, 120 / 255f, 0.2f, -0.8f, 0.2f));
		env.add(new PointLight().set(255 / 255f, 253 / 255f, 231 / 255f, new Vector3(10, 10, 9), 10f));
		env.add(new PointLight().set(255 / 255f, 253 / 255f, 231 / 255f, new Vector3(-10, 10, -9), 10f));
		
		mBatch = new ModelBatch();
		
		ModelInstance instance = new ModelInstance(gameManager.resManager.getScene1Model());
		scene = new BaseModel(instance);
		
		Tween.registerAccessor(BaseModel.class, new ModelTweenAccessor());
        tweenManager = new TweenManager();
		
		Tween.to(scene, Constants.ANGLE_Y, 10f)
			.target(360)
            .ease(TweenEquations.easeNone)
            .repeat(-1, 0f)
            .start(tweenManager);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(178/255f, 235/255f, 242/255f, 1f);
        
        stage.draw();
        stage.act(delta);
        
        tweenManager.update(delta);
        mBatch.begin(camera);
        	scene.draw(mBatch, env);
        mBatch.end();
 
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

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(this.getClass().toString(), "resize()");
	}

	@Override
	public void pause() {
		Gdx.app.log(this.getClass().toString(), "pause()");
	}

	@Override
	public void resume() {
		Gdx.app.log(this.getClass().toString(), "resume()");
	}

	@Override
	public void hide() {
		Gdx.app.log(this.getClass().toString(), "hide()");
		//gameManager.adsController.hideBanner1();
	}

	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().toString(), "dispose()");
		stage.dispose();
		skin.dispose();
	}

}
