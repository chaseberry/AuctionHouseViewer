package edu.chase.csh.taladitepricecaculator.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.chase.csh.taladitepricecaculator.R
import edu.chase.csh.taladitepricecaculator.managers.ItemManager
import org.jetbrains.anko.find

class ItemAdapter(val items: ItemManager, val parent: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(this.parent).inflate(R.layout.wow_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val nameField = v.find<TextView>(R.id.wow_item_name_text)
        var name: CharSequence
            get() = nameField.text
            set(s: CharSequence) {
                nameField.text = s
            }

    }

}