package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseFragment
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter

import com.slutsenko.cocktailapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_filter.*


class FilterFragment : BaseFragment<MainViewModel>() {
    override val viewModel: MainViewModel by activityViewModels()
    override val contentLayoutResId: Int = R.layout.fragment_filter

    //private var onFilterResultListener: OnFilterResultListener? = null
    private var alcoholFilter: AlcoholDrinkFilter? = null
    private var categoryFilter: CategoryDrinkFilter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //onFilterResultListener = context as OnFilterResultListener
//        alcoholFilter = arguments?.getSerializable(ALCOHOL_DRINK_FILTER) as AlcoholDrinkFilter?
//        categoryFilter = arguments?.getSerializable(CATEGORY_DRINK_FILTER) as CategoryDrinkFilter?
    }


    override fun configureView(savedInstanceState: Bundle?) {
        initFilters()
        viewModel.cocktailQuantityLiveData.observe(this, Observer {
            btn_apply.text = it.toString()
        })

        super.configureView(savedInstanceState)

        tv_change_alcohol.setOnClickListener { v: View ->
            PopupMenu(requireContext(), v).apply {
                setOnMenuItemClickListener {
                    tv_chosenAlcohol.text = viewModel.setAlcoholFilters(it)
                    true
                }
                inflate(R.menu.menu_alcohol)
                show()
            }
        }

        tv_change_category.setOnClickListener { v: View ->
            PopupMenu(requireContext(), v).apply {
                setOnMenuItemClickListener {
                    tv_chosenCategory.text = viewModel.setCategoryFilter(it)
                    true
                }
                inflate(R.menu.menu_category)
                show()
            }
        }

        toolbar_iv_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_apply.setOnClickListener {
            //onFilterResultListener?.onFilterResult(alcoholFilter, categoryFilter)
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_drop.setOnClickListener {
            viewModel.alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.NON
            viewModel.categoryDrinkFilterLiveData.value = CategoryDrinkFilter.NON
            //viewModel.cocktailDBLiveData?.value = cocktailList
            //onFilterResultListener?.onFilterResult(AlcoholDrinkFilter.NON, CategoryDrinkFilter.NON)
            activity?.supportFragmentManager?.popBackStack()
        }

//        val onAlcoholicRadioButtonClickListener = View.OnClickListener {
//            when (it.id) {
//                R.id.rb_alcoholic -> {
//                    alcoholFilter = AlcoholDrinkFilter.ALCOHOLIC
//                }
//                R.id.rb_non_alcoholic -> {
//                    alcoholFilter = AlcoholDrinkFilter.NON_ALCOHOLIC
//                }
//                R.id.rb_optional -> {
//                    alcoholFilter = AlcoholDrinkFilter.OPTIONAL_ALCOHOL
//                }
//                R.id.rb_non -> {
//                    alcoholFilter = AlcoholDrinkFilter.NON
//                }
//            }
//        }
//        rb_alcoholic.setOnClickListener(onAlcoholicRadioButtonClickListener)
//        rb_non_alcoholic.setOnClickListener(onAlcoholicRadioButtonClickListener)
//        rb_optional.setOnClickListener(onAlcoholicRadioButtonClickListener)

//        rg_category.setOnCheckedChangeListener { _, checkedId ->
//            CategoryDrinkFilter.values().forEach {
//                if (it.key == requireView().findViewById<RadioButton>(checkedId).text) {
//                    categoryFilter = it
//                }
//            }
//        }
//        CategoryDrinkFilter.values().forEach {
//            rg_category.addView(
//                    RadioButton(context).apply {
//                        id = View.generateViewId()
//                        text = it.key
//                    }
//            )
//        }


    }

    private fun initFilters() {
        tv_chosenAlcohol.text = viewModel.alcoholDrinkFilterLiveData.value?.key ?: ""
        tv_chosenCategory.text = viewModel.categoryDrinkFilterLiveData.value?.key ?: ""
    }

//    private fun initFilters() {
//        when (alcoholFilter) {
//            AlcoholDrinkFilter.ALCOHOLIC -> rb_alcoholic.isChecked = true
//            AlcoholDrinkFilter.NON_ALCOHOLIC -> rb_non_alcoholic.isChecked = true
//            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> rb_optional.isChecked = true
//            AlcoholDrinkFilter.NON -> rb_non.isChecked = true
//        }
//    }

    companion object {
        private const val ALCOHOL_DRINK_FILTER = "alcohol_drink_filter"
        private const val CATEGORY_DRINK_FILTER = "category_drink_filter"

        fun newInstance(alcoholDrinkFilter: AlcoholDrinkFilter?, categoryDrinkFilter: CategoryDrinkFilter?): FilterFragment {
            val fragment = FilterFragment()
            val bundle = Bundle()
            bundle.putSerializable(ALCOHOL_DRINK_FILTER, alcoholDrinkFilter)
            bundle.putSerializable(CATEGORY_DRINK_FILTER, categoryDrinkFilter)
            fragment.arguments = bundle
            return fragment
        }
    }


//    interface OnFilterResultListener {
//        fun onFilterResult(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?)
//    }
}