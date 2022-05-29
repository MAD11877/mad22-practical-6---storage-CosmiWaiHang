package me.cosmi.mad.practical.ui.main;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import me.cosmi.mad.practical.data.db.UserContractImpl;
import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.ui.base.BaseListenerHolder;
import me.cosmi.mad.practical.ui.message_group.MessageGroupActivity;

public class MainListenerHolder extends BaseListenerHolder<MainViewModel> {

    private final UserContractImpl database;

    public MainListenerHolder(final MainViewModel viewModel, final UserContractImpl database) {
        super(viewModel);
        this.database = database;
    }

    public void btnFollow(final View view) {
        final User user = this.viewModel.getUser();
        user.setIsFollowed(!user.isFollowed());

        this.viewModel.loadUserData();
        Toast.makeText(
                view.getContext(),
                user.isFollowed() ? "Followed" : "Unfollowed",
                Toast.LENGTH_SHORT
        ).show();

        this.database.save(user, rowAffected -> Log.d("Database", "Total row updated: " + rowAffected));
    }

    public void btnMessage(final View view) {
        MessageGroupActivity.start(view.getContext());
    }
}
