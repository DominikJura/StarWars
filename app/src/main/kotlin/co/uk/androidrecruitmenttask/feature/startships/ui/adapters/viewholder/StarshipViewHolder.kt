package co.uk.androidrecruitmenttask.feature.startships.ui.adapters.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.uk.androidrecruitmenttask.R

class StarshipViewHolder(starshipView: View) : RecyclerView.ViewHolder(starshipView) {

    @BindView(R.id.item_starship_name)
    lateinit var starshipNameTextView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun setItem(name: String) {
        starshipNameTextView.text = name
    }
}