package me.cosmi.mad.practical.ui.message_group;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import me.cosmi.mad.practical.databinding.ActivityMessageGroupBinding;
import me.cosmi.mad.practical.ui.base.BaseActivity;

public class MessageGroupActivity extends BaseActivity<ActivityMessageGroupBinding, MessageGroupViewModel, MessageGroupListenerHolder> {

    public static void start(final Context context) {
        final Intent intent = new Intent(context, MessageGroupActivity.class);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    protected ActivityMessageGroupBinding createViewBinding(final LayoutInflater inflater) {
        return ActivityMessageGroupBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected MessageGroupViewModel createViewModel() {
        final MessageGroupViewModelFactory factory = new MessageGroupViewModelFactory();

        return new ViewModelProvider(this, factory).get(MessageGroupViewModel.class);
    }

    @NonNull
    @Override
    protected MessageGroupListenerHolder createEventListenerHolder(final MessageGroupViewModel viewModel) {
        return new MessageGroupListenerHolder(viewModel);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewBinding.btnGroupOne.setOnClickListener(this.eventListener::btnGroupOne);
        this.viewBinding.btnGroupTwo.setOnClickListener(this.eventListener::btnGroupTwo);
    }
}