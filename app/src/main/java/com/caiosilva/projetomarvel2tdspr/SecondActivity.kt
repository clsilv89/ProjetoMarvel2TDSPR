package com.caiosilva.projetomarvel2tdspr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.caiosilva.projetomarvel2tdspr.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()
    }

    private fun getExtras() {
        val stringExtra = intent.getStringExtra("IMAGE_URL").orEmpty()

        binding.textViewDescription.text = stringExtra
        binding.textViewDescription.setOnClickListener {
            openUrl(stringExtra)
        }

        binding.textViewDescription.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                shareUrl(stringExtra)

                return true
            }
        })
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun shareUrl(url: String) {
        val intent = Intent(Intent.ACTION_SEND)
            .putExtra("EXTRA_URL", Uri.parse(url))
        intent.setType("text/plain")

        startActivity(Intent.createChooser(intent, "Compartilhar URL"))
    }
}