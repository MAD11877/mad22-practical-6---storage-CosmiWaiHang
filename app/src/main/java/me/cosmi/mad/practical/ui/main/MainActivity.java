package me.cosmi.mad.practical.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import me.cosmi.mad.practical.data.db.UserContractImpl;
import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.databinding.ActivityMainBinding;
import me.cosmi.mad.practical.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainListenerHolder> {

    /**
     * On purpose: use as a name(id) for a user model when putting value into the intent
     */
    private static final String EXT_USER = "EXT_USER";
    private final UserContractImpl database = new UserContractImpl(this);

    public static void start(final Context context, final User user) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXT_USER, user);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    protected ActivityMainBinding createViewBinding(final LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        final User user = this.get(EXT_USER);
        final MainViewModelFactory factory = new MainViewModelFactory(user);

        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @NonNull
    @Override
    protected MainListenerHolder createEventListenerHolder(final MainViewModel viewModel) {
        return new MainListenerHolder(viewModel, this.database);
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        this.viewModel.loadUserData();
        this.viewModel
                .getUserLiveData()
                .observe(this, user -> {
                    final String username = user.getName() + " - " + user.getId();

                    this.viewBinding.tvName.setText(username);
                    this.viewBinding.tvDescription.setText(user.getDescription());
                    this.viewBinding.btnFollow.setText(user.isFollowed() ? "Unfollow" : "Follow");
                });

        this.viewBinding.btnFollow.setOnClickListener(this.eventListener::btnFollow);
        this.viewBinding.btnMessage.setOnClickListener(this.eventListener::btnMessage);
    }
}