package com.example.lamp.presenter.main


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lamp.R
import com.example.lamp.data.network.entity.State
import com.example.lamp.data.network.entity.StateResponse
import com.example.lamp.databinding.FragmentMainBinding
import com.example.lamp.di.ViewModelFactory
import com.example.lamp.di.appComponent
import javax.inject.Inject


class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels() {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getColors()

        with(binding) {
            on.setOnClickListener {
                viewModel.turnOn()
                viewModel.stateTurnOn.observe(viewLifecycleOwner) {
                    reaction(it)
                }
            }

            off.setOnClickListener {
                viewModel.turnOff()
                viewModel.stateTurnOff.observe(viewLifecycleOwner) {
                    reaction(it)
                }
            }
            brightness.setOnSeekBarChangeListener(
                BrightnessSeekBar() {level ->
                    stateBrightness(level)}
            )


            val adapter = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_spinner_item)


            viewModel.colors.observe(viewLifecycleOwner){
                when(it.state){
                    State.SUCCESS -> {
                        adapter.addAll(it.content)
                    }
                    State.FAIL -> {
                        adapter.addAll(listOf("Нет"))
                    }
                }
            }

            viewModel.stateColor.observe(viewLifecycleOwner){
                reaction(it)
            }
            color.adapter = adapter
            color.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    itemSelected: View, selectedItemPosition: Int, selectedId: Long
                ) {
                    val adapter = parent?.adapter as? ArrayAdapter<String>
                    val selectedText = adapter?.getItem(selectedItemPosition) ?: ""
                    if(selectedText != "-")
                        viewModel.setColor(selectedText)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun reaction(it: StateResponse<Boolean>){
        Toast.makeText(
            requireContext(),
            it.message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun stateBrightness(level: Int){
        viewModel.changeBrightness(level)
        viewModel.stateBrightness.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }
}