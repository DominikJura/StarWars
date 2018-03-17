package co.uk.androidrecruitmenttask.feature.main.presentation

import co.uk.androidrecruitmenttask.data.api.StarWarsService
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.View
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject
import retrofit2.HttpException

class MainActivityPresenter(
        private val view: View,
        private val service: StarWarsService,
        private val onLoadMoreSubject: Subject<Int>,
        private val httpErrorProvider: HttpErrorProvider,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() {
        compositeDisposable.add(onLoadMoreSubject
                .distinct()
                .filter { !view.isAllPagesLoaded }
                .doOnNext { view.isPageLoading = true }
                .flatMapSingle {
                    service.getPeople(it)
                            .subscribeOn(Schedulers.io())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (it.next == null) {
                        view.isAllPagesLoaded = true
                    }
                }
                .doAfterNext {
                    view.isPageLoading = false
                    view.nextPageIndex++
                }
                .subscribe(
                        {
                            view.addPeopleToList(it.results)
                        }, {
                    view.isPageLoading = false
                    when (it) {
                        is HttpException -> {
                            val errorMessage = httpErrorProvider.getStartWarsPeopleMessage(it)
                            view.showSnackBar(errorMessage)
                        }
                    }
                })
        )
        onLoadMoreSubject.onNext(view.nextPageIndex)
    }

    override fun onLoadMore() {
        onLoadMoreSubject.onNext(view.nextPageIndex)
    }

    override fun onItemClicked(position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}