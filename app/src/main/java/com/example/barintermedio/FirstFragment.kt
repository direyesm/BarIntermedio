package com.example.barintermedio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var mVierModel: BarViewModel
    private var idBar: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            idBar = it.getInt("id")
            Log.d("OBJ", idBar.toString())
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mVierModel = ViewModelProvider(this).get(BarViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/faf7c277742879.5c90cac284024.gif")
            .centerCrop().into(imageView)

       idBar?.let {
            mVierModel.getOnBarByID(it).observe(viewLifecycleOwner, Observer {
                txtNombre.setText(it.name)
                txtNum.setText(it.price.toInt())
            })
        }



        saveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    private fun numberPickerL(){
        numberPicker.maxValue = 20
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener{
                number_picker, i, i2 -> Log.d("NUMBER", number_picker.value.toString())

        }
    }
}