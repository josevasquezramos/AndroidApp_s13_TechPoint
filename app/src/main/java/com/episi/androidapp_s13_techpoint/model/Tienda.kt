package com.episi.androidapp_s13_techpoint.model

data class Tienda(
    val nombre: String,
    val lat: Double,
    val lng: Double,
    val ubi: String,
    val equipos: List<String>,
    val horario_cierre: String,
    val valoracion: Double
)
