package com.ssong_develop.changefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SampleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = arguments?.getInt(ARG_NO)
        val text = "${number}번째 프래그먼트"
        Log.d("SampleFragment","OnCreate $text")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.text)
        val number = arguments?.getInt(ARG_NO)
        val text = "${number}번째 프래그먼트"
        Log.d("SampleFragment","onViewCreated ${text}")
        textView.text = text
    }

    companion object {
        private const val ARG_NO = "ARG_NO"

        fun newInstance(number : Int): SampleFragment {
            val sampleFragment = SampleFragment()
            val args = Bundle()
            args.putInt(ARG_NO,number)
            sampleFragment.arguments = args
            return sampleFragment
        }
    }
}