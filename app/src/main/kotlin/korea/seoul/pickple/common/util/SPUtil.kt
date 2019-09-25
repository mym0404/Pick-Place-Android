package korea.seoul.pickple.common.util

import android.app.Application
import android.content.Context

/**
 * Created by mj on 24, September, 2019
 */

class SPUtil(private val context: Application) {

    enum class SPKeys(val key : String) {
        TOKEN("TOKEN")


        ,

        ;
    }
    companion object {
        val DEFAULT_STRING = null
        val DEFAULT_BOOLEAN = false
        val DEFAULT_INT = -1
        val DEFAULT_FLOAT = -1.0f
    }

    private val packageName: String = context.packageName

    private val preferences = context.getSharedPreferences(packageName, Context.MODE_PRIVATE)

    fun putString(key: SPKeys, value: String) = preferences.edit().putString(key.key, value).apply()


    fun putBoolean(key: SPKeys, value: Boolean) = preferences.edit().putBoolean(key.key, value).apply()


    fun putInt(key: SPKeys, value: Int) = preferences.edit().putInt(key.key, value).apply()


    fun putFloat(key: SPKeys, value: Float) = preferences.edit().putFloat(key.key, value).apply()


    fun getString(key: SPKeys) = preferences.getString(key.key, DEFAULT_STRING)


    fun getBoolean(key: SPKeys) = preferences.getBoolean(key.key, DEFAULT_BOOLEAN)


    fun getInt(key: SPKeys) = preferences.getInt(key.key, DEFAULT_INT)


    fun getFloat(key: SPKeys) = preferences.getFloat(key.key, DEFAULT_FLOAT)


}