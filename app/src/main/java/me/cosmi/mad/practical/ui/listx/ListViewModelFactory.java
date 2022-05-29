package me.cosmi.mad.practical.ui.listx;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import me.cosmi.mad.practical.ui.base.BaseViewModelFactory;

public class ListViewModelFactory extends BaseViewModelFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class)) {
            return (T) new ListViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
