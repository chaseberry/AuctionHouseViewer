package edu.csh.chase.pixel.engine

import edu.csh.chase.pixel.model.Branch
import edu.csh.chase.pixel.model.Config
import edu.csh.chase.pixel.ui.Frame
import java.awt.Color
import java.util.*

class Engine(val config: Config) {

    val branches = ArrayList<Branch>()

    val frame = Frame()

    val timeThread = Thread {
        gameLoop()
    }

    var running: Boolean = true

    var paused: Boolean = false

    var frames: Int = 0

    init {
        branches.add(Branch(
                x = 144.0,
                y = 0.0,
                angle = 0,
                color = Color.green,
                heightGrowth = 2.0,
                maxHeight = 50.0
        ))
        
        timeThread.start()
    }

    private fun update() {
        branches.toList().forEach {
            it.onTick(this)
        }

    }

    private fun gameLoop() {
        val targetUpdateHertz = 30.0

        val TIME_BETWEEN_UPDATES = 1000000000 / targetUpdateHertz

        val MaxUpdates = 5

        var lastUpdateTime = System.nanoTime().toDouble()

        var lastRenderTime: Double

        val TARGET_TIME_BETWEEN_RENDERS = 1000000000 / config.targetFps

        var lastSecondTime = (lastUpdateTime / 1000000000).toInt()

        while (running) {
            var now = System.nanoTime().toDouble()

            var updateCount = 0

            if (!paused) {
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MaxUpdates) {
                    update()
                    lastUpdateTime += TIME_BETWEEN_UPDATES
                    updateCount++
                }
                now = System.nanoTime().toDouble()

                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES
                }

                //val interpolation = Math.min(1.0f, ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES).toFloat())
                render()
                lastRenderTime = now

                //Update the frames we got.
                val thisSecond = (lastUpdateTime / 1000000000).toInt()
                if (thisSecond > lastSecondTime) {
                    println("$frames in $thisSecond ")
                    frames = 0
                    lastSecondTime = thisSecond
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.`yield`()
                    try {
                        Thread.sleep(1)
                    } catch (e: Exception) {
                    }

                    now = System.nanoTime().toDouble()
                }
            }
        }
    }

    private fun render() {

        frame.drawBranches(branches.toList())

        frames++
    }

}

