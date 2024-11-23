package com.example.fitsnap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fitsnap.ViewModels.HomeViewModel
import com.example.fitsnap.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val viewModel : HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        viewModel.getHome()

        val dateFormat = SimpleDateFormat("EEEE, dd'th' MMM", Locale.US)
        val today = Calendar.getInstance()
        val formattedDate = dateFormat.format(today.time)
        binding.txtDate.text = formattedDate
        //observe plane name
        viewModel.goalName.observe(viewLifecycleOwner){
            binding.planeName.text = it
            binding.planeTitle.text = it
        }

        //observe calories
        viewModel.calories.observe(viewLifecycleOwner){
            binding.dietPts.text = it
        }

        // observe exercise pts
        viewModel.exercisePts.observe(viewLifecycleOwner){
            binding.excercisePts.text = it
        }

        // observe percent
        viewModel.percent.observe(viewLifecycleOwner){
            val percentage = it.replace("%","")
            binding.percent.setProgressWithAnimation(percentage.toFloat())
            binding.percentage.text = it
        }

        setGoalImage()
    }

    private fun setGoalImage() {
        Glide.with(this)
            .load("https://s3-alpha-sig.figma.com/img/c367/d24e/80c4e289efd652084eca8bac0bc065b4?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=N-v93Xz-jaWRYY1sPDdP71VuDtbeaha8HxdaPCtQ2R~0e0pFJ9S71aSIOWAVuFDnsBwoFPXwzNy7doYpCM4G07ypj0T-eTCpqNMzP0feATHAxLZ4TnAqIz9D1Mb3SJVt5YXpwBHYh05ExJChUCOZ7ulC7-yW4ViQ-5Ut2m0aW2Imnhe2ZAL4HTRnFpaMqkwhSl-eEC23Ku9UNSk72cLxi9xQmfO5XdEpYrgH~iWvGxah65FTDjEOerPgIUqS2eQWzqDQs4iyN6-g658m1sDz5chY9PYGmGZwoin-yK9em60mFjBoaKkb6EVs1~vVBpfSSwPnfvKeZU~UAmPT-Y6Siw__")
            .into(binding.goalImage)
    }
}