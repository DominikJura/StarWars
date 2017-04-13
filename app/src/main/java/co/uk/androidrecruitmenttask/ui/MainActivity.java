package co.uk.androidrecruitmenttask.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.uk.androidrecruitmenttask.R;
import co.uk.androidrecruitmenttask.StarWarsApplication;
import co.uk.androidrecruitmenttask.data.People;
import co.uk.androidrecruitmenttask.data.api.ListResponse;
import co.uk.androidrecruitmenttask.data.api.StarWarsService;
import co.uk.androidrecruitmenttask.ui.util.EndlessRecyclerViewScrollListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    StarWarsService service;

    RecyclerView peopleRecyclerView;
    PeopleAdapter adapter;
    StarWarsScrollListener starWarsScrollListener;
    PeopleClickListener clickListener;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<People> peopleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StarWarsApplication) getApplication()).getApiComponent().inject(this);

        setContentView(R.layout.activity_main);
        peopleRecyclerView = (RecyclerView) findViewById(R.id.peopleList);
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
                        peopleList.addAll(peopleListResponse.getResults());
                        adapter.notifyDataSetChanged();
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
        adapter = new PeopleAdapter(peopleList);
        peopleRecyclerView.setAdapter(adapter);
        starWarsScrollListener = new StarWarsScrollListener((LinearLayoutManager) layoutManager);
        peopleRecyclerView.addOnScrollListener(starWarsScrollListener);
    }

    private void unsubscribe() {
        compositeDisposable.clear();
    }

    class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

        private List<People> list;

        public PeopleAdapter(List<People> list) {
            this.list = list;
        }

        @Override
        public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_people, parent, false);
            itemView.setOnClickListener(clickListener);
            return new PeopleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PeopleViewHolder holder, int position) {
            People people = list.get(position);
            holder.name.setText(people.getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class PeopleViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public PeopleViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
            }
        }
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
            People item = peopleList.get(itemPosition);
            Toast.makeText(MainActivity.this, String.format("You clicked %s", item.getName()),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
