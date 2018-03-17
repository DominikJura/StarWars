package co.uk.androidrecruitmenttask.feature.main.presentation

import android.util.Log
import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.StarWarsService
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivityPresenter(
        private val view: View,
        private val service: StarWarsService,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() {
        fetchPeopleFromRemote()
    }

    override fun fetchPeopleFromRemote() {
        Log.d("TEST", "page: ${view.nextPageIndex}")
        view.isPageLoading = true
        compositeDisposable.add(service.getPeople(view.nextPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse<People>>() {
                    override fun onSuccess(peopleListResponse: ListResponse<People>) {
                        if(peopleListResponse.next == null) {
                            view.isAllPagesLoaded = true
                        }

                        view.addPeopleToList(peopleListResponse.results)
                        view.nextPageIndex++
                        view.isPageLoading = false
                    }

                    override fun onError(e: Throwable) {
                        view.isPageLoading = false
                        view.showSnackBar(e.message)
                    }
                }))
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}