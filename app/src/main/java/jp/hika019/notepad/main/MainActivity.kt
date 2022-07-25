package jp.hika019.notepad.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.hika019.notepad.R
import jp.hika019.notepad.databinding.ActivityMainBinding
import jp.hika019.notepad.txtData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        binding.mainViewModel =this.viewModel
        binding.lifecycleOwner = this

        txtData.value = arrayListOf("aaa", "iii", "uuu")

        val adapter = MainCustomAdapter()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = binding.recycleview


        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition

                val tmp = txtData.value!!.removeAt(from)
                txtData.value!!.add(to, tmp)

                adapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //スワイプで消す
            }
        })




        recycleview.adapter = adapter
        recycleview.layoutManager = layoutManager
        recycleview.setHasFixedSize(true)
        touchHelper.attachToRecyclerView(recyclerView)
        recycleview.addItemDecoration(touchHelper)



    }
}