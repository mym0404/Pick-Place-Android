package korea.seoul.pickple.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val observers = mutableListOf<()->Unit>()

    @MainThread
    fun<T> LiveData<T>.managedObserve(observer: Observer<in T>) {
        observeForever(observer)

        observers.add {
            removeObserver(observer)
        }
    }

    @MainThread
    fun<T> LiveData<T>.managedObserve(observer: (T)->Unit) {
        observeForever {
            observer(it)
        }

        observers.add {
            removeObserver(observer)
        }
    }

    override fun onCleared() {
        super.onCleared()

        observers.forEach { it() }
    }
}