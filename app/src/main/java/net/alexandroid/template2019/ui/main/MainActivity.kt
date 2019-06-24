package net.alexandroid.template2019.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.alexandroid.template2019.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val bgScope = CoroutineScope(Dispatchers.IO + job)

    val mainViewModel: MainViewModel by viewModel()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
