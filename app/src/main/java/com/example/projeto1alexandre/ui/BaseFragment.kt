package com.example.projeto1alexandre.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base Fragment to inherit from.
 * All common code and abstraction goes here.
 */
abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {
    private var _viewBinding: viewBinding? = null
    val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val inflatedBinding = inflateView(inflater, container)
        _viewBinding = inflatedBinding
        return inflatedBinding.root
    }

    abstract fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): viewBinding

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
