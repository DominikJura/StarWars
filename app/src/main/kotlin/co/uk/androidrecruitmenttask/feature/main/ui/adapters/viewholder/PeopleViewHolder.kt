package co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.uk.androidrecruitmenttask.R

class PeopleViewHolder(peopleView: View) : RecyclerView.ViewHolder(peopleView) {

    @BindView(R.id.item_people_name)
    lateinit var nameTextView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun setItem(name: String) {
        nameTextView.text = name
    }
}