package co.uk.androidrecruitmenttask.feature.startships.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.startships.ui.adapters.viewholder.StarshipViewHolder
import java.util.ArrayList

class StarshipsAdapter(
    val starshipsList: ArrayList<Starships>
) : RecyclerView.Adapter<StarshipViewHolder>() {

    companion object {
        private const val LAST_INDEX = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder =
            StarshipViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_starship, parent, false))

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val starships = starshipsList[position]
        holder.setItem(starships.name)
    }

    override fun getItemCount(): Int =
            starshipsList.size

    fun updateList(starships: ArrayList<Starships>) = with(starshipsList) {
        clear()
        addAll(starships)
        notifyDataSetChanged()
    }

    fun addStarship(starship: Starships) = with(starshipsList) {
        add(starship)
        notifyItemInserted(size - LAST_INDEX)
    }
}
