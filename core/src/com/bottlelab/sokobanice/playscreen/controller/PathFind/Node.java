package com.bottlelab.sokobanice.playscreen.controller.PathFind;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedNode;
import com.badlogic.gdx.utils.Array;

public class Node implements IndexedNode<Node> {
	
	
	public static final int TILE_EMPTY = 0;
	public static final int TILE_FLOOR = 1;
	public static final int TILE_WALL = 2;
	
	public int
		x,z,type,angle;

	public int maxZ;
	
	public Array<Connection<Node>> 
		connections;
	
	public Node(int _x, int	_z, int _type, int _angle, Array<Connection<Node>> _connections, int _maxZ ) {
		x = _x;
		z = _z;
		type = _type;
		angle = _angle;
		connections = _connections;
		maxZ = _maxZ;
	}


	@Override
	public int getIndex () {
		return x * maxZ + z;
	}


	@Override
	public Array<Connection<Node>> getConnections() {
		return connections;
	}

}
