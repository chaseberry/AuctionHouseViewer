package edu.chase.csh.taladitepricecaculator.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import edu.chase.csh.taladitepricecaculator.R
import edu.chase.csh.taladitepricecaculator.adapters.ItemAdapter
import edu.chase.csh.taladitepricecaculator.managers.ItemManager
import edu.chase.csh.taladitepricecaculator.models.Item
import edu.csh.chase.sprint.Sprint
import edu.csh.chase.sprint.parameters.UrlParameters
import org.jetbrains.anko.find

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.main_activity_layout

    lateinit var loadingIcon: ProgressBar
    lateinit var listView: RecyclerView

    val items = ItemManager()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        loadingIcon = find<ProgressBar>(R.id.main_activity_loading_icon)
        listView = find<RecyclerView>(R.id.main_activity_content_view)

    }

    override fun onStart() {
        super.onStart()
        Sprint.get(
                url = "http://www.wowuction.com/eu/zuljin/horde/Tools/RealmDataExportGetFileStatic",
                urlParameters = UrlParameters("type" to "csv", "token" to "84FgfzGxsvsB8VVDAf8Biw2")
        ) { r, response ->
            if (!response.successful) {
                return@get
            }

            val body = response.bodyAsString!!.split("\n").forEach {
                val i = Item.parse(it)
                if (i != null) items.add(i)
            }

            runOnUiThread {
                loadingIcon.visibility = View.GONE
                listView.adapter = ItemAdapter(items, this@MainActivity)
                listView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }

    }

}