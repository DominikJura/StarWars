package co.uk.androidrecruitmenttask.util.configuration

import android.content.Context
import android.support.annotation.StringRes

class ResourceProviderImpl(private val appContext: Context) : ResourceProvider {

    override fun getString(@StringRes stringId: Int): String = appContext.getString(stringId)
}
