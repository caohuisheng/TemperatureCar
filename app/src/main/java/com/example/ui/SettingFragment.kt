package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    private lateinit var tv_temp: TextView;
    private lateinit var tv_env: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        initView(view)
        switchToFragment(TempDataFragment.newInstance())
        return view;
    }

    private fun initView(v: View) {
        tv_temp = v.findViewById(R.id.tv_temp)
        tv_env = v.findViewById(R.id.tv_env)

        tv_temp.setOnClickListener(View.OnClickListener {
            switchToFragment(TempDataFragment.newInstance())
        })
        tv_env.setOnClickListener(View.OnClickListener {
            switchToFragment(EnvDataFragment.newInstance())
        })
    }

    private fun switchToFragment(fragment:Fragment){
        val manager = fragmentManager
        val transaction = manager?.beginTransaction()
        transaction?.replace(R.id.data_container,fragment)
        transaction?.commit()
    }

}