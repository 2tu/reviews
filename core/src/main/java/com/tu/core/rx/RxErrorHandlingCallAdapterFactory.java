package com.tu.core.rx;

import android.support.annotation.CheckResult;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * @author tu enum@foxmail.com
 */
public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
  private final RxJavaCallAdapterFactory original;

  private RxErrorHandlingCallAdapterFactory() {
    this.original = RxJavaCallAdapterFactory.create();
  }

  private RxErrorHandlingCallAdapterFactory(Scheduler scheduler) {
    this.original = RxJavaCallAdapterFactory.createWithScheduler(scheduler);
  }

  public static RxErrorHandlingCallAdapterFactory create() {
    return new RxErrorHandlingCallAdapterFactory();
  }

  public static RxErrorHandlingCallAdapterFactory createWithScheduler(Scheduler scheduler) {
    return new RxErrorHandlingCallAdapterFactory(scheduler);
  }

  @Override
  public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
  }

  static class RxCallAdapterWrapper implements CallAdapter<Observable<?>> {
    private final Retrofit retrofit;
    private final CallAdapter<?> wrapped;

    public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
      this.retrofit = retrofit;
      this.wrapped = wrapped;
    }

    @Override public Type responseType() {
      return wrapped.responseType();
    }

    @SuppressWarnings("unchecked") @Override public <R> Observable<?> adapt(Call<R> call) {
      return ((Observable) wrapped.adapt(call)).onErrorResumeNext(
          new Func1<Throwable, Observable>() {
            @Override public Observable call(Throwable t) {
              return Observable.error(handleError(t));
            }
          });
    }

    @CheckResult Throwable handleError(Throwable t) {
      return t;
    }
  }
}
