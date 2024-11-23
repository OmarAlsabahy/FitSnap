package com.example.fitsnap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fitsnap.Adapters.FactsAdapter
import com.example.fitsnap.ViewModels.FeedViewModel
import com.example.fitsnap.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {
    lateinit var binding:FragmentFeedBinding
    lateinit var adapter: FactsAdapter
    val viewModel : FeedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFeedBinding.bind(view)
        viewModel.getFeed()

        //observer foodName
        viewModel.foodName.observe(viewLifecycleOwner){
            binding.foodName.text = it
        }
        // observe health rate
        viewModel.healthScore.observe(viewLifecycleOwner){
            binding.healthRate.text = it
        }

        //observe description
        viewModel.description.observe(viewLifecycleOwner){
            binding.description.text = it
        }

        //observe nutrition
        viewModel.nutritionScale.observe(viewLifecycleOwner){
            binding.energyServe.text = String.format("%.2f",it[0].value)+" "+it[0].units
            binding.proteinServe.text = String.format("%.2f",it[1].value)+" "+it[1].units
            binding.transServe.text =String.format("%.2f",it[2].value)+" "+it[2].units
            binding.carbServe.text = String.format("%.2f",it[3].value)+" "+it[3].units
            binding.fibreServe.text = String.format("%.2f",it[4].value)+" "+it[4].units

        }

        //observe facts
        viewModel.facts.observe(viewLifecycleOwner){
            adapter = FactsAdapter(it)
            binding.factsRecycler.adapter = adapter
        }


        setImage()
    }

    private fun setImage() {
        Glide.with(this)
            .load("https://s3-alpha-sig.figma.com/img/8cfa/f4a0/04ca3a2533ce96d8c201c2c9e217d7a5?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=fce3qajbDHsTl2NRRxpIk2LUlqv9J3WEvb~y5Mj5prHVP3GPS8QjUriadsRFpbx-RghFejtZBMlKVSA41vPf206G~VgZ0~Vgh4KntXEKMgrZo10fpCZXXsIwOWB--OgP96TccxxjwkyAsJgoby59ovKJeuuM1JD9-~RFmiNccNBf-xG~Sin~6jUKc5gPvTzv8bkRc~7kxzf1y4PexBXRWntS~szFV6vT7QzMuAaZoLT-5bwfUO9QsOJ0wYvt7g6nYxm3o5~ZCbqR6UCPsWY4fQANSkJjyJok0PZ~h8LOmdaNGDiIy3rSh3PyJLdbeeNpZMvoE5VSNGszX7wXmdrrlg__")
            .centerCrop()
            .into(binding.foodImage)
    }
}