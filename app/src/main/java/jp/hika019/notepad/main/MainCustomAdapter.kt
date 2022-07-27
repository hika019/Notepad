package jp.hika019.notepad.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.hika019.notepad.R
import jp.hika019.notepad.editText.EditTextActivity
import jp.hika019.notepad.txtData
import jp.hika019.notepad.updateTxtData
import kotlinx.android.synthetic.main.item_subjet.view.*


class MainCustomAdapter(val context: Context): RecyclerView.Adapter<MainCustomAdapter.CustomViewHolder>() {

    private val TAG ="MainCustomAdapter"

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val subject: TextView = view.itemSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.item_subjet, parent, false)
        return  CustomViewHolder(item)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.subject.text = txtData.value!![position]
        holder.view.setOnClickListener{
            Log.d(TAG, "Click: view")
            val intent = Intent(context, EditTextActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }

        holder.view.deleteTxt.setOnClickListener {
            Log.d(TAG, "Click: deleteTxt")
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return txtData.value!!.size
    }

    private fun removeItem(position: Int){
        txtData.value!!.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
        updateTxtData.value = true
    }
}