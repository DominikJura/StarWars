package co.uk.androidrecruitmenttask.feature.main.presentation

import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.data.api.StarWarsService
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Router
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.View
import co.uk.androidrecruitmenttask.util.configuration.ResourceProvider
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject
import retrofit2.HttpException

class MainActivityPresenter(
        private val view: View,
        private val router: Router,
        private val service: StarWarsService,
        private val onLoadMoreSubject: Subject<Int>,
        private val resourceProvider: ResourceProvider,
        private val httpErrorProvider: HttpErrorProvider,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() = with(view) {
        compositeDisposable.add(onLoadMoreSubject
                .distinct()
                .filter { !isAllPagesLoaded }
                .doOnNext { isPageLoading = true }
                .flatMapSingle {
                    service.getPeople(it)
                            .subscribeOn(Schedulers.io())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (it.next == null) {
                        isAllPagesLoaded = true
                    }
                }
                .doAfterNext {
                    isPageLoading = false
                    nextPageIndex++
                }
                .doOnError { isPageLoading = false }
                .subscribe({ addPeopleToList(it.results) }, { handelErrorMessage(it) })
        )

        onLoadMoreSubject.onNext(nextPageIndex)
    }

    private fun handelErrorMessage(error: Throwable) {
        val errorMessage = when (error) {
            is HttpException -> {
                httpErrorProvider.getStartWarsPeopleMessage(error)
            }
            else -> resourceProvider.getString(R.string.default_error_message)
        }
        view.showSnackBar(errorMessage)
    }

    override fun onLoadMore() {
        onLoadMoreSubject.onNext(view.nextPageIndex)
    }

    override fun onItemClicked(person: People) = with(person.starships) {
        when {
            isEmpty() -> view.showToast(resourceProvider.getString(R.string.no_starships))
            else -> router.navigateToStarshipScreen(this)
        }
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}