package com.example.newsapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategorieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Categorie : Fragment() {
    val item = listOf(
        Category_item("sports", R.string.Sports, R.drawable.sport, R.color.red),
        Category_item(
            "entertainment",
            R.string.Politics,
            R.drawable.entertainment,
            R.color.darkBlue
        ),
        Category_item("health", R.string.Health, R.drawable.health, R.color.pink),
        Category_item("business", R.string.business, R.drawable.bussines, R.color.brown),
        Category_item("technology", R.string.Environment, R.drawable.technology, R.color.babyBlue),
        Category_item("science", R.string.Science, R.drawable.science, R.color.yellow),
    )

    @Inject
    lateinit var categoryAdaptor: categoryAdaptor
    lateinit var categoryDataBinding: FragmentCategorieBinding
    lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categorie, container, false)
        // Inflate the layout for this fragment

        return categoryDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdaptor.changeData(item)
        categoryDataBinding.categoryRV.adapter = categoryAdaptor

        searchView = requireActivity().findViewById(R.id.searchView)
        searchView.isVisible = false

        categoryAdaptor.onCategoryClickListener = object : categoryAdaptor.OnItemClickListener {
            override fun onCategoryClick(position: Int, categorie: Category_item) {
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
        fun onCategoryClicke(category: Category_item)
    }

}
