package com.app.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotifyclone.R
import com.app.spotifyclone.model.Section
import com.app.spotifyclone.model.data
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.section_item.view.*


class SearchFragment : Fragment() {

    private lateinit var sectionAdapter: SectionAdapter

    companion object {
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            val argumento = Bundle()
            fragment.arguments = argumento
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sectionAdapter = SectionAdapter(data())
        recycler_view_sections.adapter = sectionAdapter
        recycler_view_sections.layoutManager = GridLayoutManager(context, 2)
    }

    private inner class SectionAdapter(private val sections: MutableList<Section>): RecyclerView.Adapter<SectionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
            return SectionHolder(layoutInflater.inflate(R.layout.section_item, parent, false))
        }

        override fun onBindViewHolder(holder: SectionHolder, position: Int) {
            val section = sections[position]
            holder.bind(section)
        }

        override fun getItemCount(): Int = sections.size
    }

    private inner class SectionHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(section: Section) {
            itemView.image_section.setImageResource(section.section)
            itemView.text_name_section.text = section.nameSection
        }
    }
}