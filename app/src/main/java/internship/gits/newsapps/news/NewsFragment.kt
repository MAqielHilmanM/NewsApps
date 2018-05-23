package internship.gits.newsapps.news


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import internship.gits.newsapps.R
import internship.gits.newsapps.databinding.NewsFragBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : Fragment() {

    private lateinit var viewBinding: NewsFragBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = NewsFragBinding.inflate(inflater, container, false).apply {
            vm = (activity as NewsActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNews()
    }

    private fun setupNews() {
        val viewModel = viewBinding.vm
        if(viewModel != null){
            newsAdapter = NewsAdapter(viewModel.newsList,viewModel)
            viewBinding.rvNewsFrag.adapter = newsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()

    }

    companion object {
        fun newInstance() = NewsFragment().apply {

        }
    }
}
