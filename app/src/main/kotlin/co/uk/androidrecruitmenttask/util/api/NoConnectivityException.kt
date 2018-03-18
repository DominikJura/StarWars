package co.uk.androidrecruitmenttask.util.api

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String
        get() = "No connectivity exception"
}