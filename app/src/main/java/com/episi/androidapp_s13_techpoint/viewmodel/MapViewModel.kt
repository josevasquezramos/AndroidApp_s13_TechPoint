package com.episi.androidapp_s13_techpoint.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.episi.androidapp_s13_techpoint.model.Tienda
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _resultadoBusqueda = MutableLiveData<String>()
    val resultadoBusqueda: LiveData<String> = _resultadoBusqueda

    private var tiendas: List<Tienda> = emptyList()

    init {
        cargarTiendasDesdeJSON()
    }

    private fun cargarTiendasDesdeJSON() {
        val context = getApplication<Application>().applicationContext
        val json = context.assets.open("tiendas.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val tipoLista = object : TypeToken<List<Tienda>>() {}.type
        tiendas = gson.fromJson(json, tipoLista)
    }

    fun buscarTiendaCercana(
        componente: String,
        ubicacionActual: LatLng
    ): Tienda? {
        val coincidencias = tiendas.filter { tienda ->
            tienda.equipos.any { it.contains(componente, ignoreCase = true) }
        }

        if (coincidencias.isEmpty()) {
            _resultadoBusqueda.value = "No se encontraron tiendas que vendan '$componente'."
            return null
        }

        val tiendaMasCercana = coincidencias.minByOrNull { tienda ->
            val distancia = calcularDistancia(
                ubicacionActual.latitude,
                ubicacionActual.longitude,
                tienda.lat,
                tienda.lng
            )
            distancia
        }

        tiendaMasCercana?.let {
            val distanciaKm = calcularDistancia(
                ubicacionActual.latitude,
                ubicacionActual.longitude,
                it.lat,
                it.lng
            )
            _resultadoBusqueda.value = "La tienda más cercana es ${it.nombre}, está a ${"%.2f".format(distanciaKm)} km, en ${it.ubi}. Abierto hasta ${it.horario_cierre}. Calificación: ⭐ ${it.valoracion}"
        }

        return tiendaMasCercana
    }

    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val loc1 = Location("").apply {
            latitude = lat1
            longitude = lon1
        }
        val loc2 = Location("").apply {
            latitude = lat2
            longitude = lon2
        }
        return loc1.distanceTo(loc2) / 1000.0 // en km
    }

    fun obtenerTodosLosEquiposUnicos(): List<String> {
        return tiendas.flatMap { it.equipos }
            .map { it.trim() }
            .distinct()
            .sorted()
    }
}
