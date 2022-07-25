package jp.hika019.notepad.editText


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EditTextViewModel: ViewModel() {
    val text = MutableLiveData<String>("テスト")
    var saveEnabled = MutableLiveData(false)

    init {
        text.asFlow()
            .onEach {
                changeEnabled()
            }.launchIn(viewModelScope)
    }

    fun save(){
        //val file = File(context.filesDir, "")
    }

    private fun changeEnabled(){
        saveEnabled.value = text.value != ""
    }
}