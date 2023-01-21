package dev.amits.cleanarchitecturenewsapp.presenter.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.amits.cleanarchitecturenewsapp.presenter.view.MainActivity
import dev.amits.cleanarchitecturenewsapp.databinding.FragmentSecondBinding
import dev.amits.cleanarchitecturenewsapp.presenter.view.viewmodel.LiveNewsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var viewModel: LiveNewsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = (activity as MainActivity).newsAdapter

        binding.recyclerView.adapter = adapter
        adapter.type = "saved"

        viewModel.getSaveNews().observe(viewLifecycleOwner){
            if (it != null){
                adapter.differ.submitList(it)
            }
        }
        adapter.setFevBtnClickListener {
            viewModel.deleteArticle(it)
            adapter.notifyDataSetChanged()
            Snackbar.make(view,"News delete Successfully!",Snackbar.LENGTH_SHORT).show()
        }
        adapter.setOnclickItemListener {
            val action = SavedNewsFragmentDirections.actionSecondFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).title = "Saved News"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}