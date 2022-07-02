package me.cosmi.mad.practical.ui.base;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

public abstract class BaseListenerHolder<MODEL extends BaseViewModel> {

    protected final MODEL viewModel;

    public BaseListenerHolder(final MODEL viewModel) {
        this.viewModel = viewModel;
    }

    protected FragmentActivity getFragmentActivity(final View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof FragmentActivity) {
                return (FragmentActivity) context;
            }
            context = (ContextWrapper) ((ContextWrapper) context).getBaseContext();
        }

        throw new ActivityNotFoundException("Activity not found in the given view");
    }
}
