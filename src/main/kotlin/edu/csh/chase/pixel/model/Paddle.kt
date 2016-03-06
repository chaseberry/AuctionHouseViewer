package edu.csh.chase.pixel.model

import edu.csh.chase.pixel.engine.Collidable
import edu.csh.chase.pixel.engine.Ray
import javafx.scene.shape.Rectangle

class Paddle(x: Double, y: Double, ray: Ray) : Collidable(ray, Rectangle(x, y, 100.0, 25.0)) {

    override fun doCollide(obj: Collidable) {
        obj.movement = obj.movement.copy(direction = Math.abs(obj.movement.direction - 180))
    }


}