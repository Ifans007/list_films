package com.ifansdev.listfilms.commonfragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class CreateFragment {

    public static CreateFragment INSTANCE = new CreateFragment();

    public void createSimpleFragment(@NonNull AppCompatActivity activity, @IdRes int idContainer, @NonNull Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(idContainer, fragment)
                .commit();
    }

    public void createErrorFragment(@NonNull AppCompatActivity activity, @IdRes int idContainer, @NonNull Throwable e) {
        ErrorsFragment errorsFragment = new ErrorsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("Throwable", e);
        errorsFragment.setArguments(bundle);

        activity.getSupportFragmentManager().beginTransaction()
                .replace(idContainer, errorsFragment)
                .commit();
    }
}
