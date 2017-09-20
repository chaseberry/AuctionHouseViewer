package edu.csh.chase.pixel.ui

import edu.csh.chase.pixel.model.Branch
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.WindowConstants

class Frame : JFrame() {

    val panel = DisplayPanel()

    init {

        setSize(insets.left + (48 * 9) + insets.right, insets.top + (48 * 9) + insets.bottom)

        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE

        add(panel)

        isVisible = true
    }

    fun drawBranches(branches: List<Branch>) {

        panel.img = BufferedImage(panel.width, panel.height, BufferedImage.TYPE_INT_ARGB)

        val g = panel.img!!.createGraphics()

        branches.forEach {
            g.color = it.color
            val p = it.getPolygon()
            println(p.xpoints.zip(p.ypoints))
            g.fillPolygon(p)
        }

        panel.repaint()
    }
}