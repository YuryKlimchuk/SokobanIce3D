package com.bottlelab.sokobanice.utils;

public class Constants {
	
	public static final int
		APP_WIDTH = 800,
		APP_HEIGHT = 480;
	
	// меткидля вытаскивания мешей из модели
	public static final String			
		ICE = "ice",
		OCEAN = "ocean",
		ICE_MARK = "ice_mark",
		BOX = "box",
		HERO = "hero",
		GROUND = "ground",
		ICEBERG = "iceberg";
	
	public static final float 
		VELOSITY_MOVE = 0.5f,
		VELOSITY_ROT = 0.3f;

	public static final int
    	POS_X = 1,		// передвижение по Х
    	POS_Y = 2,		// передвижение по Х
    	POS_Z = 3,		// передвижение по Z
    	POS_XYZ = 4,
    	ANGLE_Y = 5;	// вращение вокруг локальной OY
	
	
	public static final int 
	
		DIR_X_POS = 0,
		DIR_X_NEG = 1,
		DIR_Z_POS = 2,
		DIR_Z_NEG = 3,
		DIR_NULL = -1;
	
	public static final int 
		
		SHOW_BANNER1 = 0,
		HIDE_BANNER1 = 1,
		SHOW_BANNER2 = 2,
		HIDE_BANNER2 = 3;
	
	public static final int 
		MIN_BOX_COUNT = 2,
		MAX_BOX_COUNT = 4,
		COUNT_LEVEL_IN_GROUP = 12,
		COUNT_COLUMS = 6,
		COUNT_ROWS = 2;
		
	
	public static final int 
		MAX_COUNT_LEVELS_2BOX = 30,
		MAX_COUNT_LEVELS_3BOX = 30,
		MAX_COUNT_LEVELS_4BOX = 30;
				
	
	public static final String 
		BANNER1_ID = "ca-app-pub-6274393496106535/4218327809",
		BANNER2_ID = "ca-app-pub-6274393496106535/4091963007",
		INTERSTITIAL_AD1_ID = "ca-app-pub-6274393496106535/1783115007";
	
	
    public static String
    	SOUND_ENABLE = "SOUND_ENABLE",
    	MUSIC_ENABLE = "MUSIC_ENABLE",
    	MAX_BOX_COUNT_ENABLE = "MAX_BOX_COUNT_ENABLE",
    	MAX_LEVEL_2BOX_ENABLE = "MAX_LEVEL_2BOX_ENABLE",
    	MAX_LEVEL_3BOX_ENABLE = "MAX_LEVEL_3BOX_ENABLE",
    	MAX_LEVEL_4BOX_ENABLE = "MAX_LEVEL_4BOX_ENABLE";


}
