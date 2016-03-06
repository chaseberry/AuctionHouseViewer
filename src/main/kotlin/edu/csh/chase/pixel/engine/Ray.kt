package edu.csh.chase.pixel.engine

data class Ray(val speed: Double, val direction: Int) {

    val xComponent: Double
        get() = Math.cos(Math.toRadians(direction.toDouble()))

    val yComponent: Double
        get() = Math.sin(Math.toRadians(direction.toDouble()))
}