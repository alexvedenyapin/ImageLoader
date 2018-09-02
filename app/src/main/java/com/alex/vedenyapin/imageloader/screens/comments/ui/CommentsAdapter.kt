package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.databinding.CommentItemBinding
import com.alex.vedenyapin.imageloader.model.comment.Comment

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private lateinit var commentsList: List<Comment>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CommentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(commentsList[position])

    override fun getItemCount(): Int {
        return if (::commentsList.isInitialized) commentsList.size else 0
    }

    fun updateCommentsList(commentsList: List<Comment>) {
        this.commentsList = commentsList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val commentViewModel = CommentViewModel()

        fun bind(post: Comment) {
            commentViewModel.bind(post)
            binding.commentViewModel = commentViewModel
        }
    }
}