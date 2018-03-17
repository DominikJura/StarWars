package co.uk.androidrecruitmenttask.feature.main.presentation

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class MainActivityPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: MainActivityContract.View

    @Mock
    private lateinit var router: MainActivityContract.Router

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var httpErrorProvider: HttpErrorProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var people: People

    @Mock
    private lateinit var starshipsList: ArrayList<String>

    private lateinit var presenter: MainActivityContract.Presenter

    private val onLoadSubject = PublishSubject.create<Int>()
    private val getPeopleSubject = PublishSubject.create<ListResponse<People>>().singleOrError()

    override fun setup() {
        super.setup()

        presenter = MainActivityPresenter(
                view,
                router,
                repository,
                onLoadSubject,
                httpErrorProvider,
                compositeDisposable
        )
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(
                view,
                router,
                repository,
                httpErrorProvider,
                compositeDisposable,
                people,
                starshipsList
        )
    }

    @Test
    fun `should initialize disposables and emits onLoadNext after initialize is called()`() {
        initialize()
    }

    private fun initialize() {
        val mockInitialPageValue = 1
        val mockIsAllPagesLoaded = false

        `when`(view.isAllPagesLoaded).thenReturn(mockIsAllPagesLoaded)
        `when`(view.nextPageIndex).thenReturn(mockInitialPageValue)
        `when`(repository.getPeople(anyInt())).thenReturn(getPeopleSubject)

        presenter.initialize()

        verify(view, times(1)).nextPageIndex
        verify(view, times(1)).isAllPagesLoaded
        verify(view, times(1)).isPageLoading = true
        verify(repository, times(1)).getPeople(mockInitialPageValue)
        verify(compositeDisposable, times(1)).add(any(Disposable::class.java))
    }

    @Test
    fun `should show snackBar with error message after onError emits`() {
//        getPeopleSubject.
    }

    @Test
    fun `should navigate to starship after onItemClicked is called`() {
        `when`(people.starships).thenReturn(starshipsList)

        presenter.onItemClicked(people)

        verify(people, times(1)).starships
        verify(router, times(1)).navigateToStarshipScreen(starshipsList)
    }

    @Test
    fun `should clear composite disposable after clear is called`() {
        presenter.clear()

        verify(compositeDisposable, times(1)).clear()
    }
}