package co.uk.androidrecruitmenttask.util.tools

interface HttpErrorProvider {

    fun getStartWarsPeopleMessage(error: Throwable): String
}