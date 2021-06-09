package com.app.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.spotifyclone.R
import com.app.spotifyclone.fragmentsTab.AlbumsFragment
import com.app.spotifyclone.fragmentsTab.ArtistsFragment
import com.app.spotifyclone.fragmentsTab.PlayListsFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {

    companion object {
        fun newInstance(): LibraryFragment {
            val fragment = LibraryFragment()
            val argumento = Bundle()
            fragment.arguments = argumento
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)
            .add("PlayLists", PlayListsFragment::class.java)
            .add("Artistas", ArtistsFragment::class.java)
            .add("Álbuns", AlbumsFragment::class.java)
            .create())

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }

}