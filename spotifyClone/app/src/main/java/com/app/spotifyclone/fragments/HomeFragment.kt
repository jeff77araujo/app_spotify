package com.app.spotifyclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotifyclone.DetailsActivity
import com.app.spotifyclone.R
import com.app.spotifyclone.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val argumento = Bundle()
            fragment.arguments = argumento
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Dados falsos para testar aplicação
//        val categories: MutableList<Category> = ArrayList()
//        for (categ in 0..4) {
//            val category = Category()
//            category.title = "Categoria: $categ"
//
//            val albums: MutableList<Album> = ArrayList()
//            for (alb in 0..19) {
//                val album = Album()
//                //album.album = R.drawable.spotify
//                albums.add(album)
//            }
//            category.albums = albums
//            categories.add(category)
//        }

        val categories = arrayListOf<Category>()

        categoryAdapter = CategoryAdapter(categories)
        recycler_view_category.adapter = categoryAdapter
        recycler_view_category.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyAPI::class.java)
            .listCategory()
            .enqueue(object : Callback<Categories>{
                    override fun onFailure(call: Call<Categories>, t: Throwable) {
                        Toast.makeText(context, "Erro no servidor", Toast.LENGTH_SHORT).show()
                    }

                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            categoryAdapter.categories.clear()
                            categoryAdapter.categories.addAll(it.categories)
                            categoryAdapter.notifyDataSetChanged()
                        }
                    }
                }
                }
            )
    }

    private inner class CategoryAdapter(internal val categories: MutableList<Category>): RecyclerView.Adapter<CategoryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            return CategoryHolder(layoutInflater.inflate(R.layout.category_item, parent, false))
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        override fun getItemCount(): Int = categories.size
    }

    private inner class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.text_title.text = category.title
            itemView.recycler_view_album.adapter = AlbumsAdapter(category.albums) { album ->
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("album", album.album)
                intent.putExtra("title", titles[album.id])
                startActivity(intent)
            }
            itemView.recycler_view_album.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    //----------------------------------------Albums----------------------------------------------->

    private inner class AlbumsAdapter(private val albums: List<Album>, private val listener: ((Album) -> Unit)?): RecyclerView.Adapter<AlbumsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder = AlbumsHolder(
             layoutInflater.inflate(R.layout.album_item, parent, false), listener)


        override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
            val album = albums[position]
            holder.bind(album)
        }

        override fun getItemCount(): Int = albums.size
    }

    private inner class AlbumsHolder(itemView: View, val onClick: ((Album) -> Unit)?): RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            //itemView.image_album.setImageResource(album.album)
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
            itemView.image_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }
    }

}


