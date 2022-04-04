package hu.bme.aut.dogspecies.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hu.bme.aut.dogspecies.R
import hu.bme.aut.dogspecies.ui.about.AboutActivity
import hu.bme.aut.dogspecies.ui.details.DetailsActivity

class MainActivity : AppCompatActivity() {
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