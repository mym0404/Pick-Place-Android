package korea.seoul.pickple.application

import android.app.Application
import korea.seoul.pickple.ui.course.map.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class PickPleApplication : Application() {

    private val appModule = module {

        viewModel<MapViewModel>()

    }


    override fun onCreate() {
        super.onCreate()

        inject()
    }

    /**
     * Inject for Koin
     */
    private fun inject() {
        startKoin {

            androidLogger(Level.ERROR)

            androidContext(this@PickPleApplication)

            modules(
                appModule
            )

        }
    }
}