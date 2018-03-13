package co.uk.androidrecruitmenttask.feature.main.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.feature.main.ui.view.PeopleViewHolder

class PeopleRecyclerAdapter(private val peopleList: MutableList<People>) : RecyclerView.Adapter<PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_people, parent, false)
        return PeopleViewHolder(view)
    }

    override fun getItemCount(): Int =
            peopleList.size


    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.setItem(peopleList[position].name)
    }

    fun addPeople(people: List<People>) {
        peopleList.addAll(people)
        notifyDataSetChanged()
    }
}