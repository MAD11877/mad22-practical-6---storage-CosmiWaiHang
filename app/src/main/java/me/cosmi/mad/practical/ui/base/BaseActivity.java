package me.cosmi.mad.practical.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<
        BINDING extends ViewBinding,
        MODEL extends BaseViewModel,
        HOLDER extends BaseListenerHolder<MODEL>> extends AppCompatActivity {

    protected BINDING viewBinding;
    protected MODEL viewModel;
    protected HOLDER eventListener;

    @NonNull
    protected abstract BINDING createViewBinding(final LayoutInflater inflater);

    @NonNull
    protected abstract MODEL createViewModel();

    @NonNull
    protected abstract HOLDER createEventListenerHolder(final MODEL viewModel);

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        final LayoutInflater inflater = LayoutInflater.from(this);
        this.viewBinding = this.createViewBinding(inflater);

        final View view = this.viewBinding.getRoot();
        this.setContentView(view);
        this.viewModel = this.createViewModel();
        this.eventListener = this.createEventListenerHolder(this.viewModel);
    }

    protected <T extends Parcelable> T get(final String key) {
        final Intent intent = this.getIntent();
        final T parcelable = intent.getParcelableExtra(key);

        if (null == parcelable) {
            throw new NullPointerException(String.format("%s doesn't exist in intent", key));
        }

        return parcelable;
    }
}
