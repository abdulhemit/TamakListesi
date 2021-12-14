package com.okuuyghur.tamaklistesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.okuuyghur.tamaklistesi.databinding.FragmentListeBinding

class ListeFragment : Fragment() {
    private var _binding : FragmentListeBinding? = null
    private val binding get () = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListeBinding.inflate(layoutInflater,container,false)
        val h = YemekHelper(requireContext())
        val yemek_tizim = h.Read_Yemek()
        val adapter = R_Adapter(yemek_tizim)
        val ln = LinearLayoutManager(requireContext())
        binding?.recyclerView?.layoutManager = ln
        binding?.recyclerView?.adapter = adapter
        return  binding?.root
    }
}