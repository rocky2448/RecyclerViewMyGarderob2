package com.example.recyclerviewmygarderob2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmygarderob.R

class GarderobActivity : AppCompatActivity() {

    private val clothesss = mutableListOf(
        Clothes("Ботинки", "Классические, мужские, чёрные, из натуральной кожи", R.drawable.botinki_classic),
        Clothes("Худи", "Трикотажное, чёрное с эмблемой android, с капюшоном, без замка", R.drawable.hudi_black_android),
        Clothes("Худи", "Трикотажное, тёмно-синего цвета с капюшоном, на замке", R.drawable.hudi_blue_dark),
        Clothes("Худи", "Трикотажное, оранжевое с эмблемой marvel, с капюшоном, без замка", R.drawable.hudi_orange_marvel),
        Clothes("Кепка", "Чёрная с эмблемой автопроизводителя Mazda", R.drawable.kepka_mazda),
        Clothes("Кепка", "Чёрная с эмблемой автопроизводителя Toyota", R.drawable.kepka_toyota),
        Clothes("Кросовки", "Утеплённые, зимние, чёрные с оранжевыми вставками", R.drawable.krosovki_black_orange),
        Clothes("Кросовки", "Спортивные, лёгкие, летние, Nike", R.drawable.krosovki_blue_sport),
        Clothes("Куртка", "Чёрная, демисезонная, Adidas с тремя полосами на рукавах", R.drawable.kurtka_adidas),
        Clothes("Брюки", "Бежевые, классические, мужские", R.drawable.pants_bejevyi),
        Clothes("Брюки", "Чёрные, полуспортивные, мужские", R.drawable.pants_black),
        Clothes("Брюки", "Синие, классические, мужские", R.drawable.pants_blue),
        Clothes("Рубашка", "Чёрная, из серии люди в чёрном", R.drawable.rubashka_black),
        Clothes("Рубашка", "Синяя, под джинсу, мужская, с карманом на груди", R.drawable.rubashka_blue),
        Clothes("Рубашка", "Белая в чёрную клетку, чёткая сетка", R.drawable.rubashka_kletka),
        Clothes("Рубашка", "Красная, для брутальных пацанов", R.drawable.rubashka_red),
        Clothes("Шорты", "Чёрные, спортивные, подходят для занятия спортом", R.drawable.shorty_black),
        Clothes("Шорты", "Голубые, быстросохнущие, подходят для плавания", R.drawable.shorty_blue),
        Clothes("Шорты", "Серые, мягкие, идеально для домашнего ношения", R.drawable.shorty_gray),
        Clothes("Футболка", "Зелёная, однотонная, х/б 95%, латекс 5%", R.drawable.tishka_green),
        Clothes("Футболка", "Оранжевая, однотонная, унисекс х/б 95%, латекс 5%", R.drawable.tishka_orange),
        Clothes("Футболка", "Белая, однотонная, х/б 100%, на Sensation в самый раз", R.drawable.tishka_white)
        )
    private lateinit var toolbarMain: Toolbar
    private lateinit var recyclerViewRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_garderob)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        recyclerViewRV = findViewById(R.id.recyclerViewRV)

        setSupportActionBar(toolbarMain)
        title = "Мой гардероб"
        toolbarMain.subtitle = "by Rocky"
        toolbarMain.setLogo(R.drawable.ic_clothes)

        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(clothesss)
        recyclerViewRV.adapter = adapter
        recyclerViewRV.setHasFixedSize(true)
        adapter.setOnClothesClickListener(object :
        CustomAdapter.OnClothesClickListener {
            override fun OnClothesClick(clothes: Clothes, position: Int) {
                val intent = Intent(this@GarderobActivity, DetailActivity::class.java)
                intent.putExtra("clothes", clothes)
                intent.putExtra("position", position)
                startActivity(intent)


            }

        })

        var updateClothes: Clothes? = null
        if (intent.hasExtra("clothes") && intent.hasExtra("position")) {
            updateClothes = intent.getSerializableExtra("clothes") as Clothes
            val position = intent.getIntExtra("position", 0)
            clothesss[position] = updateClothes
            recyclerViewRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }
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