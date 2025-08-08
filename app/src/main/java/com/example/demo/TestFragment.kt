package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentTestBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class TestFragment : Fragment() {
    lateinit var binding: FragmentTestBinding
    val viewModel by activityViewModels<ActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentTestBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



                binding.btnClick.setOnClickListener {

                    viewLifecycleOwner.lifecycleScope.launch {
                        //here delay to simulate mutliple clicks and see how many events are collected
                        delay(2000)
                        viewModel.sendEvent(Event.NavigateToTestFragment2)
                    }






                }

        viewLifecycleOwner.lifecycleScope.launch {

        //collect using shared flow and use distinctUntilChanged() operator
                viewModel.eventSharedFlow.distinctUntilChanged()
                    .collect { event ->
                        when (event) {

                            Event.NavigateToTestFragment2 -> {
                                Log.e("event", "navigate event")
                                findNavController().navigate(R.id.test2Fragment)
                            }
                        }
                    }
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("destroy","onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("destroy","onViewDestroy")
    }


}





