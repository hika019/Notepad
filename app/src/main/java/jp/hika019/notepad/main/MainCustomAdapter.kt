package jp.hika019.notepad.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.hika019.notepad.R
import kotlinx.android.synthetic.main.item_subjet.view.*


class MainCustomAdapter(
    private val txtList :ArrayList<String>
): RecyclerView.Adapter<MainCustomAdapter.CustomViewHolder>() {

    private val TAG ="MainCustomAdapter"

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val subject = view.itemSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.item_subjet, parent, false)
        return  CustomViewHolder(item)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.subject.text = txtList[position]
        holder.view.setOnClickListener{
            Log.d(TAG, "Click: view")
        }

        holder.view.deleteTxt.setOnClickListener {
            Log.d(TAG, "Click: deleteTxt")
        }

    }

    override fun getItemCount(): Int {
        return txtList.size
    }
}