@file:Suppress("UnusedImport")

package com.raywenderlich.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.listview.databinding.FragmentMainBinding
class MainFragment(val clickListener: MainFragmentInteractionListener) : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {
    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }
    private lateinit var binding: FragmentMainBinding
    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener)
                = MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container,
            false)
        // 1
        binding.listsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext())
        // 2
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))).get(MainViewModel::class.java)
        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
        binding.listsRecyclerview.adapter = recyclerViewAdapter
        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }
    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }

}