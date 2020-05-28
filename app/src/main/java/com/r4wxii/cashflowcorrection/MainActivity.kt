package com.r4wxii.cashflowcorrection

import android.os.Bundle
import com.r4wxii.cashflowcorrection.features.accountbook.AccountBookFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,
                        AccountBookFragment()
                    )
                    .commitNow()
        }
    }
}
