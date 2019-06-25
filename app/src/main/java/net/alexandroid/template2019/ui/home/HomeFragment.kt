package net.alexandroid.template2019.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseFragment
import net.alexandroid.template2019.ui.main.MainViewModel
import net.alexandroid.utils.mylog.MyLog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainViewModel.getDiscoveredMovies().observe(this, Observer<Tmdb.Discover> {
            MyLog.d(it.results[1].title)
        })
    }
}