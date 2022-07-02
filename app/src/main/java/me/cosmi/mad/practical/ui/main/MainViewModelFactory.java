package me.cosmi.mad.practical.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.ui.base.BaseViewModelFactory;

public class MainViewModelFactory extends BaseViewModelFactory {

    private final User user;

    public MainViewModelFactory(final User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(this.user);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
