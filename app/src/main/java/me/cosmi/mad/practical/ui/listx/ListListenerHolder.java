package me.cosmi.mad.practical.ui.listx;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.ui.base.BaseListenerHolder;
import me.cosmi.mad.practical.ui.main.MainActivity;

public class ListListenerHolder extends BaseListenerHolder<ListViewModel> {

    public ListListenerHolder(final ListViewModel viewModel) {
        super(viewModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ivProfile(final View view, final User user) {
        new AlertDialog
                .Builder(view.getContext())
                .setTitle("Profile")
                .setMessage(user.getName() + " - " + user.getId())
                .setPositiveButton("View", (dialogInterface, which) -> MainActivity.start(view.getContext(), user))
                .setNegativeButton("Close", null)
                .create()
                .show();
    }
}
