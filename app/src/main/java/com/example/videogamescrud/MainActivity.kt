package com.example.videogamescrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.videogamescrud.adapter.GamesAdapter
import com.example.videogamescrud.databinding.ActivityMainBinding
import com.example.videogamescrud.db.DBGames
import com.example.videogamescrud.model.Game

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listGames: ArrayList<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbGames = DBGames(this)

        listGames = dbGames.getGames()

        if(listGames.size == 0) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE


        val gamesAdapter = GamesAdapter(this, listGames)

        binding.lvGames.adapter = gamesAdapter

        binding.lvGames.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ID", l.toInt())

            startActivity(intent)
            finish()
        }

    }

    fun click(view: View) {
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }
}