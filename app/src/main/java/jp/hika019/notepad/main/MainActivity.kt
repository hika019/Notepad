package jp.hika019.notepad.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.hika019.notepad.NotepadRepository
import jp.hika019.notepad.R
import jp.hika019.notepad.databinding.ActivityMainBinding
import jp.hika019.notepad.txtData
import jp.hika019.notepad.updateTxtData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val TAG = "MainActivity"

    init {
        txtData.value = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        binding.mainViewModel =this.viewModel
        binding.lifecycleOwner = this

        val notepadRepository = NotepadRepository(this)
        txtData.value = notepadRepository.readData()


        val recyclerView = binding.recycleview
        val layoutManager = LinearLayoutManager(this)
        var adapter = MainCustomAdapter(txtData.value!!)

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

                //入れ替えの反映
                notepadRepository.writeData(txtData.value!!)

                //TODO 要変更
                txtData.value = notepadRepository.readData()
                Log.d(TAG, notepadRepository.readData().toString())
                Log.d(TAG, txtData.value.toString())

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //スワイプで消す
            }
        })

        //recycleviewの更新
        listOf(txtData, updateTxtData).onEach {
            it.observe(this, Observer {
                notepadRepository.writeData(txtData.value!!)
                Log.d(TAG, txtData.value.toString())

                adapter = MainCustomAdapter(txtData.value!!)
                recycleview.adapter = adapter
            })

        }

        touchHelper.attachToRecyclerView(recyclerView)
        recycleview.addItemDecoration(touchHelper)
        recycleview.layoutManager = layoutManager
        recycleview.setHasFixedSize(true)
    }
}