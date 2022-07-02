package me.cosmi.mad.practical.ui.listx;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.cosmi.mad.practical.data.db.UserContractImpl;
import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.databinding.ActivityListBinding;
import me.cosmi.mad.practical.ui.base.BaseActivity;

public class ListActivity
        extends BaseActivity<ActivityListBinding, ListViewModel, ListListenerHolder>
        implements UserAdapter.UserListener {

    public static final int NUMBER_OF_USER = 200;
    private final UserContractImpl database = new UserContractImpl(this);

    @NonNull
    @Override
    protected ActivityListBinding createViewBinding(final LayoutInflater inflater) {
        return ActivityListBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected ListViewModel createViewModel() {
        // repo
        final ListViewModelFactory factory = new ListViewModelFactory();

        return new ViewModelProvider(this, factory).get(ListViewModel.class);
    }

    @NonNull
    @Override
    protected ListListenerHolder createEventListenerHolder(final ListViewModel viewModel) {
        return new ListListenerHolder(viewModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final UserAdapter userAdapter = new UserAdapter(this);

        this.viewBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        this.viewBinding.rvContent.setAdapter(userAdapter);

        // init listener
        this.viewModel
                .getUserListLiveData()
                .observe(this, userAdapter::setUserList);
        this.viewModel
                .getUserLiveData()
                .observe(this, user -> this.eventListener.ivProfile(this.viewBinding.getRoot(), user));

        this.database.findAll(this.viewModel::setUserListLiveData);
    }

    @Override
    public void onUserClicked(final User user) {
        this.viewModel.onUserClicked(user);
    }
}