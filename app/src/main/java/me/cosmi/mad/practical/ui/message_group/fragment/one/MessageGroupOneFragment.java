package me.cosmi.mad.practical.ui.message_group.fragment.one;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import me.cosmi.mad.practical.databinding.MessageGroupOneFragmentBinding;
import me.cosmi.mad.practical.ui.base.BaseFragment;

public class MessageGroupOneFragment extends BaseFragment<MessageGroupOneFragmentBinding, MessageGroupOneViewModel, MessageGroupOneListenerHolder> {

    @NonNull
    @Override
    protected MessageGroupOneFragmentBinding createViewBinding(final LayoutInflater inflater, final ViewGroup container) {
        return MessageGroupOneFragmentBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected MessageGroupOneViewModel createViewModel() {
        final MessageGroupOneViewModelFactory factory = new MessageGroupOneViewModelFactory();

        return new ViewModelProvider(this, factory).get(MessageGroupOneViewModel.class);
    }

    @NonNull
    @Override
    protected MessageGroupOneListenerHolder createEventListenerHolder(final MessageGroupOneViewModel viewModel) {
        return new MessageGroupOneListenerHolder(viewModel);
    }
}