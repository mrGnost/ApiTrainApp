package com.example.apitrainapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apitrainapp.data.model.DogSample
import com.example.apitrainapp.databinding.FragmentDogsBinding
import com.example.apitrainapp.ui.adapter.DogAdapter
import com.example.apitrainapp.ui.viewmodel.DogsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogsFragment : Fragment() {
    private lateinit var binding: FragmentDogsBinding
    private val viewModel: DogsViewModel by viewModels()
    private lateinit var adapter: DogAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
        setupRecyclerView()
        binding.addDog.setOnClickListener {
            viewModel.getDog()
        }
    }

    private fun subscribeLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner, this::showError)
        viewModel.dogsLiveData.observe(viewLifecycleOwner, this::showDog)
    }

    private fun setupRecyclerView() {
        adapter = DogAdapter(mutableListOf())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun showError(e: Throwable) {
        Snackbar.make(binding.addDog, "Error: ${e.message}", Snackbar.LENGTH_LONG).apply {
            setAction("OK") {

            }
            show()
        }
    }

    private fun showDog(dog: DogSample) {
        adapter.addDog(dog)
    }
}