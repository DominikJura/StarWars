package co.uk.androidrecruitmenttask.feature.main.presentation

import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Router
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.View
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.Subject

class MainActivityPresenter(
    private val view: View,
    private val router: Router,
    private val repository: Repository,
    private val onLoadMoreSubject: Subject<Int>,
    private val httpErrorProvider: HttpErrorProvider,
    private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() = with(view) {
        compositeDisposable.add(onLoadMoreSubject
                .distinct()
                .filter { !isAllPagesLoaded }
                .doOnNext { isPageLoading = true }
                .flatMapSingle { repository.getPeople(it) }
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
                .subscribe(
                        { addPeopleToList(it.results) },
                        { showSnackBar(httpErrorProvider.getStartWarsPeopleMessage(it)) }
                )
        )

        onLoadMoreSubject.onNext(nextPageIndex)
    }

    override fun onLoadMore() {
        onLoadMoreSubject.onNext(view.nextPageIndex)
    }

    override fun onItemClicked(person: People) = with(person) {
        router.navigateToStarshipScreen(starships)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}