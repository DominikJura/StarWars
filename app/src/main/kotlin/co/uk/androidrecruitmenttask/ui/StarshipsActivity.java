package co.uk.androidrecruitmenttask.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.uk.androidrecruitmenttask.R;
import co.uk.androidrecruitmenttask.data.Starships;

public class StarshipsActivity extends AppCompatActivity {

    RecyclerView starshipsRecyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships);
        starshipsRecyclerView = (RecyclerView) findViewById(R.id.starshipsList);
        initAdapter();
    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        starshipsRecyclerView.setLayoutManager(layoutManager);
        adapter = new StarshipsAdapter();
        starshipsRecyclerView.setAdapter(adapter);
    }

    class StarshipsAdapter extends RecyclerView.Adapter<StarshipsAdapter.ViewHolder> {

        private List<Starships> starshipsList;

        public void setStarshipsList(List<Starships> starshipsList) {
            this.starshipsList = starshipsList;
            notifyDataSetChanged();
        }

        public void addStarships(List<Starships> starshipsList) {
            int curSize = getItemCount();
            this.starshipsList.addAll(starshipsList);
            notifyItemRangeInserted(curSize, starshipsList.size() - 1);
        }

        @Override
        public StarshipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_starship, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(StarshipsAdapter.ViewHolder holder, int position) {
            Starships starships = starshipsList.get(position);
            holder.name.setText(starships.getName());
        }

        @Override
        public int getItemCount() {
            return starshipsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
            }
        }
    }
}
