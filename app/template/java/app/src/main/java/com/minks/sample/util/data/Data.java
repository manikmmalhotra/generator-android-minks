package <%= appPackage %>.util.data;

import androidx.lifecycle.MutableLiveData;


public class Data<T> extends MutableLiveData<T> {

    public Data() {
    }

    public Data(T t) {
        setValue(t);
    }

    public T get() {
        return this.getValue();
    }

    public void set(T t) {
        this.setValue(t);
    }
}
