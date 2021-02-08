package com.example.starwarssearch.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.BaseFragment
import com.example.core.states.StarWarResource
import com.example.core.utils.SingleArg
import com.example.core.utils.extensions.gone
import com.example.core.utils.extensions.visible
import com.example.core.utils.itemdecorators.VerticalListItemDecorator
import com.example.core.utils.scroll.EndlessRecyclerViewScrollListener
import com.example.starwarssearch.R
import com.example.starwarssearch.databinding.FragmentSearchBinding
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.SearchAdapterItem
import com.example.starwarssearch.ui.adapters.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.appcompat.queryTextChanges


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SingleArg<CharacterModel>,
    CharactersAdapter.CharactersAdapterListener {

    companion object {
        const val DEBOUNCE_TIME = 400L
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val searchFragmentViewModel: SearchFragmentViewModel by viewModels()
    private val charactersAdapter: CharactersAdapter by lazy { CharactersAdapter(this) }

    private var uiStateJob1: Job? = null
    private var uiStateJob2: Job? = null

    override val layoutRes: Int
        get() = R.layout.fragment_search


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        initViews()
        searchFragmentViewModel.getCharacterLocal()
        charactersAdapter.charactersAdapterListener = this
    }


    override fun observeViewModel() {
        with(searchFragmentViewModel.characters) {
            uiStateJob1 = lifecycleScope.launchWhenStarted {
                collect {
                    when (it) {
                        is StarWarResource.Success -> {
                            it.data?.let { characters ->
                                val searchItems = characters.map { characterModel ->
                                    SearchAdapterItem.SearchItem(characterModel)
                                }
                                charactersAdapter.submitList(searchItems)
                                successState()
                            }
                        }
                        is StarWarResource.Loading -> {
                            charactersAdapter.submitList(emptyList())
                            loadingState()
                        }

                        is StarWarResource.Error -> {
                            errorState()
                            showError(it.message)
                        }
                        else -> Unit
                    }
                }
            }
        }


        with(searchFragmentViewModel.moreCharacters) {
            uiStateJob2 = lifecycleScope.launchWhenStarted {
                collect {
                    when (it) {
                        is StarWarResource.Success -> {
                            it.data?.let { moreCharacters ->
                                val newItems =
                                    moreCharacters.map { item -> SearchAdapterItem.SearchItem(item) }
                                val newList = charactersAdapter.currentList + newItems
                                charactersAdapter.submitList(newList)
                            }

                        }
                        is StarWarResource.Loading -> { }
                        is StarWarResource.Error -> { }
                        else -> Unit
                    }
                }
            }
        }


        with(searchFragmentViewModel.recentCharacters) {
            lifecycleScope.launchWhenStarted { collect {
                    when (it) {
                        is StarWarResource.Success -> {
                            it.data?.let { recents ->
                                when {
                                    recents.isEmpty() -> charactersAdapter.submitList(emptyList())
                                    else -> {
                                        charactersAdapter.submitList(listOf(SearchAdapterItem.RecentItem(recents)))
                                    }
                                }
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }


    }


    private fun successState() {
        binding.errorFrame.gone()
        binding.loadingFrame.gone()
        binding.charactersRv.visible()
    }

    private fun errorState() {
        binding.errorFrame.visible()
        binding.charactersRv.gone()
        binding.loadingFrame.gone()
    }

    private fun loadingState() {
        binding.charactersRv.gone()
        binding.errorFrame.gone()
        binding.loadingFrame.visible()
    }


    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        val endlessRecyclerViewScrollListener =
            object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    searchFragmentViewModel.loadMore()
                }
            }

        with(binding.charactersRv) {
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
                removeDuration = 100
            }
            layoutManager = linearLayoutManager
            addItemDecoration(
                VerticalListItemDecorator(
                    resources.getDimension(R.dimen.compact_padding).toInt()
                )
            )
            addOnScrollListener(endlessRecyclerViewScrollListener)
            adapter = charactersAdapter
        }

    }


    private fun initSearchView() {
        binding.characterSearch.queryTextChanges()
            .debounce(DEBOUNCE_TIME)
            .distinctUntilChanged()
            .filter {
                it.isNotEmpty()
            }
            .mapLatest {
                searchFragmentViewModel.searchCharacter(it.toString())
            }
            .catch { err ->
                showError(err.message)
            }
            .launchIn(lifecycleScope)
    }


    private fun showError(message: String?) {
        message?.let {
            binding.errorTv.text = it
        }
    }

    override fun onStop() {
        uiStateJob1?.cancel()
        uiStateJob2?.cancel()
        super.onStop()
    }

    override fun invoke(p1: CharacterModel) {
        val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(p1)
        navigateTo(direction)
    }

    override fun onRecentItemClicked(characterModel: CharacterModel) {
        val direction =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(characterModel)
        navigateTo(direction)
    }

    override fun onDeleteRecentItemClicked(name: String) {
        searchFragmentViewModel.deleteCharacterLocal(name)
    }

}