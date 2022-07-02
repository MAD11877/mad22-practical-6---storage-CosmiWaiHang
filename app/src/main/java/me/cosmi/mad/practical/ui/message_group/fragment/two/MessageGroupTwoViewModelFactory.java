package me.cosmi.mad.practical.ui.message_group.fragment.two;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import me.cosmi.mad.practical.ui.base.BaseViewModelFactory;

public class MessageGroupTwoViewModelFactory extends BaseViewModelFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MessageGroupTwoViewModel.class)) {
            return (T) new MessageGroupTwoViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
