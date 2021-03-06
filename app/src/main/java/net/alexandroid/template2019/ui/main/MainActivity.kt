package net.alexandroid.template2019.ui.main

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Movie
import net.alexandroid.utils.mylogkt.logD

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val bgScope = CoroutineScope(Dispatchers.IO + job)

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()

        mainViewModel.getDiscoveredMovies().observe(this, Observer<List<Movie>> {
            logD("Discover movies loaded")
        })

        mainViewModel.getViewEvents().observe(this, Observer {
            when (it) {
                EVENT_START_ANIMATION -> startAnimation()
            }
        })
    }

    private fun setToolBar() {
        setSupportActionBar(toolBar)
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(toolBar, navController)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            if (destination.id == R.id.homeFragment && arguments?.getBoolean("favorites") == true) {
                toolBar.title = getString(R.string.favorites)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.popBackStack()
        return true
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        mainViewModel.onWindowFocusChanged(hasFocus)
    }

    private fun startAnimation() {
        val windowRoot = findViewById<View>(android.R.id.content)
        mainAppLayout.apply {
            alpha = 0f
            scaleX = 0f
            scaleY = 0f
            translationY = windowRoot.height.toFloat()
            animate()
                .translationY(0f)
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(2000)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
