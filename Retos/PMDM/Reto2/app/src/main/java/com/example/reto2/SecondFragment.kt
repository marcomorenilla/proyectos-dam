package com.example.reto2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto2.databinding.FragmentSecondBinding
import com.example.sqlite.adaptador.AdaptadorRecycler
import com.example.sqlite.dao.ElementoDAO
import com.example.sqlite.model.Elemento

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), OnClickListener {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var listaElemento: ArrayList<Elemento>
    private lateinit var recyclerAdaptador: AdaptadorRecycler
    private lateinit var fragmentContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        fragmentContext = requireContext().applicationContext
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listaElemento = ElementoDAO(fragmentContext).getElementos()
        recyclerAdaptador = AdaptadorRecycler(listaElemento,fragmentContext)
        binding.recyclerElementos.adapter = recyclerAdaptador
        binding.recyclerElementos.layoutManager =
            LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL,false)


        binding.btnVolver.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}