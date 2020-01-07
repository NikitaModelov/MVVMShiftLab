package ru.shiftlab.mvvmshiftlab.vacancy.vacancyDetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ru.shiftlab.mvvmshiftlab.MainActivity

import ru.shiftlab.mvvmshiftlab.R
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDatabase
import ru.shiftlab.mvvmshiftlab.databinding.VacancyDetailFragmentBinding


class VacancyDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            VacancyDetailFragment()
    }

    private lateinit var viewModel: VacancyDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding : VacancyDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.vacancy_detail_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val arguments =
            VacancyDetailFragmentArgs.fromBundle(
                requireArguments()
            )

        val dataSource = VacancyDatabase.getInstance(application).vacancyDao
        val viewModelFactory =
            VacancyDetailViewModelFactory(
                arguments.vacancyId,
                dataSource
            )

        val vacancyDetailViewModel =
                    ViewModelProviders.of(
                this, viewModelFactory).get(VacancyDetailViewModel::class.java)

        binding.viewModel = vacancyDetailViewModel

        binding.lifecycleOwner = this

        vacancyDetailViewModel.navigateToVacancy.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    VacancyDetailFragmentDirections.actionVacancyDetailFragmentToVacancyFragment2()
                )
                vacancyDetailViewModel.doneNavigating()
            }
        })



        return binding.root
    }


}
