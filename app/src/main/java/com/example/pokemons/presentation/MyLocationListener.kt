package com.example.pokemons.presentation

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

class MyLocationListener : LocationListener {

    override fun onLocationChanged(location: Location) {
        // Обработка обновлений местоположения пользователя
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // Обработка изменения статуса провайдера геолокации
    }

    override fun onProviderEnabled(provider: String) {
        // Обработка включения провайдера геолокации
    }

    override fun onProviderDisabled(provider: String) {
        // Обработка отключения провайдера геолокации
    }
}