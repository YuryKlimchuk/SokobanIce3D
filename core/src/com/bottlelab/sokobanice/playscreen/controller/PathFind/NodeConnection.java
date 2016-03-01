package com.bottlelab.sokobanice.playscreen.controller.PathFind;

import com.badlogic.gdx.ai.pfa.DefaultConnection;
// старт ноде для граф
public class NodeConnection extends DefaultConnection<Node> {

	Graph graph;

	static final float NON_DIAGONAL_COST = (float)Math.sqrt(2);
	
	public NodeConnection(Graph _graph, Node fromNode, Node toNode) {
		super(fromNode, toNode);
	}

	@Override
	public float getCost() {
		//if (false) return 1;
//		return getToNode().x != graph.startNode.x && getToNode().z != graph.startNode.z ? NON_DIAGONAL_COST : 1;
		return 1;
	}
}
