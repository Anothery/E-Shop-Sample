package com.example.eshopsample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eshopsample.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            presenter = lastCustomNonConfigurationInstance as MainContract.Presenter
        }

        if (!this::presenter.isInitialized) {
            AndroidInjection.inject(this)
        }
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

}
