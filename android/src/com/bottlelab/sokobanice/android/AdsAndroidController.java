package com.bottlelab.sokobanice.android;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.bottlelab.sokobanice.AdsController;
import com.bottlelab.sokobanice.utils.Constants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AdsAndroidController implements AdsController{

	
	ConnectivityManager cm;
	
	boolean isBanner1Show = false;
	boolean isBanner2Show = false;
	
	
	protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case Constants.SHOW_BANNER1: {
                	
                	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                	
                	if(networkInfo != null && networkInfo.isConnected()) {
                		banner1.setVisibility(View.VISIBLE);
                		isBanner1Show = true;
                	}
                	
                    break;
                }
                case Constants.HIDE_BANNER1: {
                	if(isBanner1Show = true) {
                		banner1.setVisibility(View.GONE);
                    	isBanner1Show = false;	
                	}
                    break;
                }
                    
                case Constants.SHOW_BANNER2: {
                	
                	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                	
                	if(networkInfo != null && networkInfo.isConnected()) {
                		banner2.setVisibility(View.VISIBLE);
                		isBanner2Show = true;
                	}
                	
                    break;
                }
                case Constants.HIDE_BANNER2: {
                	if(isBanner2Show = true) {
                		banner2.setVisibility(View.GONE);
                    	isBanner2Show = false;	
                	}
                    break;
                } 
            }
        }
    };
	
	AdView banner1, banner2;
	InterstitialAd interstitialAd;
	
	AndroidApplication app;
	
	public AdsAndroidController(AndroidApplication _app) {
		app =_app;
		
		cm = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		
		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        AdRequest ad = builder.build();
		
		banner1 = new AdView(app); 
		banner1.setBackgroundColor(0xff000000); 
		banner1.setAdUnitId(Constants.BANNER1_ID);
		banner1.setAdSize(AdSize.BANNER);
        banner1.loadAd(ad);
        banner1.setVisibility(View.GONE);
         
        banner2 = new AdView(app); 
		banner2.setBackgroundColor(0xff000000); 
		banner2.setAdUnitId(Constants.BANNER2_ID);
		banner2.setAdSize(AdSize.BANNER);
        banner2.loadAd(ad);
        banner2.setVisibility(View.GONE);
        
        
        interstitialAd = new InterstitialAd(app);
        interstitialAd.setAdUnitId(Constants.INTERSTITIAL_AD1_ID);
        interstitialAd.setAdListener(new AdListener() {
		
        	@Override
        	public void onAdClosed() {
        		
        		super.onAdClosed();
        	}
        	
        	@Override
        	public void onAdOpened() {
        		// TODO Auto-generated method stub
        		super.onAdOpened();
        	}
        
        });
        loadInterstitialAd();
	}
	
	private void loadInterstitialAd() {
		AdRequest adRequest = new AdRequest.Builder()
			.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			.build();
	
		interstitialAd.loadAd(adRequest);
	}
	
	
	@Override
	public void showBanner1() {
		handler.sendEmptyMessage(Constants.SHOW_BANNER1);
		
	}

	@Override
	public void hideBanner1() {
		handler.sendEmptyMessage(Constants.HIDE_BANNER1);
	}

	@Override
	public void showInterstitialAd() {
	
		app.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if(interstitialAd.isLoaded()) {
					interstitialAd.show();
				}
			}
		});
		
		
		
		
	}



	@Override
	public void showBanner2() {
		handler.sendEmptyMessage(Constants.SHOW_BANNER2);
		
	}

	@Override
	public void hideBanner2() {
		handler.sendEmptyMessage(Constants.HIDE_BANNER2);
	}



	

}	


