package internship.gits.newsapps.newsdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import internship.gits.newsapps.data.News

import internship.gits.newsapps.databinding.DetailFragBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : Fragment() {
    private lateinit var viewBinding: DetailFragBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = DetailFragBinding.inflate(inflater, container, false).apply {
            vm = (activity as DetailActivity).obtainViewModel().apply {
                val news : News = arguments.getParcelable(DetailActivity.EXTRA_PARCELABLE)
                details.set(news)
            }
            action = object :  DetailUserActionListener {
                override fun onClickMoreInfo() {
                    vm?.loadWeb()
                }
            }
        }
        return viewBinding.root

    }

    override fun onResume() {
        super.onResume()

        viewBinding.vm?.start()
    }

    companion object {
        fun newInstance(bundle:Bundle) = DetailFragment().apply {
            arguments = bundle
        }
    }
}