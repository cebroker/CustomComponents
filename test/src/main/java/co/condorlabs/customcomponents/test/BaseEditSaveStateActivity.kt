package co.condorlabs.customcomponents.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base_edit_save_state.*

/**
 * Created by Oscar Tigreros on 25,July,2019
 */
class BaseEditSaveStateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(InitialFragment.newInstance(), "first")
        fragmentTransaction.replace(R.id.frameContent, InitialFragment.newInstance(), "first")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()


        btnGo.setOnClickListener {
            val fragmentTransaction2 = supportFragmentManager.beginTransaction()
            fragmentTransaction2.add(InitialFragment.newInstance(), "second")
            fragmentTransaction2.replace(R.id.frameContent, secondFragment.newInstance(), "second")
            fragmentTransaction2.addToBackStack(null)
            fragmentTransaction2.commitAllowingStateLoss()
        }
    }

    companion object {
        var layout: Int = R.layout.activity_base_edit_save_state
    }

}

