package co.uk.androidrecruitmenttask.feature.main.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleAdapterType.LOADING
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleAdapterType.PEOPLE_ITEM
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder.LoadingViewHolder
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder.PeopleViewHolder

typealias OnItemClickListener = (position: People) -> Unit

class PeopleRecyclerAdapter(val peopleList: ArrayList<People>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val NUMBER_OF_LOADINGS_VIEWS = 1
        private const val LAST_INDEX = 1
    }

    var onItemClickListener: OnItemClickListener? = null

    var isLoading: Boolean = false
        set(loading) {
            field = loading
            when(loading) {
                true -> notifyItemInserted(peopleList.size)
                false -> notifyItemRemoved(peopleList.size)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (PeopleAdapterType.values()[viewType]) {
                PEOPLE_ITEM -> getPeopleViewHolder(parent)
                LOADING -> getLoadingViewHolder(parent)
            }

    private fun getPeopleViewHolder(viewGroup: ViewGroup): PeopleViewHolder =
            PeopleViewHolder(LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.item_people, viewGroup, false)
            )

    private fun getLoadingViewHolder(viewGroup: ViewGroup): LoadingViewHolder =
            LoadingViewHolder(LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.item_loading, viewGroup, false))

    override fun getItemCount(): Int = when {
        isLoading -> peopleList.size + NUMBER_OF_LOADINGS_VIEWS
        else -> peopleList.size
    }

    override fun getItemViewType(position: Int): Int = when {
        isLoadingViewType(position) -> LOADING.ordinal
        else -> PEOPLE_ITEM.ordinal
    }

    private fun isLoadingViewType(position: Int) =
            isLoading && peopleList.size == position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(holder) {
        when (this) {
            is PeopleViewHolder -> {
                itemView.setOnClickListener { onItemClickListener?.invoke(peopleList[position]) }
                setItem(peopleList[position].name)
            }
            is LoadingViewHolder -> { showProgress() }
        }
    }

    fun updateList(people: List<People>) = with(peopleList) {
        clear()
        addAll(people)
        notifyDataSetChanged()
    }

    fun addPeople(people: List<People>) = with(peopleList) {
        val beforeItemIndex = itemCount
        addAll(people)
        notifyItemRangeInserted(beforeItemIndex, size - LAST_INDEX)
    }
}