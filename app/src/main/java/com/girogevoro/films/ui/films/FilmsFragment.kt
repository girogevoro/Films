package com.girogevoro.films.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girogevoro.films.databinding.FragmentFilmsBinding
import com.girogevoro.films.utils.ui.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmsFragment : ViewBindingFragment<FragmentFilmsBinding>() {
    val viewModel: FilmsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance() = FilmsFragment()
    }
}