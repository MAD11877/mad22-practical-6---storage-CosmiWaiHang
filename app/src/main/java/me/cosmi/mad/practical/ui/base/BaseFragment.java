package me.cosmi.mad.practical.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<
        BINDING extends ViewBinding,
        MODEL extends BaseViewModel,
        HOLDER extends BaseListenerHolder<MODEL>> extends Fragment {

    protected BINDING viewBinding;
    protected MODEL viewModel;
    protected HOLDER eventListener;

    @NonNull
    protected abstract BINDING createViewBinding(final LayoutInflater inflater, final ViewGroup container);

    @NonNull
    protected abstract MODEL createViewModel();

    @NonNull
    protected abstract HOLDER createEventListenerHolder(final MODEL viewModel);

    @Override
    public void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.viewBinding = this.createViewBinding(inflater, container);
        this.viewModel = this.createViewModel();
        this.eventListener = this.createEventListenerHolder(this.viewModel);

        return this.viewBinding.getRoot();
    }
}