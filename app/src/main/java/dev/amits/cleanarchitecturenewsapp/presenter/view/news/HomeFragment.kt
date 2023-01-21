package dev.amits.cleanarchitecturenewsapp.presenter.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.amits.cleanarchitecturenewsapp.presenter.view.MainActivity
import dev.amits.cleanarchitecturenewsapp.databinding.FragmentFirstBinding
import dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel.LiveNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    var page=1
    var pages=0
    var isLastPage=false
    var isLoading=false
    var isScrolling=false

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: LiveNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.type = "home"
        binding.recyclerView.adapter = newsAdapter
        binding.viewModel = viewModel
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        newsAdapter.setOnclickItemListener {
            val action = HomeFragmentDirections.actionNewsFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        newsAdapter.setSaveBtnClickListener {
            viewModel.saveArticle(it)
            Snackbar.make(view,"News Saved successfully",Snackbar.LENGTH_SHORT).show()
        }

        viewModel.getNewsHeadlines("in", 1)
        viewModel.topHeadingData.observe(viewLifecycleOwner) {
            isLoading=false
            binding.progressBar.visibility = View.VISIBLE
            if (it != null) {
                it.data?.let { it1 ->
                    newsAdapter.differ.submitList(it1.articles!!.toList())
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).title = "Home"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}