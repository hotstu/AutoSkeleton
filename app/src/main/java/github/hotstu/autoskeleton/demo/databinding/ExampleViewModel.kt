package github.hotstu.autoskeleton.demo.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc
 */
class ExampleViewModel : ViewModel() {
    val dataReady: MediatorLiveData<Boolean> = MediatorLiveData()

    val data: LiveData<List<DemoItem>> =
        Observable.create<List<DemoItem>> { emitter ->
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
            }
            emitter.onNext(
                listOf(
                    DemoItem(),
                    DemoItem(),
                    DemoItem()
                )
            )
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()


    init {
        dataReady.value = false

        dataReady.addSource(data) {
            dataReady.value = true
        }
    }


    fun <T> Observable<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(
        this.toFlowable(BackpressureStrategy.LATEST)
    )


    class DemoItem {

    }

}