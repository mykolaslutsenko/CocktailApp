package com.slutsenko.cocktailapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    //protected open fun configureDataBinding(binding: DataBinding) {}

    protected abstract val contentLayoutResId: Int
    //protected open lateinit var dataBinding: DataBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

//        dataBinding = DataBindingUtil.inflate(layoutInflater,contentLayoutResId, container, false)
//        dataBinding.setLifecycleOwner(viewLifecycleOwner)
//        return dataBinding.root
         return inflater.inflate(contentLayoutResId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureView(savedInstanceState)
//        configureDataBinding(dataBinding)
//        configureView(savedInstanceState)
//        configureObserver()
        super.onViewCreated(view, savedInstanceState)
    }

//     fun configureObserver(){
//        //
//    }

    protected open fun configureView(savedInstanceState: Bundle?) {
        //stub
    }


}