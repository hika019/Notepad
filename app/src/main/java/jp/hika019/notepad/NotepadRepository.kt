package jp.hika019.notepad

import android.content.Context
import org.json.JSONArray


class NotepadRepository(context: Context) {
    private val sharedPrefKey = context.packageName
    private val key = "Notepad"
    private val sharedPref = context.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)


    fun readData(): ArrayList<String> {
        val strJson = sharedPref.getString(key, null)
        val dataList = arrayListOf<String>()

        if (strJson.isNullOrEmpty()){
            return dataList
        }

        try {
            val jsonAry = JSONArray(strJson)
            for (i in 0 until jsonAry.length()) {
                dataList.add(jsonAry.getString(i))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return dataList
    }

    fun writeData(dataList: ArrayList<String>){
        val jsonAry = JSONArray()
        for (data in dataList){
            jsonAry.put(data)
        }
        val editor = sharedPref.edit()
        editor.putString(key, jsonAry.toString())
        editor.apply()
    }

}