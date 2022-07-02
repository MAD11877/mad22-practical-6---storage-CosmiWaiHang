package me.cosmi.mad.practical.ui.message_group.fragment.two;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import me.cosmi.mad.practical.databinding.MessageGroupTwoFragmentBinding;
import me.cosmi.mad.practical.ui.base.BaseFragment;

public class MessageGroupTwoFragment extends BaseFragment<MessageGroupTwoFragmentBinding, MessageGroupTwoViewModel, MessageGroupTwoListenerHolder> {

    @NonNull
    @Override
    protected MessageGroupTwoFragmentBinding createViewBinding(final LayoutInflater inflater, final ViewGroup container) {
        return MessageGroupTwoFragmentBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected MessageGroupTwoViewModel createViewModel() {
        final MessageGroupTwoViewModelFactory factory = new MessageGroupTwoViewModelFactory();

        return new ViewModelProvider(this, factory).get(MessageGroupTwoViewModel.class);
    }

    @NonNull
    @Override
    protected MessageGroupTwoListenerHolder createEventListenerHolder(final MessageGroupTwoViewModel viewModel) {
        return new MessageGroupTwoListenerHolder(viewModel);
    }
}