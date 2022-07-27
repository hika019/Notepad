package jp.hika019.notepad

import androidx.lifecycle.MutableLiveData

val txtData = MutableLiveData<ArrayList<String>>()
val updateTxtData = MutableLiveData<Boolean>(false)