package korea.seoul.pickple.application

import android.app.Application
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.data.repository.CourseRepository
import korea.seoul.pickple.data.repository.FakeCourseRepository
import korea.seoul.pickple.data.repository.FakePlaceRepository
import korea.seoul.pickple.data.repository.PlaceRepository
import korea.seoul.pickple.ui.course.map.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.bind
import org.koin.dsl.module

class PickPleApplication : Application() {

    private val appModule = module {

    }
    private val utilModule = module {
        single { MapUtil() }
    }
    private val repositoryModule = module {
        single { FakePlaceRepository() } bind PlaceRepository::class
        single { FakeCourseRepository(get()) } bind CourseRepository::class
    }
    private val viewModelModule = module {
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

            modules(appModule)
            modules(utilModule)
            modules(repositoryModule)
            modules(viewModelModule)

        }
    }
}