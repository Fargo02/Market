package com.example.market.ui.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.market.R
import com.example.market.domain.model.Banner
import com.example.market.domain.model.Catalog
import com.example.market.domain.model.Promotional
import com.example.market.domain.model.Section

class HomeViewModel : ViewModel() {

    private val sections = listOf(
        Section("Летний пикник", R.drawable.summer_picnic),
        Section("Летний обед", R.drawable.summer_picnic),
        Section("На завтрак", R.drawable.summer_picnic),
        Section("На ужин", R.drawable.summer_picnic),
        Section("Летний пикник", R.drawable.summer_picnic),
        Section("Летний обед", R.drawable.summer_picnic),
        Section("На завтрак", R.drawable.summer_picnic),
    )

    private val banners = listOf(
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
        Banner("Скидки  20%", "В честь открытия", R.drawable.banner),
    )
    
    private val promotions = listOf(
        Promotional(
            name = "Черные спагетти с морепродуктам (большая порция)",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Казаречче с индейкой и томатами ",
            cover = R.drawable.risotto,
            discount = "-25%",
            isNew = false,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Равиоли с грибами",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Черные спагетти с морепродуктам (большая порция)",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Черные спагетти с морепродуктам (большая порция)",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Черные спагетти с морепродуктам (большая порция)",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
        Promotional(
            name = "Черные спагетти с морепродуктам (большая порция)",
            cover = R.drawable.salad,
            discount = "-35%",
            isNew = true,
            wight = "230 г",
            lastCoast = "360 ₽",
            newCoast = "200 ₽"
        ),
    )

    private val catalogs = listOf(
        Catalog("Наборы", R.drawable.sets, R.color.set_color_bgr),
        Catalog("Пицца", R.drawable.pizza, R.color.pizza_color_bgr),
        Catalog("Паста", R.drawable.pasta, R.color.pasta_color_bgr),
        Catalog("Ризотто", R.drawable.risotto, R.color.risotto_color_bgr),
        Catalog("Салаты", R.drawable.salad, R.color.salad_color_bgr),
        Catalog("Полу фабрикаты", R.drawable.semi_finished, R.color.semi_finished_color_bgr_),
        Catalog("Десерты", R.drawable.dessert, R.color.dessert_color_bgr),
        Catalog("Добавки к блюдам", R.drawable.additives, R.color.additives_color_bgr),
        Catalog("Напитки", R.drawable.drinks, R.color.drink_color_bgr),
    )

    private val informationListener = MutableLiveData<InfState>()
    fun observeInformationListener() : LiveData<InfState> = informationListener


    fun getInf() {
        informationListener.postValue(InfState
            .Content(
                sections,
                banners,
                promotions,
                catalogs
        ))
    }
}