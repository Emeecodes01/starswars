package com.example.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.local.utils.StarWarsLocalConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StarWarsPreferenceManager @Inject constructor(@ApplicationContext context: Context): IStarWarsPreferenceManager {

    private val sharedPreferences: SharedPreferences by lazy { context.getSharedPreferences(StarWarsLocalConstants.PREFERENCE_NAME,
        Context.MODE_PRIVATE)}

    /**
     * Whenever the user search for characters this value here helps keep track of the next page in the paginated response
     */
    override var next: String?
        get() = sharedPreferences.getString(NEXT_KEY, null)
        set(value) {sharedPreferences.edit { putString(NEXT_KEY, value) }}

    companion object {
        const val NEXT_KEY = "next"
    }
}