package edu.csh.chase.pixel.model

import edu.csh.chase.pixel.engine.Collidable
import edu.csh.chase.pixel.engine.Ray
import javafx.scene.shape.Rectangle

class Edge(x: Double, y: Double, w: Double, h: Double, val isVertical: Boolean) : Collidable(Ray(0.0, 0), Rectangle(x, y, w, h)) {

    override fun doCollide(obj: Collidable) {

    }

}