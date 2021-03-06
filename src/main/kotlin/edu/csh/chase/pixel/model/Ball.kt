package edu.csh.chase.pixel.model

import edu.csh.chase.pixel.engine.Collidable
import edu.csh.chase.pixel.engine.Ray
import javafx.scene.shape.Rectangle

class Ball(x: Double, y: Double, ray: Ray) : Collidable(ray, Rectangle(x, y, 30.0, 30.0)) {

    override fun doCollide(obj: Collidable) {

    }
}