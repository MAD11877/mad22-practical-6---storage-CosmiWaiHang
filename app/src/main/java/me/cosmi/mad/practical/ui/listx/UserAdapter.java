package me.cosmi.mad.practical.ui.listx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.unimi.dsi.fastutil.Function;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.databinding.ItemUserBinding;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final UserListener userListener;
    private ObjectArrayList<User> userList;

    public UserAdapter(final UserListener userListener) {
        this.userListener = userListener;
        this.userList = new ObjectArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemUserBinding viewBinding = ItemUserBinding.inflate(inflater, parent, false);

        return new UserViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public void setUserList(final ObjectArrayList<User> userList) {
        this.userList = userList;
        this.notifyDataSetChanged(); // Ignore this, whole dataset changed
    }

    public interface UserListener {
        void onUserClicked(final User user);
    }

    protected class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final UserAdapter adapter;
        private final ItemUserBinding viewBinding;

        private final Function<Integer, Integer> toggleVisibility = (userId) -> {
            final String strUserId = String.valueOf(userId);
            final String lastDigit = strUserId.substring(strUserId.length() - 1);

            return "7".equals(lastDigit) ? View.VISIBLE : View.GONE;
        };

        public UserViewHolder(@NonNull final ItemUserBinding viewBinding) {
            super(viewBinding.getRoot());
            this.adapter = UserAdapter.this;
            this.viewBinding = viewBinding;
        }

        public void bind(final int position) {
            final User user = this.adapter.userList.get(position);
            final String username = user.getName() + " - " + user.getId();

            this.viewBinding.tvName.setText(username);
            this.viewBinding.tvDescription.setText(user.getDescription());
            this.viewBinding.clProfilex.setVisibility(this.toggleVisibility.get(user.getId()));
            this.itemView.setTag(user);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            final Object tag = view.getTag();

            if (!(tag instanceof User)) {
                throw new IllegalArgumentException("View Tag is not valid");
            }

            this.adapter.userListener.onUserClicked((User) view.getTag());
        }
    }
}
