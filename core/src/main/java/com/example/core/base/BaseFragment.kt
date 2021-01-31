package com.example.core.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T

    abstract val layoutRes: Int
    abstract fun observeViewModel()
    //private lateinit var dialog: ACProgressFlower

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

//        dialog = ACProgressFlower.Builder(requireContext())
//            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//            .themeColor(Color.WHITE)
//            .text(getString(R.string.loading))
//            .fadeColor(Color.DKGRAY).build()
    }

    fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    fun goBack() {
        findNavController().navigateUp()
    }

    fun showSnackMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
       // dialog.show()
    }

    fun hideLoading() {
        //dialog.dismiss()
    }

    protected fun setBackPressedListener(actionToPerform: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                actionToPerform()
            }
        })
    }
}