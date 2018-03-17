package co.uk.androidrecruitmenttask.util.configuration

import android.content.Context
import android.support.annotation.ArrayRes
import android.support.annotation.StringRes
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.Locale

class ResourceProviderImpl(private val appContext: Context) : ResourceProvider {

    override fun getString(@StringRes stringId: Int): String = appContext.getString(stringId)
}
