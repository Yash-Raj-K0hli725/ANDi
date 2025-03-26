package com.example.andi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andi.Fragments.Business
import com.example.andi.Fragments.Ecommerce
import com.example.andi.Fragments.Education
import com.example.andi.Fragments.Food
import com.example.andi.Fragments.Matrimony
import com.example.andi.Fragments.Medical
import com.example.andi.Fragments.Politics
import com.example.andi.Fragments.Recruitment
import com.example.andi.Fragments.realEstates
import com.example.andi.Fragments.toursTravels
import com.example.andi.MainActivity
import com.example.andi.Utils.htmlData
import com.example.andi.Utils.vpAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class mainModel(context: MainActivity) : ViewModel() {
    val repo = repo()
    private val elements = MutableLiveData<List<htmlData>>()
    val pageData: LiveData<List<htmlData>>
        get() = elements
    val tabsName = listOf(
        "Business",
        "Ecommerce",
        "Education",
        "Food",
        "Matrimony",
        "Medical",
        "Politics",
        "Real Estates",
        "Recruitment",
        "Tours & Travels"
    )
    val vpAdapter = vpAdapter(
        context, listOf(
            Business(),
            Ecommerce(),
            Education(),
            Food(),
            Matrimony(),
            Medical(),
            Politics(),
            realEstates(),
            Recruitment(),
            toursTravels()
        )
    )

    init {
        viewModelScope.launch {
            callingWebsite()
        }
    }

    suspend fun callingWebsite() {
        val result = repo.gethtml()
        if (result.isSuccessful && result.body() != null) {
            val htmlResponse = Jsoup.parse(result.body())
            val tables = htmlResponse.select("tbody").select("td")
            withContext(Dispatchers.Main) {
                elements.value = filter(tables)
            }
        } else {
            //
        }
    }

    fun filter(elements: Elements): List<htmlData> {
        val htmlData = mutableListOf<htmlData>()
        elements.forEach {
            if (emptyCheck(it)) {
                val header = it.getElementsByClass("textheader").text()
                val sector = it.getElementsByClass("text").first().text()
                val desc = it.getElementsByClass("text")[1].text()
                val price = it.getElementsByClass("text")[2].text()
                val data = htmlData(sector, header, desc, price)
                htmlData.add(data)
            }
        }
        return htmlData
    }

    fun emptyCheck(element: Element): Boolean {
        val check = element.getElementsByClass("textheader").text()
        return check.isNotEmpty()
    }


}
