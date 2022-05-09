package com.example.newsapp.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class Categorie : Fragment() {
    val item = listOf(
        category_item("sports", R.string.Sports, R.drawable.sport, R.color.red),
        category_item(
            "entertainment",
            R.string.Politics,
            R.drawable.entertainment,
            R.color.darkBlue
        ),
        category_item("health", R.string.Health, R.drawable.health, R.color.pink),
        category_item("business", R.string.business, R.drawable.bussines, R.color.brown),
        category_item("technology", R.string.Environment, R.drawable.technology, R.color.babyBlue),
        category_item("science", R.string.Science, R.drawable.science, R.color.yellow),

        )
    lateinit var recyclerView: RecyclerView
    lateinit var categoryAdaptor: categoryAdaptor
    lateinit var searchView: androidx.appcompat.widget.SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.category_RV)
        categoryAdaptor = categoryAdaptor(item)
        recyclerView.adapter = categoryAdaptor
        searchView = requireActivity().findViewById(R.id.searchView)
        searchView.isVisible = false

        categoryAdaptor.onCategoryClickListener = object : categoryAdaptor.OnItemClickListener {
            override fun onCategoryClick(position: Int, categorie: category_item) {
//           onCategory.let {
//               onCategoryClicked ->
//               onCategoryClicked?.onCategoryClicke(categorie)
//           }
                onCategory?.onCategoryClicke(categorie)

            }
        }

    }

    var onCategory: onCategoryClicked? = null

    interface onCategoryClicked {
        fun onCategoryClicke(category: category_item)
    }

}
