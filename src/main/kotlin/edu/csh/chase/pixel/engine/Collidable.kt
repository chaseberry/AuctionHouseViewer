package edu.csh.chase.pixel.engine

import javafx.scene.shape.Rectangle

abstract class Collidable(var movement: Ray, val bounds: Rectangle) : Tickable {

    var x: Double
        get() = bounds.x
        set(x: Double) {
            bounds.x = x
        }

    var y: Double
        get() = bounds.y
        set(y: Double) {
            bounds.y = y
        }

    fun didCollide(obj: Collidable): Boolean = bounds.intersects(obj.bounds.layoutBounds)

    abstract fun doCollide(obj: Collidable)

    override fun onTick() {
        x += (movement.speed * movement.xComponent)
        y += (movement.speed * movement.yComponent)
    }

}