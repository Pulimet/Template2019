package net.alexandroid.template2019.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import net.alexandroid.template2019.R
import net.alexandroid.template2019.model.Movie
import net.alexandroid.template2019.ui.base.BaseFragment
import net.alexandroid.template2019.ui.main.MainViewModel
import net.alexandroid.utils.mylogkt.logD

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
        homeViewModel.getOpenMovie().observe(viewLifecycleOwner, Observer { openMovie(it) })

        if (isFavorites()) {
            fab.hide()
            homeViewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { updateItems(it) })
        } else {
            fab.setOnClickListener { homeViewModel.onFabClick() }
            mainViewModel.getDiscoveredMovies().observe(viewLifecycleOwner, Observer { updateItems(it) })
            homeViewModel.getOpenFavorites().observe(viewLifecycleOwner, Observer { openFavorites() })
        }
    }

    private fun isFavorites() = arguments != null && HomeFragmentArgs.fromBundle(arguments!!).favorites

    private fun setRecyclerView() {
        homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            homeAdapter = HomeAdapter(homeViewModel)
            adapter = homeAdapter
        }
    }

    private fun updateItems(list: List<Movie>) {
        logD("Discover movies loaded")
        homeAdapter?.setItems(list)
        swipeRefreshLayout.isRefreshing = false
    }

    private fun openMovie(movie: Movie) {
        homeViewModel.isAnimated = true

        val holder = homeRecyclerView.findViewHolderForLayoutPosition(movie.position)
        val view = holder?.itemView?.findViewById<View>(R.id.imgMovie) ?: return
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieFragment(movie),
            FragmentNavigatorExtras(view to "imageViewAnim")
        )
    }

    private fun openFavorites() {
        homeViewModel.isAnimated = true

        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToHomeFragment(true)
        )
    }
}