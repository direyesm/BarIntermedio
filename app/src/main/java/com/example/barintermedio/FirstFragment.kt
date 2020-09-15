package com.example.barintermedio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.barintermedio.database.Bar
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var mViewModel: BarViewModel
    private var idBar: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(BarViewModel::class.java)
        arguments?.let {
            idBar = it.getInt("id")
            Log.d("ID",idBar.toString())
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/faf7c277742879.5c90cac284024.gif")
            .centerCrop().into(imageView)
        numberPickerL()

        idBar?.let {
            mViewModel.run {
                getOnBarByID(it).observe(viewLifecycleOwner, Observer {
                    editTexBar.setText(it.name)
                    textResult.setText(it.total.toString())
                })
            }
        }

        saveBtn.setOnClickListener {
            val producto = editTexBar.text.toString()
            val total = textResult.text.toString().toInt()
            val cantidad = numberPicker.value.toInt()

            if (producto.isNotEmpty()){
                if (idBar != null){
                    Log.d("OBJ_ID_BAR", idBar.toString())
                    val mBar = Bar (name = producto, cantidad = cantidad, total = total, id = idBar!!)
                    mViewModel.updateBar(mBar)
                }else{
                    val mBar = Bar (name = producto, cantidad = cantidad, total = total)
                    mViewModel.insertBar(mBar)
                }
            }else{
                Toast.makeText(context,"Debe llenar todo los campos", Toast.LENGTH_LONG).show()
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun numberPickerL(){
        numberPicker.maxValue = 20
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = true
        numberPicker.setOnValueChangedListener{
                number_picker, i, i2 -> Multiplicacion()
            Log.d("NUMBER", number_picker.value.toString())
        }
    }

    fun Multiplicacion(){
        var n1 : Int = txtNum.text.toString().toInt()
        var n2 : Int = numberPicker.value.toInt()
        var result : String = (n1 * n2).toString()
        textResult.setText(result)
    }

}
