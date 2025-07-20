package com.episi.androidapp_s13_techpoint.view.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.episi.androidapp_s13_techpoint.R
import com.episi.androidapp_s13_techpoint.databinding.FragmentMapBinding
import com.episi.androidapp_s13_techpoint.viewmodel.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapViewModel: MapViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null
    private var lastKnownLocation: Location? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            if (granted) {
                getDeviceLocation()
                updateLocationUI()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permisos de ubicación denegados.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Inicializar el mapa
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Sugerencias dinámicas desde ViewModel (obtenidas del JSON)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            mapViewModel.obtenerTodosLosEquiposUnicos()
        )
        binding.etTechComponent.setAdapter(adapter)

        // Buscar componente tech
        binding.btnSearch.setOnClickListener {
            val query = binding.etTechComponent.text.toString()
            if (query.isNotEmpty()) {
                searchForEstablishments(query)
            } else {
                Toast.makeText(requireContext(), "Ingresa un componente tech.", Toast.LENGTH_SHORT).show()
            }
        }

        // Resultado observable
        mapViewModel.resultadoBusqueda.observe(viewLifecycleOwner) { resultado ->
            binding.txtResultadoBusqueda.visibility = View.VISIBLE
            binding.txtResultadoBusqueda.text = resultado
        }

        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        getLocationPermission()
    }

    private fun getLocationPermission() {
        val context = requireContext()
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getDeviceLocation()
            updateLocationUI()
        } else {
            requestPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            )
        }
    }

    private fun getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        lastKnownLocation = location
                        val userLatLng = LatLng(location.latitude, location.longitude)
                        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15f))
                        googleMap?.addMarker(
                            MarkerOptions().position(userLatLng).title("Tu ubicación")
                        )
                    } else {
                        val fallback = LatLng(-9.0731, -78.5937) // Chimbote
                        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(fallback, 12f))
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("MapFragment", "Error al obtener ubicación: ${e.message}", e)
        }
    }

    private fun updateLocationUI() {
        if (googleMap == null) return
        try {
            val permiso = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            googleMap?.isMyLocationEnabled = permiso
            googleMap?.uiSettings?.isMyLocationButtonEnabled = permiso
        } catch (e: SecurityException) {
            Log.e("MapFragment", "Error en updateLocationUI: ${e.message}", e)
        }
    }

    private fun searchForEstablishments(component: String) {
        if (lastKnownLocation == null) {
            Toast.makeText(requireContext(), "Ubicación desconocida", Toast.LENGTH_SHORT).show()
            return
        }

        val ubicacion = LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude)
        val tienda = mapViewModel.buscarTiendaCercana(component, ubicacion)

        googleMap?.clear()
        googleMap?.addMarker(MarkerOptions().position(ubicacion).title("Tu ubicación"))

        tienda?.let {
            val tiendaLatLng = LatLng(it.lat, it.lng)
            googleMap?.addMarker(MarkerOptions().position(tiendaLatLng).title(it.nombre))

            // Hacer zoom para mostrar ambos puntos
            val bounds = LatLngBounds.builder()
                .include(ubicacion)
                .include(tiendaLatLng)
                .build()

            googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
