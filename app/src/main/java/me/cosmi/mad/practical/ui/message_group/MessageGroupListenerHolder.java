package me.cosmi.mad.practical.ui.message_group;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

import me.cosmi.mad.practical.R;
import me.cosmi.mad.practical.ui.base.BaseListenerHolder;
import me.cosmi.mad.practical.ui.message_group.fragment.one.MessageGroupOneFragment;
import me.cosmi.mad.practical.ui.message_group.fragment.two.MessageGroupTwoFragment;

public class MessageGroupListenerHolder extends BaseListenerHolder<MessageGroupViewModel> {

    public MessageGroupListenerHolder(final MessageGroupViewModel viewModel) {
        super(viewModel);
    }

    public void btnGroupOne(final View view) {
        final FragmentActivity activity = this.getFragmentActivity(view);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContent, MessageGroupOneFragment.class, null)
                .commit();
    }

    public void btnGroupTwo(final View view) {
        final FragmentActivity activity = this.getFragmentActivity(view);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContent, MessageGroupTwoFragment.class, null)
                .commit();
    }
}
