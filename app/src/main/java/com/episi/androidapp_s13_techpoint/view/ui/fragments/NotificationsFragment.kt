package com.episi.androidapp_s13_techpoint.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.episi.androidapp_s13_techpoint.databinding.FragmentNotificationsBinding
import com.episi.androidapp_s13_techpoint.viewmodel.NotificationsViewModel
import com.episi.androidapp_s13_techpoint.view.adapter.NotificationAdapter

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]

        binding.notificationsRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.notifications.observe(viewLifecycleOwner) { list ->
            binding.notificationsRecycler.adapter = NotificationAdapter(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
