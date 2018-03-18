package co.uk.androidrecruitmenttask.feature.main.presentation

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.ArgumentMatchers
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

    @Mock
    private lateinit var peopleResponse: ListResponse<People>

    @Mock
    private lateinit var peopleList: List<People>

    private lateinit var presenter: MainActivityContract.Presenter

    private val onLoadSubject = PublishSubject.create<Int>()
    private val getPeopleSubject = BehaviorSubject.create<ListResponse<People>>()

    override fun setup() {
        super.setup()

        trampolineRxPlugin()

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
        val mockInitialPageValue = 5

        `when`(repository.getPeople(ArgumentMatchers.anyInt())).thenReturn(getPeopleSubject.singleOrError())

        initialize(mockInitialPageValue)

        verify(view, times(1)).nextPageIndex
    }

    private fun initialize(mockInitialPageValue: Int) {
        val mocksAllPagesLoaded = false

        `when`(view.nextPageIndex).thenReturn(mockInitialPageValue)
        `when`(view.isAllPagesLoaded).thenReturn(mocksAllPagesLoaded)

        presenter.initialize()

        verify(view, times(1)).isPageLoading = true
        verify(view, times(1)).isAllPagesLoaded
        verify(repository, times(1)).getPeople(mockInitialPageValue)
        verify(compositeDisposable, times(1)).add(any(Disposable::class.java))
    }

    @Test
    fun `should add people to list after getPeople-onNext emits`() {
        val mockPeopleResponseNext = "someUrlToNextList"
        val mockInitialPageValue = 1
        val mockNextPageIndex = mockInitialPageValue + 1

        `when`(peopleResponse.next).thenReturn(mockPeopleResponseNext)
        `when`(peopleResponse.results).thenReturn(peopleList)
        `when`(repository.getPeople(ArgumentMatchers.anyInt())).thenReturn(Single.just(peopleResponse))

        initialize(mockInitialPageValue)

        verify(view, times(1)).isPageLoading = false
        verify(view, times(2)).nextPageIndex
        verify(view, times(1)).nextPageIndex = mockNextPageIndex
        verify(view, times(1)).addPeopleToList(peopleList)
    }

    @Test
    fun `should show snackBar with error message after getPeople-onError emits`() {
        val mockException = Exception()
        val mockErrorMessage = "Some error message"
        val mockInitialPageValue = 3

        `when`(repository.getPeople(anyInt())).thenReturn(Single.error(mockException))
        `when`(httpErrorProvider.getStartWarsPeopleMessage(any(Exception::class.java))).thenReturn(mockErrorMessage)

        initialize(mockInitialPageValue)

        verify(httpErrorProvider, times(1)).getStartWarsPeopleMessage(mockException)
        verify(view, times(1)).nextPageIndex
        verify(view, times(1)).showSnackBar(mockErrorMessage)
        verify(view, times(1)).isPageLoading = false
    }

    @Test
    fun `should not pass forward because all pages all loaded after onLoadMore called`() {
        val mockInitialPageValue = 3
        val mockSecondPageValue = 4
        val mockIsAllPagesLoaded = true

        `when`(repository.getPeople(ArgumentMatchers.anyInt())).thenReturn(getPeopleSubject.singleOrError())

        initialize(mockInitialPageValue)

        `when`(view.nextPageIndex).thenReturn(mockSecondPageValue)
        `when`(view.isAllPagesLoaded).thenReturn(mockIsAllPagesLoaded)

        presenter.onLoadMore()

        verify(view, times(2)).nextPageIndex
        verify(view, times(2)).isAllPagesLoaded
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