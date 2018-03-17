package co.uk.androidrecruitmenttask.util.configuration

import android.support.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringId: Int): String
}