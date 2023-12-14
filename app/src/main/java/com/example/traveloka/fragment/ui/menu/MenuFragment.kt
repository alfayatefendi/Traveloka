package com.example.traveloka.fragment.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.traveloka.databinding.FragmentMenuBinding
import com.example.traveloka.fragment.adapter.MenuListAdapter

const val ARG_MENU_SECTION = "arg_menu_section"

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuAdapter: MenuListAdapter
    private var section = 0

    private val menuViewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_MENU_SECTION) }?.apply {
            section = getInt(ARG_MENU_SECTION)
        }
        menuAdapter = MenuListAdapter { }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mGridLayoutManager = GridLayoutManager(requireContext(), 5)
        binding.root.apply {
            layoutManager = mGridLayoutManager
            adapter = menuAdapter
        }
        setupMenu(section)
    }
    private fun setupMenu(section: Int) {
        when (section) {
            1 -> {
                menuViewModel.listMenuFirst.observe(viewLifecycleOwner) {
                    menuAdapter.submitList(it)
                }
            }

            2 -> {
                menuViewModel.listMenuSecond.observe(viewLifecycleOwner) {
                    menuAdapter.submitList(it)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}