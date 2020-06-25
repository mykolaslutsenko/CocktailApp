package com.slutsenko.cocktailapp.ui.fragment

import android.content.Context
import android.os.Bundle
import com.slutsenko.cocktailapp.BaseFragment
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import kotlinx.android.synthetic.main.fragment_filter.*


class FilterFragment : BaseFragment() {
    override val contentLayoutResId: Int = R.layout.fragment_filter
    private var onFilterResultListener: OnFilterResultListener? = null
    private var alcoholFilter: AlcoholDrinkFilter? = null
    private var categoryFilter: CategoryDrinkFilter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFilterResultListener = context as OnFilterResultListener
        alcoholFilter = arguments?.getSerializable(ALCOHOL_DRINK_FILTER) as AlcoholDrinkFilter?
        categoryFilter = arguments?.getSerializable(CATEGORY_DRINK_FILTER) as CategoryDrinkFilter?


    }

    override fun configureView(savedInstanceState: Bundle?) {
        super.configureView(savedInstanceState)
        initFilter()
        toolbar_iv_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_apply.setOnClickListener {
            onFilterResultListener?.onFilterApply(alcoholFilter, categoryFilter)
            activity?.supportFragmentManager?.popBackStack()
        }
        btn_drop.setOnClickListener {
            onFilterResultListener?.onFilterReset(AlcoholDrinkFilter.NON, CategoryDrinkFilter.NON)
            activity?.supportFragmentManager?.popBackStack()
        }
    }

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

    private fun initFilter() {
        when (alcoholFilter) {
            AlcoholDrinkFilter.ALCOHOLIC -> {
                rb_alcoholic.isChecked = true
                //alcoholFilter = AlcoholDrinkFilter.ALCOHOLIC
            }
            AlcoholDrinkFilter.NON_ALCOHOLIC -> {
                rb_non_alcoholic.isChecked = true
               // alcoholFilter = AlcoholDrinkFilter.NON_ALCOHOLIC
            }
            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> {
                rb_optional.isChecked = true
                //alcoholFilter = AlcoholDrinkFilter.OPTIONAL_ALCOHOL
            }
            else -> {
                rb_non.isChecked = true
               // alcoholFilter = AlcoholDrinkFilter.NON
            }
        }
    }


    interface OnFilterResultListener {
        fun onFilterApply(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?)
        fun onFilterReset(alcoholFilter: AlcoholDrinkFilter?, categoryFilter: CategoryDrinkFilter?)
    }

}