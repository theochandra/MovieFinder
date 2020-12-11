package com.android.moviefinder.presentation.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
        private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    /**
     * The total number of items in the data set after the last load
     */
    private var previousTotal = 0

    /**
     * true if we are still waiting for the last set of data to load
     */
    var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                previousTotal = totalItemCount
            }
        }

        val visibleThreshold = 2
        if (!isLoading &&
                (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            onLoadMore()
            isLoading = true
        }
    }

}