package com.example.wb_7_2.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wb_7_2.R
import com.example.wb_7_2.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        binding?.detailsToolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupUI() {
        binding?.apply {

            detailsToolbar.title = arguments?.getString("name")

            heroNameDetailsTextView.text = arguments?.getString("name")
            publisherTextView.text = arguments?.getString("publisher")
            appearsInTextView.text = resources?.getString(R.string.appears_in) +
                    arguments?.getString("appears_in")
            powersTextView.text = arguments?.getString("powers")

            Picasso.get().load(arguments?.getString("img"))
                .into(heroImageImageView)

            Picasso.get().load(arguments?.getString("img"))
                .placeholder(R.drawable.superhero_placeholder)
                .into(heroIconDetailsImageView)

        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}