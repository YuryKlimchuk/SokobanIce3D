package com.bottlelab.sokobanice.playscreen.controller.PathFind;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.DefaultIndexedGraph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class Graph extends DefaultIndexedGraph<Node> implements IndexedGraph<Node> {
	
	static final int TILE_EMPTY = 0;
	static final int TILE_FLOOR = 1;
	static final int TILE_WALL = 2;
	private  int sizeX;
	private  int sizeZ;

	Node startNode;
	

	
	public Graph(int[][] _map, int _sizeX, int _sizeZ) {
		
		
		
		sizeX = _sizeX;
		sizeZ = _sizeZ;
		
		for(int x = 0; x < sizeX; x++) {
			for(int z = 0; z < sizeZ; z++) {
				nodes.add(new Node(x, z, _map[x][z], 0, new Array<Connection<Node>>(4), sizeZ));
			}
		}
		
		for (int x = 0; x < sizeX; x++) {
			int idx = x * sizeZ;
			for(int z = 0; z < sizeZ; z++) {
				Node n = nodes.get(idx + z);
				if (x > 0) addConnection(n, -1, 0);
				if (z > 0) addConnection(n, 0, -1);
				if (x < sizeX - 1) addConnection(n, 1, 0);
				if (z < sizeZ - 1) addConnection(n, 0, 1);
			}
		}
		
	}


	public void setGraphSize(int _sizeX, int _sizeZ) {
		sizeX = _sizeX;
		sizeZ = _sizeZ;
	}

	
	public Node getNode (int x, int z) {
		return nodes.get(x * sizeZ + z);
	};

	public Node getNode (int index) {
		return nodes.get(index);
	};
	
	private void addConnection (Node n, int xOffset, int yOffset) {
		Node target = getNode(n.x + xOffset, n.z + yOffset);
		if (target.type == Node.TILE_FLOOR) n.getConnections().add(new NodeConnection(this, n, target));
	}
}
