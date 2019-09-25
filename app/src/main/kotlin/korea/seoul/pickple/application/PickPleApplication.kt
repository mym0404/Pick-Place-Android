package korea.seoul.pickple.application

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import korea.seoul.pickple.common.util.*
import korea.seoul.pickple.data.api.*
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.implementation.*
import korea.seoul.pickple.data.repository.interfaces.*
import korea.seoul.pickple.ui.course.create.CourseCreateViewModel
import korea.seoul.pickple.ui.course.create.intro.CourseCreateIntroViewModel
import korea.seoul.pickple.ui.course.create.search.CourseCreateSearchViewModel
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.all_course.ShowAllCoursesViewModel
import korea.seoul.pickple.ui.course.map.MapViewModel
import korea.seoul.pickple.ui.navigation.NavigationViewModel
import korea.seoul.pickple.ui.navigation.course.NavigationCourseViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private val TAG = PickPleApplication::class.java.simpleName

    private val appModule = module {

    }

    private val utilModule = module {
        single { MapUtil() }
        single { PermissionDexterUtil() }
        single { GalleryUtil() }
        single { FileUtil(get()) }
        single { IntentUtil() }
        single { TokenUtil(get())}
        single { SPUtil(get())}
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
                .baseUrl("http://13.209.233.217:3000")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(get())
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

        single {

            val tokenUtil = get<TokenUtil>()

            return@single Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", tokenUtil.loadToken() ?: "" )
                    .build()
                debugE(TAG,"token : ${tokenUtil.loadToken()}")
                chain.proceed(request)
            }
        } bind Interceptor::class

        single {
            OkHttpClient.Builder()
                .addInterceptor(get())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        } bind OkHttpClient::class


        //APIs
        single { get<Retrofit>(Retrofit::class, named("Directions"), null).create(DirectionsAPI::class.java) } bind DirectionsAPI::class

        single { get<Retrofit>().create(CourseAPI::class.java) }
        single { get<Retrofit>().create(MainAPI::class.java) }
        single { get<Retrofit>().create(MyPageAPI::class.java) }
        single { get<Retrofit>().create(PlaceAPI::class.java) }
        single { get<Retrofit>().create(ReviewAPI::class.java) }
        single { get<Retrofit>().create(SetAPI::class.java) }
        single { get<Retrofit>().create(UserAPI::class.java) }

    }


    private val repositoryModule = module {
        //TODO Fake
//        single { FakeReviewRepository() as ReviewRepository }
//        single { FakePlaceRepository() } bind PlaceRepository::class
//        single { FakeCourseRepository(get()) } bind CourseRepository::class


        single { ReviewRepositoryImpl(get()) as ReviewRepository }
        single { PlaceRepositoryImpl(get(), get()) } bind PlaceRepository::class
        single { CourseRepositoryImpl(get()) } bind CourseRepository::class

        single { DirectionsRepositoryImpl(get()) } bind DirectionsRepository::class
        single { UserRepositoryImpl(get(),get()) } bind UserRepository::class
        single { SetRepositoryImpl(get()) } bind SetRepository::class
        single { MainRepositoryImpl(get()) } bind MainRepository::class
        single { MyPageRepositoryImpl(get()) } bind MyPageRepository::class
    }

    private val viewModelModule = module {
        viewModel { (course: Course) -> MapViewModel(get(), course) }
        viewModel { CourseCreateViewModel(get()) }
        viewModel { CourseIntroViewModel(get(), get()) }
        viewModel { CourseCreateSearchViewModel(get()) }
        viewModel { CourseCreateIntroViewModel() }
        viewModel { ShowAllCoursesViewModel(get()) }
        viewModel { NavigationViewModel(get(),get()) }
        viewModel { NavigationCourseViewModel() }
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