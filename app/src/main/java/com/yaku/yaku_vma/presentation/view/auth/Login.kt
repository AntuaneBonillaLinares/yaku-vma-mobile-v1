package com.yaku.yaku_vma.presentation.view.auth

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yaku.yaku_vma.R
import com.yaku.yaku_vma.databinding.FragmentLoginBinding
import com.yaku.yaku_vma.presentation.view.screens.home.Home
import com.yaku.yaku_vma.presentation.viewmodel.auth.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPasswordToggle(binding.inContrasena)

        binding.btnIniciarSesion.setOnClickListener {
            val username = binding.inCorreo.text.toString().trim()
            val password = binding.inContrasena.text.toString().trim()

            if (loginViewModel.isLoginValid(username, password)) {
                val sharedPref = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()

                Toast.makeText(requireContext(), "¡Login correcto!", Toast.LENGTH_SHORT).show()

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, Home())
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupPasswordToggle(editText: View) {
        val editTextCast = editText as android.widget.EditText
        editTextCast.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd: Drawable? = editTextCast.compoundDrawables[2]
                drawableEnd?.let {
                    val drawableWidth = it.bounds.width()
                    val touchX = event.x.toInt()
                    val editTextWidth = editTextCast.width
                    val paddingEnd = editTextCast.paddingEnd

                    if (touchX >= (editTextWidth - drawableWidth - paddingEnd)) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            editTextCast.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            editTextCast.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.visibility_on, 0
                            )
                        } else {
                            editTextCast.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            editTextCast.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.visibility_off, 0
                            )
                        }
                        editTextCast.setSelection(editTextCast.text?.length ?: 0)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
