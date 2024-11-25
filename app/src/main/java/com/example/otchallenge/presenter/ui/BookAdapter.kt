package com.example.otchallenge.presenter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otchallenge.R
import com.example.otchallenge.databinding.ItemBookBinding
import com.example.otchallenge.domain.model.BookModel

/**
 * Adapter class for displaying the list of books in a RecyclerView
 * Basic agenda to use ViewHolder pattern to create ViewHolder and then bind the data with the into ViewHolder.
 */
class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val books = mutableListOf<BookModel>()
    private var lastPosition = -1

    fun setData(newBooks: List<BookModel>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int = books.size

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.fade_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    // ViewHolder class for binding book data to the views
    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookModel) {
            binding.bookTitleTextView.text = book.title
            binding.bookDescriptionTextView.text = book.description
            Glide.with(binding.bookImageView.context)
                .load(book.bookImage)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.bookImageView)
        }
    }
}