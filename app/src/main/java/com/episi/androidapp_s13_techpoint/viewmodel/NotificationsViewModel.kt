package com.episi.androidapp_s13_techpoint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.episi.androidapp_s13_techpoint.R
import com.episi.androidapp_s13_techpoint.model.Notification

class NotificationsViewModel : ViewModel() {

    private val _notifications = MutableLiveData<List<Notification>>().apply {
        value = listOf(
            Notification(
                R.drawable.ic_notifications_black_24dp,
                "Nueva tienda agregada",
                "Se añadió 'Novedades Lucerito' al mapa.",
                "Hace 3 días"
            ),
            Notification(
                R.drawable.ic_notifications_black_24dp,
                "Stock actualizado",
                "TechWorld ha repuesto laptops gamer.",
                "Ayer"
            ),
            Notification(
            R.drawable.ic_notifications_black_24dp,
                "Registro confirmado",
                "Tu visita a CompuMarket ha sido agendada.",
                "Hace 1 hora"
            ),
        )
    }

    val notifications: LiveData<List<Notification>> = _notifications
}
