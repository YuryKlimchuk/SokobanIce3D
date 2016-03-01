package com.bottlelab.sokobanice.android;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bottlelab.sokobanice.GameManager;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		AdsAndroidController adsController = new AdsAndroidController(this);
        
		View gameView = initializeForView(new GameManager(adsController), config);
		
		RelativeLayout layout = new RelativeLayout(this);
        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        
        RelativeLayout.LayoutParams paramsBotton = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsBotton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paramsBotton.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(adsController.banner1, paramsBotton);
        
        RelativeLayout.LayoutParams paramsTop = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        paramsTop.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(adsController.banner2, paramsTop);
        
        
        setContentView(layout);
	}
}
