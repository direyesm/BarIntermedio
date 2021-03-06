package com.example.barintermedio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barintermedio.database.Bar
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), BarAdapter.PassTheData{

    lateinit var viewModel: BarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BarViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //val bar = Bar(1,"Bebida",1500,1)
//        val bar1 = Bar(0,"Cerveza",800,2)
//        //viewModel.insertBar(bar)
//        viewModel.insertBar(bar1)

        val mRecyclerView = recyclerView
        val mAdapter = BarAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allBar.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDataList(it)
            Log.d("UPDATE", it.size.toString())
        })

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun passTheData(mbar: Bar) {
        val mBundle = Bundle()
        mBundle.putInt("id", mbar.id)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, mBundle)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId){
//            R.id.action_settings -> {
//                viewModel.deleteAllBar()
//                true
//            }else -> super.onOptionsItemSelected(item)
//        }
//    }


}