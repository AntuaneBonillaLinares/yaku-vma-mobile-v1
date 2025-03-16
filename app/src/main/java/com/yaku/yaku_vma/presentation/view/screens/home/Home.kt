package com.yaku.yaku_vma.presentation.view.screens.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.yaku.yaku_vma.R
import com.yaku.yaku_vma.presentation.viewmodel.screens.home.HomeViewModel

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGenerar = view.findViewById<Button>(R.id.btnGenerar)

        btnGenerar.setOnClickListener {
            // Inflar acta.xml
            val actaView = layoutInflater.inflate(R.layout.fragment_acta, null)

            // Obtener el contenedor actual del fragmento
            val parent = view.parent as? ViewGroup
            parent?.removeAllViews()
            parent?.addView(actaView)
        }
    }
}
