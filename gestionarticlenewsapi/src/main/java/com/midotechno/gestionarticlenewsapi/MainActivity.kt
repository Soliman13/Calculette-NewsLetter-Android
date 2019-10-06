package com.midotechno.gestionarticlenewsapi

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.midotechno.gestionarticlenewsapi.Fragment.PrincipaleFragment
import com.midotechno.gestionarticlenewsapi.Fragment.ArticlesFragment
import com.midotechno.gestionarticlenewsapi.Fragment.NoConnectionFragment


class MainActivity : AppCompatActivity() {


    lateinit var fragment:Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val footer : BottomNavigationView = findViewById(R.id.footer_menu)

        supportFragmentManager.beginTransaction().replace(R.id.fragement1,PrincipaleFragment()).commit()

        footer.setOnNavigationItemSelectedListener {
            item -> modificationFragement(item.itemId)
        }

    }

    fun pagePrincipale() {

        //créer une instance du fragment
        fragment = PrincipaleFragment()

        //créer un transaction sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
            //replacer le précédent fragment, s'il existe
            replace(R.id.fragement1, fragment)
            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()
        //finalement, on valide la transaction

    }

    fun secondPage() {

        if ( connecterInternet(applicationContext) == false ){
            //Toast.makeText(applicationContext , "Pas de connexion internet pour afficher les news" , Toast.LENGTH_SHORT)
            //créer une instance du fragment
            fragment = NoConnectionFragment()
        }else{
            //créer une instance du fragment
            fragment = ArticlesFragment()
        }


        //créer un transaction sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
            //replacer le précédent fragment, s'il existe
            replace(R.id.fragement1, fragment)
            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()
        //finalement, on valide la transaction

    }

    private fun modificationFragement(integer: Int): Boolean {
        when (integer) {
            R.id.accueil -> pagePrincipale()

            R.id.news -> secondPage()
        }
        return true
    }



    // verification de la connextion
    // pour que ne crash pas
    fun connecterInternet(c: Context): Boolean {
        val connectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
