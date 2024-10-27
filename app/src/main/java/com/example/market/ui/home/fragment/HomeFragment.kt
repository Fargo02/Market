package com.example.market.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
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
import com.example.market.ui.home.ui.BannersAdapter
import com.example.market.ui.home.ui.CatalogsAdapter
import com.example.market.ui.home.ui.PromotionsAdapter
import com.example.market.ui.home.ui.PromotionsAdapter.PromotionClickListener
import com.example.market.ui.home.ui.SectionsAdapter
import com.example.market.ui.home.view_model.HomeViewModel
import com.example.market.ui.home.view_model.InfState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    private var section = ArrayList<Section>()
    private var banners = ArrayList<Banner>()
    private var catalogs = ArrayList<Catalog>()
    private var promotions = ArrayList<Promotional>()

    private var sectionsAdapter: SectionsAdapter? = null
    private var bannersAdapter: BannersAdapter? = null
    private var catalogsAdapter: CatalogsAdapter? = null
    private var promotionsAdapter: PromotionsAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInf()

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

    override fun onDestroyView() {
        super.onDestroyView()
        sectionsAdapter = null
        bannersAdapter = null
        catalogsAdapter = null
        promotionsAdapter = null
    }
}