package com.caiosilva.projetomarvel2tdspr

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caiosilva.projetomarvel2tdspr.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    val retrofit = RetrofitClient().client("https://jsonplaceholder.typicode.com").create(API::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        setupRecyclerView()
        apiCall()
    }

    private fun apiCall() {
        val getPosts = retrofit.getPosts()

        getPosts.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.let {
                    setupRecyclerView(it)
                }

                println(response.body())
            }

            override fun onFailure(call: Call<List<Posts>>, throwable: Throwable) {
                println(throwable)
            }
        })
    }

    private fun sendPost(posts: Posts) {
        val newPost = NewPost(
            title = posts.title,
            body = posts.body,
            userId = posts.userId
        )
        val sendPosts = retrofit.submitPosts(newPost)

        sendPosts.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                println("Postagem recebida: ${response.body()}")
            }

            override fun onFailure(call: Call<Posts>, throwable: Throwable) {
                println(throwable)
            }
        })
    }

    private fun setupRecyclerView(posts: List<Posts>) {
        val lista = listComics()
        val adapter: ComicsAdapter = ComicsAdapter()

//        adapter.submitList(lista)
        adapter.submitList(posts)
        adapter.onPostClick = {
            sendPost(it)
        }
        adapter.onClick = {
            openIntent(it)
        }

        binding.recyclerViewListComics.adapter = adapter
        binding.recyclerViewListComics.layoutManager = LinearLayoutManager(this)
    }

    private fun openIntent(comicBookcData: ComicBookData) {
        val comicBookDataString = comicBookcData.toJsonString()
        val intent = Intent(this, SecondActivity::class.java)
            .putExtra("COMIC_BOOK_DATA", comicBookDataString)
//            .putExtra("IMAGE_URL", comicBookcData.imageUrl)
//            .putExtra("EXTRA_INT", 3)
        startActivity(intent)
    }

    private fun listComics(): List<ComicBookData> {
        return listOf(
            ComicBookData(
                1,
                "Homem Aranha De Volta Pra Casa",
                "Homem Aranha vai pro Baile com a filha do vilão",
                123,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/1600px-Marvel_Logo.svg.png"
            ),
            ComicBookData(
                2,
                "Vingadores: Ultimato",
                "Os Vingadores vão pro espaço",
                234,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/1600px-Marvel_Logo.svg.png"
            ),
            ComicBookData(
                3,
                "Homem Formiga: Quantunmania",
                "Nesse aparece o vilão da série do Loki",
                345,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/1600px-Marvel_Logo.svg.png"
            ),
            ComicBookData(
                4,
                "Homem de Ferro 3",
                "Tony Stark fala o endereço dele na TV",
                456,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/1600px-Marvel_Logo.svg.png"
            )
        )
    }
}
