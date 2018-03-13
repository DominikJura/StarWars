package co.uk.androidrecruitmenttask.feature.main.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import co.uk.androidrecruitmenttask.R;
import co.uk.androidrecruitmenttask.data.People;
import co.uk.androidrecruitmenttask.data.api.ListResponse;
import co.uk.androidrecruitmenttask.data.api.StarWarsService;
import co.uk.androidrecruitmenttask.feature.common.ui.BaseActivity;
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract;
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleRecyclerAdapter;
import co.uk.androidrecruitmenttask.ui.util.EndlessRecyclerViewScrollListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity<MainActivityContract.Presenter> {

    @BindView(R.id.main_people_recycler)
    RecyclerView peopleRecyclerView;

    @Inject
    StarWarsService service;

    @Inject
    PeopleRecyclerAdapter peopleAdapter;

    StarWarsScrollListener starWarsScrollListener;
    PeopleClickListener clickListener;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clickListener = new PeopleClickListener();
        initAdapter();
        fetchPeopleFromRemote(1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribe();
    }

    private void fetchPeopleFromRemote(int page) {
        starWarsScrollListener.resetState();
        compositeDisposable.add(service.getPeople(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ListResponse<People>>() {
                    @Override
                    public void onSuccess(@NonNull ListResponse<People> peopleListResponse) {
                        peopleAdapter.addPeople(peopleListResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // fixme
                    }
                }));
    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        peopleRecyclerView.setLayoutManager(layoutManager);
        peopleRecyclerView.setAdapter(peopleAdapter);
        starWarsScrollListener = new StarWarsScrollListener((LinearLayoutManager) layoutManager);
        peopleRecyclerView.addOnScrollListener(starWarsScrollListener);
    }

    private void unsubscribe() {
        compositeDisposable.clear();
    }

    private class StarWarsScrollListener extends EndlessRecyclerViewScrollListener {

        public StarWarsScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            fetchPeopleFromRemote(page);
        }
    }

    class PeopleClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int itemPosition = peopleRecyclerView.getChildLayoutPosition(v);
//            People item = peopleList.get(itemPosition);
//            Toast.makeText(MainActivity.this, String.format("You clicked %s", item.getName()),
//                    Toast.LENGTH_SHORT).show();
        }
    }

}
