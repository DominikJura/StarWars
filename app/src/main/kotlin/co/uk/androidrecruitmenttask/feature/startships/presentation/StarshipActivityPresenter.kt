package co.uk.androidrecruitmenttask.feature.startships.presentation

import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.View
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class StarshipActivityPresenter(
        private val view: View,
        private val repository: Repository,
        private val httpErrorProvider: HttpErrorProvider,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    override fun initialize() = Unit

    override fun onStarshipsUrlExtras(starshipsUrlList: ArrayList<String>) {
        compositeDisposable.add(repository.getStarships(starshipsUrlList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Starships>>() {
                    override fun onSuccess(starshipsList: List<Starships>) {
                        view.addStarshipsToList(starshipsList)
                    }

                    override fun onError(error: Throwable) {
                        view.showSnackBar(httpErrorProvider.getStartWarsPeopleMessage(error))
                    }
                })
        )
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}