package com.example.videogamescrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.videogamescrud.databinding.ActivityInsertBinding
import com.example.videogamescrud.db.DBGames

class InsertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get reference to the string array that we just created
        val options = resources.getStringArray(R.array.options)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, options)
        // set adapter to the autocomplete tv to the arrayAdapter
        binding.ddGenre.setAdapter(arrayAdapter)
    }

    fun click(view: View) {

        val dbGames = DBGames(this)

        with(binding){

            if(!tietTitulo.text.toString().isEmpty() && !ddGenre.text.toString().isEmpty() && !tietDeveloper.text.toString().isEmpty()){
                val id = dbGames.insertGame(tietTitulo.text.toString(), ddGenre.text.toString(), tietDeveloper.text.toString())

                if(id > 0) { //el registro se insertó correctamente
                    Toast.makeText(this@InsertActivity, "Registro guardado exitosamente", Toast.LENGTH_LONG).show()

                    //Reiniciamos las cajas de texto
                    tietTitulo.setText("")
                    ddGenre.setText("")
                    tietDeveloper.setText("")
                    tietTitulo.requestFocus()
                }else{
                    Toast.makeText(this@InsertActivity, "Error al guardar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_LONG).show()

                //Para mandar un error en una caja de texto especíica
                //tietTitulo.text = "Por favor agrega un título"
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}