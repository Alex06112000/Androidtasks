package com.alex.calculator.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alex.calculator.R

class AboutFragment : Fragment() {

  private lateinit var galleryViewModel: AboutViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    galleryViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_about, container, false)
    val textView: TextView = root.findViewById(R.id.text_gallery)
    val shareButton:TextView = root.findViewById(R.id.share)
    galleryViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
      shareButton.setOnClickListener{
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "type/palin"
        val shareBody = "Calculator  info"
        val shareSub = textView.text
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
        startActivity(Intent.createChooser(shareIntent, "Share about us"))
      }
    })
    return root
  }
}