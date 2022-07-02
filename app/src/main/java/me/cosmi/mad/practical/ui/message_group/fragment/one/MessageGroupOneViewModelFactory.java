package me.cosmi.mad.practical.ui.message_group.fragment.one;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import me.cosmi.mad.practical.ui.base.BaseViewModelFactory;

public class MessageGroupOneViewModelFactory extends BaseViewModelFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MessageGroupOneViewModel.class)) {
            return (T) new MessageGroupOneViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
