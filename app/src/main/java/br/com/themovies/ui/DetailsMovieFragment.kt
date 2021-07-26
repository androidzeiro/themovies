package br.com.themovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.themovies.R
import br.com.themovies.databinding.FragmentDetailsMovieBinding
import br.com.themovies.extensions.loadImage
import br.com.themovies.model.response.detailsmovie.DetailsMovieResponse
import br.com.themovies.ui.adapters.GenresAdapter
import br.com.themovies.viewmodel.DetailsMovieViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private val adapter: GenresAdapter by lazy { GenresAdapter() }

    private val args: DetailsMovieFragmentArgs by navArgs()
    private val viewModel: DetailsMovieViewModel by viewModels()

    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        val view = binding.root

        setupObservers()
        viewModel.getDetailsMovie(movieId = args.movieId)

        return view
    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner) { it: DetailsMovieResponse? ->
            if (it != null) {
                setViews(it)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {

        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                dialog(resources.getString(R.string.cenection_error))
            } else {
                dialog(it)
            }
        }
    }

    private fun dialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.error_title))
            .setMessage(error)
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
            }
            .show()
    }

    private fun setViews(data: DetailsMovieResponse) {
        binding.ivBackdropPath.loadImage(
            context = binding.ivBackdropPath.context,
            url = data.backdropPath
        )

        adapter.setList(data.genres)
        binding.rvGenres.setHasFixedSize(true)
        binding.rvGenres.adapter = adapter

        binding.tvTitle.text = data.title
        binding.tvOverview.text = data.overview
        binding.tvVoteAverage.text = data.voteAverage.toString()
        binding.tvRuntime.text = data.runtime.toString()
        binding.tvReleaseDate.text = data.releaseDate
        binding.tvBudget.text = data.budget.toString()
        binding.tvRevenue.text = data.revenue.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}