package com.eriumyedu.task

import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eriumyedu.task.pojo.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String> ? = null
    internal val countrylist = ArrayList<String>()
    internal var statelist = ArrayList<String>()
    internal val citylist = ArrayList<String>()

    internal var country1=Country()
    internal var mMineUserEntity=Data()
    private var countryname=""
    private var statename=""
    private var cityname=""
    private lateinit var googleMap: GoogleMap

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

          /*  val redmiMobiles = ArrayList<String>()
            redmiMobiles.add("Redmi Y2")
            redmiMobiles.add("Redmi S2")
            redmiMobiles.add("Redmi Note 5 Pro")
            redmiMobiles.add("Redmi Note 5")
            redmiMobiles.add("Redmi 5 Plus")
            redmiMobiles.add("Redmi Y1")
            redmiMobiles.add("Redmi 3S Plus")*/

           /* val micromaxMobiles = ArrayList<String>()
            micromaxMobiles.add("Micromax Bharat Go")
            micromaxMobiles.add("Micromax Bharat 5 Pro")
            micromaxMobiles.add("Micromax Bharat 5")
            micromaxMobiles.add("Micromax Canvas 1")
            micromaxMobiles.add("Micromax Dual 5")*/

          /*  val appleMobiles = ArrayList<String>()
            appleMobiles.add("iPhone 8")
            appleMobiles.add("iPhone 8 Plus")
            appleMobiles.add("iPhone X")
            appleMobiles.add("iPhone 7 Plus")
            appleMobiles.add("iPhone 7")
            appleMobiles.add("iPhone 6 Plus")*/

            /*val samsungMobiles = ArrayList<String>()
            samsungMobiles.add("Samsung Galaxy S9+")
            samsungMobiles.add("Samsung Galaxy Note 7")
            samsungMobiles.add("Samsung Galaxy Note 5 Dual")
            samsungMobiles.add("Samsung Galaxy S8")
            samsungMobiles.add("Samsung Galaxy A8")
            samsungMobiles.add("Samsung Galaxy Note 4")*/

            listData["country"] = countrylist
            listData["city"] = citylist
            listData["state"] = statelist
            //listData["Samsung"] = samsungMobiles

            return listData
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandableListView = findViewById(R.id.expandableListView)

        val request = RetroApicall.buildService(RetroService::class.java)
        val hashMap : HashMap<String, String> = HashMap()

        hashMap.put("app_type" , "app1")
        hashMap.put("channel_type" , "radio")
        hashMap.put("country" , "")
        val call = request.getLocatoinsdata(hashMap)


        call!!.enqueue {

            onResponse = {
                // do
                if (it.isSuccessful) {
                    try {
                        val rootJsonObject = JSONObject(it.body()!!.string())
                        val isSucess = rootJsonObject.getString("message")
                        val data = rootJsonObject.getString("data")
                        mMineUserEntity =  Gson().fromJson(data, Data::class.java)
                        if(mMineUserEntity!=null) {
                            if (mMineUserEntity.getCountry() != null) {
                                println("CHECK_RESPONSE: 0021" + mMineUserEntity.getCountry()!!.size)
                                //val arrayList = ArrayList<Country>()
                                for ((index, value) in mMineUserEntity.getCountry()!!.withIndex()) {
                                    country1= Country()
                                    country1= value!!
                                    countrylist.add(country1.getCountryName()!!)
                                    println("the element at $index is ${value!!.getCountryName()}")
                                }
                            }
                        }

                    } catch (exception: IllegalStateException) {
                        println("CHECK_RESPONSE: 2")

                    } catch (exception: JsonSyntaxException) {
                        println("CHECK_RESPONSE: 3")

                    }
                }else{
                    println("CHECK_RESPONSE: 4")

                }


            }

            onFailure = {
                // do
                println("CHECK_RESPONSE: 2")

            }

        }



        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener(OnGroupExpandListener { groupPosition ->
                Toast.makeText(applicationContext, (titleList as ArrayList<String>)[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show()
            })
            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(applicationContext, (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.", Toast.LENGTH_SHORT).show()
            }


            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(applicationContext, "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition), Toast.LENGTH_SHORT).show()

                if ((titleList as ArrayList<String>)[groupPosition].equals("country")){
                    countryname=  listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition)

                }else if((titleList as ArrayList<String>)[groupPosition].equals("state")){
                    statename =listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition)
                }else{
                    cityname =listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition)
                }

                for ((index, value) in mMineUserEntity.getCountry()!!.withIndex()) {
                    if (countryname.equals(value!!.getCountryName())){
                        val states= value.getState()
                        for ((index1, value1) in states!!.withIndex()) {
                            statelist.clear()
                            statelist.add(value1!!.getStateName()!!)
                            (adapter as CustomExpandableListAdapter).notifyDataSetChanged()
                            if (statename == value1!!.getStateName()) {
                                val citys= value1.getCity()
                                (adapter as CustomExpandableListAdapter).notifyDataSetChanged()
                                for ((index2, value2) in citys!!.withIndex()) {
                                    citylist.clear()
                                    citylist.add(value2!!.getCityName()!!)
                                    (adapter as CustomExpandableListAdapter).notifyDataSetChanged()
                                    if (cityname == value2!!.getCityName()) {
                                        val icon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_location))
                                        googleMap.addMarker(
                                            MarkerOptions()
                                                .position(LatLng(value2.getLatitude()!!.toDouble(), value2.getLongitude()!!.toDouble()))
                                                .title("Current Location")
                                                .snippet(cityname)
                                                .icon(icon)
                                        )

                                        val cameraPosition = CameraPosition.Builder()
                                            .target(LatLng(value2.getLatitude()!!.toDouble(), value2.getLongitude()!!.toDouble()))
                                            .zoom(17f)
                                            .build()
                                        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                                    }
                                }
                            }
                      }
                  }
                }
                false
            }
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap= p0!!
    }
}



fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

class CallBackKt<LocationsList>: Callback<LocationsList> {

    var onResponse: ((Response<LocationsList>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<LocationsList>, t: Throwable) {
        println("CHECK_RESPONSE: 3"+t.toString())
        onFailure?.invoke(t)

    }

    override fun onResponse(call: Call<LocationsList>, response: Response<LocationsList>) {
        onResponse?.invoke(response)
    }


}



