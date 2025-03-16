package com.yaku.yaku_vma.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.yaku.yaku_vma.MainActivity
import com.yaku.yaku_vma.R
import com.yaku.yaku_vma.presentation.viewmodel.auth.WelcomeViewModel
import androidx.fragment.app.viewModels

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val btnIniciarSesion = view.findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("navigateTo", "login")
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }
}
