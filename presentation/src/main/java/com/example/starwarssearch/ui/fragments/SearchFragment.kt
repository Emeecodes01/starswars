package com.example.starwarssearch.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.states.StarWarResource
import com.example.core.utils.itemdecorators.VerticalListItemDecorator
import com.example.starwarssearch.R
import com.example.starwarssearch.databinding.FragmentSearchBinding
import com.example.starwarssearch.ui.adapters.CharactersAdapter
import com.example.starwarssearch.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.appcompat.queryTextChanges


@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding>() {

    private val searchFragmentViewModel: SearchFragmentViewModel by viewModels()
    private val charactersAdapter: CharactersAdapter by lazy { CharactersAdapter() }

    private var uiStateJob: Job? = null

    override val layoutRes: Int
        get() = R.layout.fragment_search



    override fun observeViewModel() {
        with(searchFragmentViewModel.characters) {
            uiStateJob = lifecycleScope.launchWhenStarted {
                collect {
                    when(it) {
                        is StarWarResource.Success -> {
                            it.data?.let { characters ->
                                charactersAdapter.submitList(characters)
                            }
                        }
                        is StarWarResource.Loading -> {

                        }
                        is StarWarResource.Error -> TODO()
                    }
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        initViews()
    }

    private fun initViews() {
        with(binding.charactersRv) {
            itemAnimator = SlideInDownAnimator().apply {
                addDuration = 300
                removeDuration = 300
            }
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalListItemDecorator(resources.getDimension(R.dimen.compact_padding).toInt()))
            adapter = charactersAdapter
        }

    }


    private fun initSearchView() {
        binding.characterSearch.queryTextChanges()
            .debounce(400)
            .filter { it.isNotEmpty() }
            .mapLatest {
                searchFragmentViewModel.searchCharacter(it.toString())
            }
            .catch {  err ->
                showError(err.message)
            }
    }


    private fun showError(message: String?) {

    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

}