package net.alexandroid.template2019.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.utils.mylog.MyLog
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.ViewGroup
import android.view.View
import android.view.animation.DecelerateInterpolator
import net.alexandroid.template2019.R


class MainActivity : AppCompatActivity() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val bgScope = CoroutineScope(Dispatchers.IO + job)

    private val mainViewModel: MainViewModel by viewModel()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        mainViewModel.getDiscoveredMovies().observe(this, Observer<Tmdb.Discover> {
            MyLog.d("Discover movies loaded")
        })

        mainViewModel.getViewEvents().observe(this, Observer {
            when (it) {
                EVENT_START_ANIMATION -> startAnimation()
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
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
                .setDuration(1500)
                .setInterpolator(DecelerateInterpolator())
                .setStartDelay(300)
                .start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
