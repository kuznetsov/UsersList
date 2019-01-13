package com.apps.elliotgrin.userslist.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apps.elliotgrin.userslist.R
import com.apps.elliotgrin.userslist.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.user_item.view.*

class UsersRecyclerAdapter(

    val data: List<User>,
    val context: Context,
    val editUser: (User) -> Unit

) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position]) { editUser(data[position]) }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(user: User, editUser: () -> Unit) {
        val fullName = user.firstName + " " + user.lastName
        view.nameTextView.text = fullName
        view.emailTextView.text = user.email

        if (user.avatarUrl != null && user.avatarUrl.isNotEmpty()) {
            Glide.with(view.avatarImageView)
                .load(user.avatarUrl)
                .into(view.avatarImageView)
        } else {
            val context = view.avatarImageView.context
            val drawableIcon = ContextCompat.getDrawable(context, R.drawable.ic_default_avatar)
            view.avatarImageView.setImageDrawable(drawableIcon)
        }

        view.setOnClickListener { editUser() }
    }
}