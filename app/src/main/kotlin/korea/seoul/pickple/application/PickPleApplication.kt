package korea.seoul.pickple.application

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import korea.seoul.pickple.common.util.GalleryUtil
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.common.util.PermissionDexterUtil
import korea.seoul.pickple.data.api.DirectionsAPI
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.*
import korea.seoul.pickple.ui.course.create.CourseCreateViewModel
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.create.intro.CourseCreateIntroViewModel
import korea.seoul.pickple.ui.course.create.search.CourseCreateSearchViewModel
import korea.seoul.pickple.ui.course.intro.all_course.ShowAllCoursesViewModel
import korea.seoul.pickple.ui.course.map.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class PickPleApplication : Application() {

    private val appModule = module {

    }

    private val utilModule = module {
        single { MapUtil() }
        single { PermissionDexterUtil() }
        single { GalleryUtil() }
    }

    private val apiModule = module {
        //Gson
        single<Gson> { GsonBuilder().create() }

        //Retrofit Clients
        /**
         * 이거 쓰세요
         */
        //TODO
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("서버에게 URL을 받으세요")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }

        /**
         * Google Maps Directions API에 사용되는 Retrofit Client입니다 - 명주
         */
        single<Retrofit>(qualifier = named("Directions")) {
            Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }

        //APIs
        single { get<Retrofit>(Retrofit::class, named("Directions"), null).create(DirectionsAPI::class.java) } bind DirectionsAPI::class
    }


    private val repositoryModule = module {
        single { FakeReviewRepository() as ReviewRepository }
        single { FakePlaceRepository() } bind PlaceRepository::class
        single { FakeCourseRepository(get()) } bind CourseRepository::class
        single { DirectionsRepositoryImpl(get()) } bind DirectionsRepository::class
    }

    private val viewModelModule = module {
        viewModel { (course : Course) -> MapViewModel(get(), course) }
        viewModel { CourseCreateViewModel() }
        viewModel { CourseCreateSearchViewModel() }
        viewModel { (courseId: Int) -> CourseIntroViewModel(courseId, get(), get()) }
        viewModel { CourseCreateIntroViewModel() }
        viewModel { ShowAllCoursesViewModel(get()) }
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
            modules(apiModule)

        }
    }
}