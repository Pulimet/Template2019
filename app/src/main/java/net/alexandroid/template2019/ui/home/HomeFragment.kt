package net.alexandroid.template2019.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Tmdb
import net.alexandroid.template2019.ui.base.BaseFragment
import net.alexandroid.template2019.ui.main.MainViewModel
import net.alexandroid.utils.mylog.MyLog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private var homeAdapter: HomeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener { mainViewModel.onUserRefreshedMain() }
        mainViewModel.getDiscoveredMovies().observe(this, Observer<Tmdb.Discover> {
            MyLog.d("Discover movies loaded")
            homeAdapter?.setItems(it)
            swipeRefreshLayout.isRefreshing = false
        })

        homeViewModel.getOpenMovie().observe(this, Observer<Tmdb.Movie> {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieFragment(it)
            )
        })
    }

    private fun setRecyclerView() {
        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter = HomeAdapter(homeViewModel)
            adapter = homeAdapter
        }
    }
}