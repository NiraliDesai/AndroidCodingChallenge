package com.example.otchallenge.presenter.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otchallenge.MyApplication
import com.example.otchallenge.databinding.ActivityMainBinding
import com.example.otchallenge.domain.model.BookModel
import com.example.otchallenge.presenter.HomeContract
import javax.inject.Inject

/**
 * This is the Entrypoint(start point) of the application, this screen will display the List of books.
 */
class HomeActivity : AppCompatActivity(), HomeContract.View {

    // Dagger component for presenter injection
    @Inject
    lateinit var presenter: HomeContract.Presenter

    private lateinit var bookAdapter: BookAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Dagger component and inject dependencies
        (application as MyApplication).appComponent.presenterComponent().create().inject(this)

        // Request to fetch the book list
        presenter.attachView(this)

        // Set up RecyclerView with the book list adapter
        bookAdapter = BookAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = bookAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this))

        // SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.fetchBookData()
        }
        binding.buttonRetry.setOnClickListener {
            presenter.fetchBookData()
        }
        presenter.fetchBookData() // Initial data fetch

    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.errMessage.visibility = View.GONE
        binding.buttonRetry.visibility = View.GONE

        if (!binding.swipeRefreshLayout.isRefreshing) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
        binding.errMessage.visibility = View.GONE
        binding.buttonRetry.visibility = View.GONE
    }

    override fun showData(data: List<BookModel>) {
        // Display the list of books in the RecyclerView
        bookAdapter.setData(data)

    }

    override fun showError(error: String) {
        //This will show: generic error and network disconnection error.
        binding.errMessage.text = error
        binding.errMessage.visibility = View.VISIBLE
        binding.buttonRetry.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
