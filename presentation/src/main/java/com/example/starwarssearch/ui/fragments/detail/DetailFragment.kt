package com.example.starwarssearch.ui.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.states.ItemState
import com.example.core.states.StarWarResource
import com.example.core.utils.itemdecorators.DetailsVerticalListDecorator
import com.example.core.utils.itemdecorators.VerticalListItemDecorator
import com.example.starwarssearch.R
import com.example.starwarssearch.databinding.FragmentDetailLayoutBinding
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.DetailAdapterItem
import com.example.starwarssearch.ui.adapters.DetailFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailLayoutBinding>() {

    private val detailsFragmentViewModel: DetailFragmentViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private val character: CharacterModel by lazy { args.character }

    private val detailAdapter: DetailFragmentAdapter by lazy { DetailFragmentAdapter() }
    private var uiStateJob: Job? = null

    override val layoutRes: Int
        get() = R.layout.fragment_detail_layout

    override fun observeViewModel() {

        with(detailsFragmentViewModel) {
            uiStateJob = lifecycleScope.launchWhenStarted {
                species.collect { res ->
                    when (res) {
                        is StarWarResource.Loading -> {
                            detailAdapter.addItem(DetailAdapterItem.HeaderItem(getString(R.string.species)))
                            detailAdapter.addItem(DetailAdapterItem.SpeciesItem(emptyList(), ItemState.LOADING))
                        }
                        is StarWarResource.Success -> {
                            res.data?.let {
                                if (it.isNotEmpty()) {
                                    detailAdapter.addItem(DetailAdapterItem.SpeciesItem(it, ItemState.SUCCESS))
                                }
                            }
                        }
                        is StarWarResource.Error -> {
                            detailAdapter.addItem (DetailAdapterItem.SpeciesItem(emptyList(), ItemState.ERROR, res.message))
                        }
                    }
                }
            }


            lifecycleScope.launchWhenStarted {
                films.collect { res ->
                    when (res) {
                        is StarWarResource.Success -> {
                            res.data?.let {
                                if (it.isNotEmpty()) {
                                    detailAdapter.addItem(DetailAdapterItem.FilmsItem(it, ItemState.SUCCESS))
                                }
                            }
                        }

                        is StarWarResource.Loading -> {
                            detailAdapter.addItem(DetailAdapterItem.HeaderItem(getString(R.string.films)))
                            detailAdapter.addItem(DetailAdapterItem.FilmsItem(emptyList(), ItemState.LOADING))
                        }

                        is StarWarResource.Error -> {
                            detailAdapter.addItem(DetailAdapterItem.FilmsItem(emptyList(), ItemState.ERROR, res.message))
                        }
                    }
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRV()
        detailsFragmentViewModel.getAllCharacterDetails(character)
    }


    private fun setUpRV() {
        with(binding.detailsRv) {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DetailsVerticalListDecorator(
                    resources.getDimension(R.dimen.compact_padding).toInt()
                )
            )
            adapter = detailAdapter
        }
        detailAdapter.addItem(DetailAdapterItem.CharacterItem(character))
    }

}