package com.bottlelab.sokobanice.playscreen.controller.PathFind;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ASG on 31.08.2015.
 */
public class PathFinder {

    TiledManhattanDistance heuristic = new TiledManhattanDistance();
    DefaultGraphPath<Node> outPath = new DefaultGraphPath<Node>();




    public DefaultGraphPath<Node> findPath(int[][] map, int maxX, int maxZ, Vector3 _start, Vector3 _end) {
        Graph graph = new Graph(map, maxX, maxZ);
        IndexedAStarPathFinder<Node> finder = new IndexedAStarPathFinder<Node>(graph);
        outPath.clear();

        Node start = graph.getNode((int) _start.x, (int) _start.z);
        Node end = graph.getNode((int) _end.x, (int) _end.z);

        //Gdx.app.log("START", " X = " + _start.x + "; Z = " + _start.z);
        //Gdx.app.log("END", " X = " + _end.x + "; Z = " + _end.z);

        finder.searchNodePath(start, end, heuristic, outPath);

        return outPath;
    }

    public DefaultGraphPath<Node> getPath() {
        return outPath;
    }



}
