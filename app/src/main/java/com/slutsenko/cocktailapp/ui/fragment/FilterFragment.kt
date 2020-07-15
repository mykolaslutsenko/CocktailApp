package com.slutsenko.cocktailapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_filter.*


class FilterFragment : BaseFragment<MainViewModel>() {
    override val viewModel: MainViewModel by activityViewModels()
    override val contentLayoutResId: Int = R.layout.fragment_filter

    override fun configureView(savedInstanceState: Bundle?) {
        initFilters()
        super.configureView(savedInstanceState)

        tv_change_alcohol.setOnClickListener { v: View ->
            PopupMenu(requireContext(), v).apply {
                setOnMenuItemClickListener {
                    viewModel.setAlcoholFilters(it)
                    tv_chosenAlcohol.text = viewModel.alcoholDrinkFilterLiveData.value?.key
                    true
                }
                inflate(R.menu.menu_alcohol)
                show()
            }
        }

        tv_change_category.setOnClickListener { v: View ->
            PopupMenu(requireContext(), v).apply {
                setOnMenuItemClickListener {
                    viewModel.setCategoryFilter(it)
                    tv_chosenCategory.text = viewModel.categoryDrinkFilterLiveData.value?.key
                    true
                }
                inflate(R.menu.menu_category)
                show()
            }
        }

        viewModel.cocktailQuantityLiveData.observe(this, Observer {
            btn_apply.text = it.toString()
        })

        toolbar_iv_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_apply.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_drop.setOnClickListener {
           viewModel.dropFilters()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initFilters() {
        tv_chosenAlcohol.text = viewModel.alcoholDrinkFilterLiveData.value?.key ?: ""
        tv_chosenCategory.text = viewModel.categoryDrinkFilterLiveData.value?.key ?: ""
    }

    companion object {
        fun newInstance(): FilterFragment {
            return FilterFragment()
        }
    }
}