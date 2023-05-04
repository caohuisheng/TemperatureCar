package com.example.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.View.CustomTouchEvent
import com.example.ViewModel.BlueViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RemoteControlFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RemoteControlFragment_Car : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: BlueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModel = ViewModelProvider(requireActivity()).get(BlueViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_remote_control_car, container, false)
        initView(v)
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RemoteControlFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RemoteControlFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView(v : View) {
        val up = v.findViewById<AppCompatImageButton>(R.id.remote_up)
        val customTouchEvent = CustomTouchEvent()
        customTouchEvent.apply {
            pressAction = {viewModel.sendMessage("F")}
            endAction = {viewModel.sendMessage("S")}
        }
        //up.setOnTouchListener(customTouchEvent)
        val videoView = v.findViewById<VideoView>(R.id.remote_videoView)
        videoView.setMediaController(MediaController(requireContext()))
        videoView.setOnClickListener{view ->
            run {
                //val uri = "android.resource://" + requireActivity().packageName + "/" + R.raw.hoshi;  //本地
                //videoView.setVideoURI(Uri.parse(uri));  //本
                videoView.start()
            }
        }
    }
}