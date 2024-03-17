package com.example.testspeedtest

import android.app.AppOpsManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declaring Button from the layout file
        val btn = findViewById<Button>(R.id.btn)

        // Action when the button id clicked
        btn.setOnClickListener {

            // Connectivity Manager
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // Network Capabilities of Active Network
            //val nc = cm.getNetworkCapabilities(cm.activeNetwork)
            val networkCapabilities = connectivityManager.activeNetwork ?: return@setOnClickListener
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return@setOnClickListener

            // DownSpeed in MBPS
            val downSpeed = (activeNetwork.linkDownstreamBandwidthKbps)/1000

            // UpSpeed  in MBPS
            val upSpeed = (activeNetwork.linkUpstreamBandwidthKbps)/1000

            // Toast to Display DownSpeed and UpSpeed
            Toast.makeText(applicationContext,
                "Up Speed: $upSpeed Mbps \nDown Speed: $downSpeed Mbps",
                Toast.LENGTH_LONG).show()
        }
    }
}