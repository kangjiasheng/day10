package com.example.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    public CompositeDisposable compositeDisposable;

    public void setCompositeDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            synchronized (this) {
                if (compositeDisposable == null)
                    compositeDisposable = new CompositeDisposable();
            }
        }
        compositeDisposable.add(disposable);
    }

    public void removeDisposable(Disposable disposable) {
        if (compositeDisposable != null)
            compositeDisposable.remove(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
