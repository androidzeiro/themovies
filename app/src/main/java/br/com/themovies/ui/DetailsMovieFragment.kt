package br.com.themovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.themovies.databinding.FragmentDetailsMovieBinding
import br.com.themovies.viewmodel.DetailsMovieViewModel

class DetailsMovieFragment : Fragment() {

    private lateinit var viewModel: DetailsMovieViewModel

    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}