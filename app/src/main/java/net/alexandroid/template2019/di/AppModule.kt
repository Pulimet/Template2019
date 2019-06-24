package net.alexandroid.template2019.di

import net.alexandroid.template2019.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    viewModel { MainViewModel() }
}

