package co.uk.androidrecruitmenttask.feature.startships.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.uk.androidrecruitmenttask.R;
import co.uk.androidrecruitmenttask.data.api.Starships;

public class StarshipsAdapter extends RecyclerView.Adapter<StarshipsAdapter.ViewHolder> {

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
