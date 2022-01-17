package com.example.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsapp.fragments.HomeFragment;
import com.example.newsapp.fragments.SettingsFragment;
import com.example.newsapp.listeners.SourceListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter implements SourceListener {

    private ArrayList<String> izvor;
    private ArrayList<String> sources;
    private int obrisi;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment;

        switch (position)
        {
            case 1:
                fragment = new SettingsFragment();
                ((SettingsFragment)fragment).sourceListener = this;
                break;
            default:
                fragment = new HomeFragment();
                ((HomeFragment)fragment).sourceListener = this;
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    //SETTER
    @Override
    public void setSource(ArrayList<String> sources) {
        this.sources = sources;
    }

    @Override
    public void setIzvor(ArrayList<String> izvor) {
        this.izvor = izvor;
    }

    @Override
    public void setObrisi(int obrisi) {
        this.obrisi = obrisi;
    }

    //GETTER
    @Override
    public ArrayList<String> getSource() {
        return sources;
    }

    @Override
    public ArrayList<String> getIzvor() {
        return izvor;
    }

    @Override
    public int getObrisi() {
        return obrisi;
    }
}
