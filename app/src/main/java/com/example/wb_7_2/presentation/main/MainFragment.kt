package com.example.wb_7_2.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wb_7_2.R
import com.example.wb_7_2.utils.Resource
import com.example.wb_7_2.appComponent
import com.example.wb_7_2.databinding.FragmentMainBinding
import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import javax.inject.Inject


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    private var adapter: MainAdapter? = null

    private var loadingPermission = true

    private val vm: MainViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupObservers()
    }

    private fun setupObservers() {
        vm.superHeroesList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
            binding?.mainProgressBar?.visibility = View.GONE
        })

        vm.loadingPermission.observe(viewLifecycleOwner, Observer {
            loadingPermission = it
        })

        if (loadingPermission) {

            vm.getSuperHeroes().observe(viewLifecycleOwner, Observer { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data != null) {
                            vm.setSuperHeroesList(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                        binding?.mainProgressBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding?.mainProgressBar?.visibility = View.VISIBLE
                    }
                }
            })

            vm.changeToFalseLoadingPermission()
        }
    }

    private fun navigateToDetails(hero: SuperHeroModelDomain) {

        findNavController().navigate(
            R.id.action_mainFragment_to_detailsFragment,
            getHeroBundle(hero)
        )
    }

    private fun getHeroBundle(hero: SuperHeroModelDomain): Bundle {
        val heroInfoBundle = Bundle()
        heroInfoBundle.putString("name", hero.name)
        heroInfoBundle.putString("img", hero.image)
        heroInfoBundle.putString("publisher", hero.publisher)
        heroInfoBundle.putString("powers", hero.powers)
        heroInfoBundle.putString("appears_in", hero.appearsIn)

        return heroInfoBundle
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter { navigateToDetails(it) }
        binding?.apply {
            mainRecyclerView.adapter = adapter
            mainRecyclerView.layoutManager = LinearLayoutManager(context)
            mainRecyclerView.itemAnimator = null
            val dividerItemDecoration = DividerItemDecoration(
                mainRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
            ContextCompat.getDrawable(context!!, R.drawable.divider_item)?.let {
                dividerItemDecoration.setDrawable(
                    it
                )
            }
            mainRecyclerView.addItemDecoration(
                dividerItemDecoration
            )
        }
    }

    override fun onDestroyView() {
        binding = null
        adapter = null
        super.onDestroyView()
    }

}