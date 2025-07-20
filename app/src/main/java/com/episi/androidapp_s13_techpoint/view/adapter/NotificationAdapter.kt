package com.episi.androidapp_s13_techpoint.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.episi.androidapp_s13_techpoint.databinding.ItemNotificationBinding
import com.episi.androidapp_s13_techpoint.model.Notification

class NotificationAdapter(private val notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        with(holder.binding) {
            notificationIcon.setImageResource(notification.iconRes)
            notificationTitle.text = notification.title
            notificationMessage.text = notification.message
            notificationTime.text = notification.time
        }
    }

    override fun getItemCount() = notifications.size
}
