package edu.chase.csh.taladitepricecaculator.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import edu.chase.csh.taladitepricecaculator.R
import org.jetbrains.anko.find

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.base_layout)

        val view = layoutInflater.inflate(layoutId, null)
        find<FrameLayout>(R.id.base_layout_content_view).addView(view)
    }

}