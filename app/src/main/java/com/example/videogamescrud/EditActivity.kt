package com.example.videogamescrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.videogamescrud.databinding.ActivityEditBinding
import com.example.videogamescrud.db.DBGames
import com.example.videogamescrud.model.Game

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    private lateinit var dbGames: DBGames
    var game: Game? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = resources.getStringArray(R.array.options)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, options)

        if(savedInstanceState == null){
            val bundle = intent.extras
            if(bundle != null){
                id = bundle.getInt("ID", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbGames = DBGames(this)

        game = dbGames.getGame(id)

        if(game != null){
            with(binding){
                tietTitulo.setText(game?.title)
                ddGenre.setText(game?.genre)
                tietDeveloper.setText(game?.developer)
                binding.ddGenre.setAdapter(arrayAdapter)
            }
        }
    }

    fun click(view: View) {
        with(binding){
            if(!tietTitulo.text.toString().isEmpty() && !ddGenre.text.toString().isEmpty() && !tietDeveloper.text.toString().isEmpty()){
                if(dbGames.updateGame(id, tietTitulo.text.toString(), ddGenre.text.toString(), tietDeveloper.text.toString())){
                    Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                    intent.putExtra("ID", id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditActivity, "Ningún campo puede quedar vacío", Toast.LENGTH_LONG).show()
            }
        }
    }
}