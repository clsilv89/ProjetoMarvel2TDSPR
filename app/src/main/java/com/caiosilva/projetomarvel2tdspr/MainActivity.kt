package com.caiosilva.projetomarvel2tdspr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caiosilva.projetomarvel2tdspr.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val lista = listComics()
        val adapter: ComicsAdapter = ComicsAdapter()

        adapter.submitList(lista)

        binding.recyclerViewListComics.adapter = adapter
        binding.recyclerViewListComics.layoutManager = LinearLayoutManager(this)
    }

    private fun listComics(): List<ComicBookData> {
        return listOf(
            ComicBookData(
                1,
                "Homem Aranha De Volta Pra Casa",
                "Homem Aranha vai pro Baile com a filha do vilão",
                123
            ),
            ComicBookData(
                2,
                "Vingadores: Ultimato",
                "Os Vingadores vão pro espaço",
                234
            ),
            ComicBookData(
                3,
                "Homem Formiga: Quantunmania",
                "Nesse aparece o vilão da série do Loki",
                345
            ),
            ComicBookData(
                4,
                "Homem de Ferro 3",
                "Tony Stark fala o endereço dele na TV",
                456
            )
        )
    }
}
