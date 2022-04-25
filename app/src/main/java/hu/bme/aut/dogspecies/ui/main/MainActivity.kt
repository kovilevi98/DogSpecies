package hu.bme.aut.dogspecies.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hu.bme.aut.dogspecies.R
import hu.bme.aut.dogspecies.di.NetworkModule
import hu.bme.aut.dogspecies.ui.about.AboutActivity
import hu.bme.aut.dogspecies.ui.details.DetailsActivity
import retrofit2.Retrofit

import javax.inject.Inject




class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var networkModule: NetworkModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detailsButton = findViewById<Button>(R.id.details)

        detailsButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        val aboutButton = findViewById<Button>(R.id.about)

        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

}