package com.example.market.ui.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market.R
import com.example.market.domain.model.Banner
import com.example.market.domain.model.Catalog
import com.example.market.domain.model.Promotional
import com.example.market.domain.model.Section
import com.example.market.domain.search.LocationSearchInteractor
import com.example.market.domain.search.model.Address
import com.example.market.utils.debounce
import kotlinx.coroutines.launch

class HomeViewModel(
    private val locationSearchInteractor: LocationSearchInteractor
): ViewModel() {

    private val token = "Token 9b68276ac74327d5d2075270d1d1e16c49ab9360"

    private var latestSearchText: String? = null

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
        Catalog("Наборы", R.drawable.risotto, R.color.set_color_bgr),
        Catalog("Пицца", R.drawable.salad, R.color.pizza_color_bgr),
        Catalog("Паста", R.drawable.risotto, R.color.pasta_color_bgr),
        Catalog("Ризотто", R.drawable.salad, R.color.risotto_color_bgr),
        Catalog("Салаты", R.drawable.risotto, R.color.salad_color_bgr),
        Catalog("Полу фабрикаты", R.drawable.salad, R.color.semi_finished_color_bgr_),
        Catalog("Десерты", R.drawable.risotto, R.color.dessert_color_bgr),
        Catalog("Добавки к блюдам", R.drawable.salad, R.color.additives_color_bgr),
        Catalog("Напитки", R.drawable.risotto, R.color.drink_color_bgr),
    )


    private val trackSearchDebounce = debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
        latestSearchText?.let { requestToServer(it) }
    }
    private val informationListener = MutableLiveData<InfState>()
    fun observeInformationListener() : LiveData<InfState> = informationListener

    private val searchStateListener = MutableLiveData<SearchState>()
    fun observeSearchStateListener() : LiveData<SearchState> = searchStateListener

    fun searchDebounce(changedText: String) {
        if(changedText == "") {
            renderState(SearchState.Empty)
            return
        }
        if (latestSearchText != changedText) {
            latestSearchText = changedText
            trackSearchDebounce(changedText)
        }
    }

    private fun requestToServer(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            viewModelScope.launch {
                locationSearchInteractor
                    .getAddress(token, newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundAddress: List<Address>?, errorMessage: String?) {
        val addresses = mutableListOf<Address>()

        if (foundAddress != null) {
            addresses.addAll(foundAddress)
        }

        when {
            errorMessage != null -> {
                renderState(SearchState.Error)
            }
            addresses.isEmpty() -> {
                renderState(SearchState.Empty)
            }
            else -> {
                renderState(
                    SearchState.Content(addresses)
                )
            }
        }
    }

    private fun renderState(state: SearchState) {
        searchStateListener.postValue(state)
    }

    fun getInf() {
        informationListener.postValue(InfState
            .Content(
                sections,
                banners,
                promotions,
                catalogs
        ))
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 300L
    }
}