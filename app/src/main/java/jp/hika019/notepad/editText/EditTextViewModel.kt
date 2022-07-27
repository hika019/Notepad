package jp.hika019.notepad.editText


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import jp.hika019.notepad.NotepadRepository
import jp.hika019.notepad.txtData
import jp.hika019.notepad.updateTxtData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EditTextViewModel: ViewModel() {
    val text = MutableLiveData<String>("テスト")
    var saveEnabled = MutableLiveData(false)
    val editActivityFinish = MutableLiveData<Boolean>(false)

    init {
        text.asFlow()
            .onEach {
                changeEnabled()
            }.launchIn(viewModelScope)
    }

    fun save(context: Context){
        if (text.value.isNullOrEmpty()){
            return
        }
        txtData.value!!.add(0, text.value!!)
        val notepadRepository = NotepadRepository(context)
        notepadRepository.writeData(txtData.value!!)
        editActivityFinish.value = true
        updateTxtData.value = true
    }

    private fun changeEnabled(){
        saveEnabled.value = text.value != ""
    }
}