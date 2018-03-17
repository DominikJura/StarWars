package co.uk.androidrecruitmenttask.feature.startships.presentation

import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.View
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StarshipActivityPresenter(
        private val view: View,
        private val repository: Repository,
        private val httpErrorProvider: HttpErrorProvider,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() = Unit

    override fun onStarshipsUrlExtras(starshipsUrlList: ArrayList<String>) {
        when {
            starshipsUrlList.isEmpty() -> view.showNoStarshipsText()
            else -> getStarshipList(starshipsUrlList)
        }
    }

    private fun getStarshipList(starshipsUrlList: ArrayList<String>) = with(view) {
        showProgress()
        compositeDisposable.add(repository.getStarships(starshipsUrlList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addStarshipToList(it)
                }, {
                    hideProgress()
                    showSnackBar(httpErrorProvider.getStartWarsPeopleMessage(it))
                }, {
                    hideProgress()
                })
        )
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}