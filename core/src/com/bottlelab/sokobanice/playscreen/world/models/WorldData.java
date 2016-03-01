package com.bottlelab.sokobanice.playscreen.world.models;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WorldData {
	
	public Array<WorldUnitData> 
		hints,
		ice,
		iceMark,
		box;

	public WorldUnitData
		hero;

	public int maxX, maxZ;

	// возвращает массив с координатами клеток с отметками( целевые клетки)
	public Array<Vector2> getGoalPositions(){
		Array<Vector2> positions = new Array<Vector2>();
		for (int i = 0; i < iceMark.size; i++) 
			positions.add(new Vector2(iceMark.get(i).x, iceMark.get(i).z));
	return positions;
	}
}
