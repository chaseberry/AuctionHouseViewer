package edu.csh.chase.pixel.model

import edu.csh.chase.pixel.engine.Engine
import edu.csh.chase.pixel.utils.roundToInt
import edu.csh.chase.pixel.utils.yConst
import java.awt.Color
import java.awt.Polygon

class Branch(val x: Double, val y: Double, val angle: Int, val heightGrowth: Double,
             val maxHeight: Double, val color: Color) {

    var width = 10.0
        private set

    var height = 0.0
        private set

    private var didSprout = false

    fun onTick(engine: Engine) {
        if (didSprout) {
            return
        }

        if (height < maxHeight) {
            grow()
            return
        }

        didSprout = true
        sprout(engine)
    }

    private fun sprout(e: Engine) {

        e.branches.add(Branch(
                x = x + width,
                y = y + height,
                angle = angle + 2,
                heightGrowth = 2.0,
                maxHeight = maxHeight,
                color = color
        ))

        e.branches.add(Branch(
                x = x,
                y = x + width,
                angle = angle - 2,
                heightGrowth = 2.0,
                maxHeight = maxHeight,
                color = color
        ))

    }

    fun getPolygon(): Polygon {

        val x2 = (width * Math.sin(Math.toRadians((90 - angle).toDouble()))).roundToInt()
        val x4 = (width * Math.sin(Math.toRadians((90 - angle).toDouble()))).roundToInt()

        val x3 = when {
            90 <= angle || angle >= 270 -> x4 + x2
            else -> x4 - x2
        }

        val y2 = (width * Math.cos(Math.toRadians((90 - angle).toDouble()))).roundToInt()
        val y4 = (height * Math.sin(Math.toRadians((90 - angle).toDouble()))).roundToInt()

        val y3 = when {
            90 <= angle || angle >= 270 -> y4 - y2
            else -> y4 + y2
        }

        val yPoints = intArrayOf(yConst - y.roundToInt(), yConst - y2, yConst - y4, yConst - y3)

        val xInt = x.roundToInt()

        return Polygon(intArrayOf(xInt, x2 + xInt, x4 + xInt, x3 + xInt), yPoints, 4)

    }

    private fun grow() {
        height += heightGrowth
        if (height > maxHeight) {
            height = maxHeight
        }
    }

}