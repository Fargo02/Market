package com.example.market.ui.home.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.INPUT_METHOD_SERVICE
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.market.utils.BindingFragment
import com.example.market.R
import com.example.market.databinding.FragmentHomeBinding
import com.example.market.domain.model.Banner
import com.example.market.domain.model.Catalog
import com.example.market.domain.model.Promotional
import com.example.market.domain.model.Section
import com.example.market.domain.search.model.Address
import com.example.market.ui.home.ui.banner.BannersAdapter
import com.example.market.ui.home.ui.catalog.CatalogsAdapter
import com.example.market.ui.home.ui.location.LocationsAdapter
import com.example.market.ui.home.ui.promotion.PromotionsAdapter
import com.example.market.ui.home.ui.promotion.PromotionsAdapter.PromotionClickListener
import com.example.market.ui.home.ui.section.SectionsAdapter
import com.example.market.ui.home.view_model.HomeViewModel
import com.example.market.ui.home.view_model.InfState
import com.example.market.ui.home.view_model.SearchState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    private var section = ArrayList<Section>()
    private var banners = ArrayList<Banner>()
    private var catalogs = ArrayList<Catalog>()
    private var promotions = ArrayList<Promotional>()
    private var locations = ArrayList<Address>()

    private var sectionsAdapter: SectionsAdapter? = null
    private var bannersAdapter: BannersAdapter? = null
    private var catalogsAdapter: CatalogsAdapter? = null
    private var promotionsAdapter: PromotionsAdapter? = null
    private var locationsAdapter: LocationsAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInf()

        val bottomSheetContainer = binding.bottomSheet

        bottomSheetContainer.setOnClickListener {}

        val overlay = binding.overlay

        val bottomSheetBehavior = bottomSheetContainer.let {
            BottomSheetBehavior.from(it).apply {
                state = BottomSheetBehavior.STATE_HIDDEN
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {

                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        overlay.isVisible = false
                    }
                    else -> {
                        overlay.isVisible = true
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) { overlay.alpha = slideOffset }
        })

        binding.address.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.addressArrow.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        locationsAdapter = LocationsAdapter{
            binding.address.text = it.street
        }
        sectionsAdapter = SectionsAdapter { println("click to section item") }
        bannersAdapter = BannersAdapter { println("click to banner item") }
        catalogsAdapter = CatalogsAdapter { println("click to catalog item") }
        promotionsAdapter = PromotionsAdapter(object : PromotionClickListener {
            override fun onPlusClicked(model: Promotional) {

                println("click to +")
                //viewModel.addProduct(model)
            }

            override fun onMinusClicked(model: Promotional) {
                println("click to -")
                //viewModel.removeProduct(model)
            }
        })

        binding.buttonLike.setOnClickListener { println("click to button like") }
        binding.seeAllLayout.setOnClickListener{ println("click to button see all") }

        val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)

        binding.navImage.setOnClickListener {
            drawerLayout?.openDrawer(GravityCompat.START)
        }

        locationsAdapter?.locations = locations
        binding.locationList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.locationList.adapter = locationsAdapter

        sectionsAdapter?.sections = section
        binding.sectionsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.sectionsList.adapter = sectionsAdapter

        bannersAdapter?.banners = banners
        binding.bannersList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bannersList.adapter = bannersAdapter

        promotionsAdapter?.promotions = promotions
        binding.promotionalList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.promotionalList.adapter = promotionsAdapter

        catalogsAdapter?.catalogs = catalogs
        binding.catalogList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.catalogList.adapter = catalogsAdapter

        viewModel.observeInformationListener().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.observeSearchStateListener().observe(viewLifecycleOwner) {
            renderSearch(it)
        }

        binding.close.setOnClickListener {
            val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(binding.inputEditText.windowToken, 0)
            binding.bottomSheetEditText.setText(getString(R.string.empty_string))
            binding.bottomSheetEditText.clearFocus()
        }

        binding.currentGeolocation.setOnClickListener {  }

        val textWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) { }
        }
        textWatcher.let { binding.bottomSheetEditText.addTextChangedListener(it) }

    }

    private fun showSection(section: List<Section>) {
        sectionsAdapter?.sections?.clear()
        sectionsAdapter?.sections?.addAll(section)
        sectionsAdapter?.notifyDataSetChanged()
    }

    private fun showBanners(banners: List<Banner>) {
        bannersAdapter?.banners?.clear()
        bannersAdapter?.banners?.addAll(banners)
        bannersAdapter?.notifyDataSetChanged()
    }

    private fun showPromotions(promotions: List<Promotional>) {
        promotionsAdapter?.promotions?.clear()
        promotionsAdapter?.promotions?.addAll(promotions)
        promotionsAdapter?.notifyDataSetChanged()
    }


    private fun showCatalogs(catalogs: List<Catalog>) {
        catalogsAdapter?.catalogs?.clear()
        catalogsAdapter?.catalogs?.addAll(catalogs)
        catalogsAdapter?.notifyDataSetChanged()
    }

    private fun render(state: InfState) {
        when (state) {
            is InfState.Content -> {
                showCatalogs(state.catalogs)
                showPromotions(state.promotions)
                showBanners(state.banners)
                showSection(state.sections)
            }
        }
    }

    private fun showLocations(address: List<Address>) {
        locationsAdapter?.locations?.clear()
        locationsAdapter?.locations?.addAll(address)
        locationsAdapter?.notifyDataSetChanged()
    }

    private fun showEmptySearch() {
        locationsAdapter?.locations?.clear()
        locationsAdapter?.notifyDataSetChanged()
    }

    private fun showError() {
        locationsAdapter?.locations?.clear()
        locationsAdapter?.notifyDataSetChanged()
        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
    }

    private fun renderSearch(state: SearchState) {
        when(state) {
            is SearchState.Content -> showLocations(state.addresses)
            is SearchState.Empty -> showEmptySearch()
            is SearchState.Error -> showError()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        sectionsAdapter = null
        bannersAdapter = null
        catalogsAdapter = null
        promotionsAdapter = null
        locationsAdapter = null
    }
}