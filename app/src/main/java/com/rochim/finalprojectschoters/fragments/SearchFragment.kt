package com.rochim.finalprojectschoters.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rochim.finalprojectschoters.ArticleActivity
import com.rochim.finalprojectschoters.R
import com.rochim.finalprojectschoters.adapters.NewsAdapter
import com.rochim.finalprojectschoters.api.Retrofit
import com.rochim.finalprojectschoters.models.Article
import com.rochim.finalprojectschoters.models.NewsResponse
import com.rochim.finalprojectschoters.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var hints: String
    private lateinit var editable: Editable
    private var listNews: MutableList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvNews)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        button = view.findViewById(R.id.btnSearch)
        editText = view.findViewById(R.id.etSearch)

        hints = activity?.intent?.getStringExtra("hint").toString()
        editable = Editable.Factory.getInstance().newEditable(hints)
        when(hints){
            "Search news"->{
                editText.text.clear()
            }
            else ->{
                editText.text = editable
                dataInitialization(hints)
            }
        }

        button.setOnClickListener{
            listNews.clear()

            val hint = editText.text.toString()
            dataInitialization(hint)

            closeKeyboard(editText)
        }

    }

    fun dataInitialization(string: String){
        Retrofit.instance.getSearchNews(string,Constants.API_KEY).enqueue(object:
            Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                listNews.addAll(response.body()!!.articles)
                adapter = NewsAdapter(listNews)
                recyclerView.adapter = adapter

                adapter.setOnItemClickListener(object : NewsAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(activity, ArticleActivity::class.java)
                        intent.putExtra("url",listNews.get(position).url)
                        intent.putExtra("frag","search")
                        intent.putExtra("hint",string)
                        startActivity(intent)
                    }

                })
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(context,"${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun closeKeyboard(view: View){
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }

}