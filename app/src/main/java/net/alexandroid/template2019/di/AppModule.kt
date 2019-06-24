package net.alexandroid.template2019.di

import net.alexandroid.template2019.ui.main.MainRepository
import net.alexandroid.template2019.ui.main.MainRepositoryImpl
import net.alexandroid.template2019.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    single<MainRepository> { MainRepositoryImpl() }
    viewModel { MainViewModel(get()) }
}

