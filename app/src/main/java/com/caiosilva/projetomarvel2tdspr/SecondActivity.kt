package com.caiosilva.projetomarvel2tdspr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
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
        openGallery()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            val uri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            binding.imageView.setImageBitmap(bitmap)
        }
    }

    private fun getExtras() {
        val comicBookData =
            intent.getStringExtra("COMIC_BOOK_DATA").orEmpty().fromJson(ComicBookData::class.java)

        binding.textViewDescription.text = comicBookData.description
        binding.textViewDescription.setOnClickListener {
            openUrl(comicBookData.imageUrl)
        }

        binding.textViewDescription.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                shareUrl(comicBookData.imageUrl)

                return true
            }
        })
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        binding.textViewAbrirGaleria.setOnClickListener {
            startActivityForResult(intent, 1001)
        }
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