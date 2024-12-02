package com.example.recyclerviewmygarderob2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewmygarderob.R

class DetailActivity : AppCompatActivity() {

    private var clothes: Clothes? = null
    private var position: Int = 0
    private lateinit var layoutLL: LinearLayout
    private lateinit var toolbarMain: Toolbar
    private lateinit var imageClothesIV: ImageView
    private lateinit var editNameTV: TextView
    private lateinit var editDescriptionTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)


        layoutLL = findViewById(R.id.layoutLL)
        toolbarMain = findViewById(R.id.toolbarMain)
        imageClothesIV = findViewById(R.id.imageClothesIV)
        editNameTV = findViewById(R.id.editNameTV)
        editDescriptionTV = findViewById(R.id.editDescriptionTV)

        setSupportActionBar(toolbarMain)
        title = "Мой гардероб"
        toolbarMain.subtitle = "by Rocky"
        //toolbarMain.setLogo(R.drawable.ic_clothes)

//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        toolbarMain.setNavigationOnClickListener {
//            onBackPressed()
//        }

        if (intent.hasExtra("clothes")) {
            clothes = intent.getSerializableExtra("clothes") as Clothes
        }
        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 0)
        }

        if (clothes != null) {
            editNameTV.text = clothes!!.name
            editDescriptionTV.text = clothes!!.description
            imageClothesIV.setImageResource(clothes!!.image)
        }

        layoutLL.setOnLongClickListener {
            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialog.setView(dialogView)
            val editName = dialogView.findViewById(R.id.updateNameET) as EditText
            val editDescription = dialogView.findViewById(R.id.updateDescriptionET) as EditText
            dialog.setTitle("Обновить запись")
            dialog.setMessage("Введите данные ниже: ")
            dialog.setPositiveButton("Обновить") { _, _ ->
                editNameTV.text = editName.text.toString()
                editDescriptionTV.text = editDescription.text.toString()
                clothes = Clothes(
                    editNameTV.text.toString(),
                    editDescription.text.toString(),
                    clothes!!.image
                )
            }
            dialog.setNegativeButton("Отмена") { _, _ -> }
            dialog.create().show()
            false
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, GarderobActivity::class.java)
        intent.putExtra("clothes", clothes)
        intent.putExtra("position", position)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}
