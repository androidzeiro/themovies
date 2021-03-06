package br.com.themovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.themovies.R
import br.com.themovies.databinding.FragmentHomeBinding
import br.com.themovies.model.response.upcomingmovies.ResultResponse
import br.com.themovies.ui.adapters.MoviesAdapter
import br.com.themovies.viewmodel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val adapter: MoviesAdapter by lazy { MoviesAdapter(::listenerBottom, ::onClickItem) }

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setupObservers()
        viewModel.getUpCommingMovies()
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter

        return view
    }

    private fun listenerBottom() {
        viewModel.getUpCommingMovies()
    }

    private fun onClickItem(item: ResultResponse) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsMovieFragment(movieId = item.id)
        findNavController().navigate(action)
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.setList(it.toList())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}