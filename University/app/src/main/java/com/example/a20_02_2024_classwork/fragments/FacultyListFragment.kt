package com.example.a20_02_2024_classwork.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a20_02_2024_classwork.R
import com.example.a20_02_2024_classwork.data.Faculty
import com.example.a20_02_2024_classwork.databinding.FragmentFacultyListBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FacultyListFragment : Fragment() {

    companion object {
        private var INSTANCE: FacultyListFragment? = null

        fun getInstance(): FacultyListFragment {
            if (INSTANCE == null) INSTANCE = FacultyListFragment()
            return INSTANCE ?: throw Exception("FacultyListFragment не создан")
        }
    }

    private lateinit var viewModel: FacultyListViewModel
    private lateinit var _binding: FragmentFacultyListBinding
    val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacultyListBinding.inflate(inflater, container, false)
        binding.rvFacultyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FacultyListViewModel::class.java)
        viewModel.facultyList.observe(viewLifecycleOwner) {
            binding.rvFacultyList.adapter = FacultyAdapter(it!!.items)
        }
        binding.floatingActionButton2.setOnClickListener{
//            newFaculty()
        }
    }

    private inner class FacultyAdapter(private val items: List<Faculty>) :
        RecyclerView.Adapter<FacultyAdapter.ItemHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FacultyAdapter.ItemHolder {
            val item = layoutInflater.inflate(R.layout.element_faculty_list, parent, false)
            return ItemHolder(item!!)
        }

        override fun getItemCount(): Int = items.size
        override fun onBindViewHolder(holder: FacultyAdapter.ItemHolder, position: Int) {
            holder.bind(viewModel.facultyList.value!!.items[position])
        }

        private var lastView: View? = null
        private fun updateCurrentView(view: View) {
            val ll = lastView?.findViewById<LinearLayout>(R.id.llButtons)
            ll?.visibility=View.INVISIBLE
            ll?.layoutParams=ll?.layoutParams.apply { this?.width=1 }

            lastView?.findViewById<ConstraintLayout>(R.id.clFaculty)
                ?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            view.findViewById<ConstraintLayout>(R.id.clFaculty)
                ?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.my_blue))
            lastView = view

        }

        private inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
            private lateinit var faculty: Faculty

            fun bind(faculty: Faculty) {

                if (faculty == viewModel.faculty)
                    updateCurrentView(itemView)

                this.faculty = faculty
                val tv = itemView.findViewById<TextView>(R.id.tvFacultyName)
                tv.text = faculty.name

                val c_l = itemView.findViewById<ConstraintLayout>(R.id.clFaculty)
                itemView.findViewById<ImageButton>(R.id.ibEdit).setOnClickListener{
//                    updateFaculty()
                }
                itemView.findViewById<ImageButton>(R.id.ibDelete).setOnClickListener{
//                    deleteFaculty()
                }

                val lib = itemView.findViewById<LinearLayout>(R.id.llButtons)
                lib.visibility = View.INVISIBLE
                lib?.layoutParams=lib?.layoutParams.apply { this?.width=1 }

                val cl = View.OnClickListener {
                    viewModel.setCurrentFaculty(faculty)
                    updateCurrentView(itemView)
                }

                c_l.setOnClickListener(cl)
                tv.setOnClickListener(cl)


                c_l.setOnLongClickListener{
                    c_l.callOnClick()
                    lib.visibility= View.VISIBLE
                    MainScope().launch{
                        val lp = lib?.layoutParams
                        lp?.width = 1

                        while (lp?.width!! < 350){
                            lp?.width = lp?.width!! + 35
                            lib?.layoutParams = lp
                            delay(50)
                        }
                    }
                    true
                }
            }
        }
    }
}