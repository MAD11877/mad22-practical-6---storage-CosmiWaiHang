package me.cosmi.mad.practical.ui.message_group;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import me.cosmi.mad.practical.ui.base.BaseViewModelFactory;

public class MessageGroupViewModelFactory extends BaseViewModelFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MessageGroupViewModel.class)) {
            return (T) new MessageGroupViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
