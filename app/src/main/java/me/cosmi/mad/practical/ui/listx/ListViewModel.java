package me.cosmi.mad.practical.ui.listx;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.cosmi.mad.practical.data.domain.User;
import me.cosmi.mad.practical.ui.base.BaseViewModel;

public class ListViewModel extends BaseViewModel {

    private final MutableLiveData<ObjectArrayList<User>> userListLiveData = new MutableLiveData<>();
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public void onUserClicked(final User user) {
        this.userLiveData.postValue(user);
    }

    public MutableLiveData<ObjectArrayList<User>> getUserListLiveData() {
        return this.userListLiveData;
    }

    public void setUserListLiveData(final ObjectArrayList<User> userListLiveData) {
        this.userListLiveData.postValue(userListLiveData);
    }

    public MutableLiveData<User> getUserLiveData() {
        return this.userLiveData;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Deprecated
    protected ObjectArrayList<User> getRandUserList(final int max) {
        final ObjectArrayList<User> userList = new ObjectArrayList<>();

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final Supplier<Integer> randInt = random::nextInt;
        final Supplier<Boolean> randBoolean = random::nextBoolean;

        User user;

        for (int i = 0; i < max; i++) {
            user = new User();
            user.setName("Username");
            user.setId(Math.abs(randInt.get()));
            user.setDescription("Description - " + randInt.get());
            user.setIsFollowed(randBoolean.get());

            userList.add(user);
        }

        return userList;
    }
}
