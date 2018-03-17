package co.uk.androidrecruitmenttask.util.tools

import retrofit2.HttpException

interface HttpErrorProvider {

    fun getStartWarsPeopleMessage(error: HttpException): String
}