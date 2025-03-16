package com.yaku.yaku_vma.presentation.view.screens.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaku.yaku_vma.R
import com.yaku.yaku_vma.presentation.viewmodel.screens.home.ActaViewModel

class Acta : Fragment() {

    companion object {
        fun newInstance() = Acta()
    }

    private val viewModel: ActaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_acta, container, false)
    }
}