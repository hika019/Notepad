package jp.hika019.notepad.editText

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditTextViewModel: ViewModel() {
    val text = MutableLiveData<String>("")
    var finish = MutableLiveData<Boolean>(false)

    fun save(){

    }

    fun goMain(){
        finish.value = true
    }
}