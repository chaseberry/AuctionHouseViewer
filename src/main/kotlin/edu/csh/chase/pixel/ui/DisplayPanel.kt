package edu.csh.chase.pixel.ui

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class DisplayPanel : JPanel() {

    var img: BufferedImage? = null

    init {
        setLocation(0, 0)
        setSize(9 * 32, 9 * 32)
        isVisible = true
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.clearRect(0, 0, 9 * 32, 9 * 32)
        img?.let {
            g.drawImage(img, 0, 0, null)
        }
    }

}