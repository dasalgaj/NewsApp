package com.example.newsapp.listeners;

import java.util.ArrayList;

public interface SourceListener {
    void setSource (ArrayList<String> sources);
    void setIzvor (ArrayList<String> izvor);
    void setObrisi (int obrisi);

    ArrayList<String> getSource();
    ArrayList<String> getIzvor();
    int getObrisi();
}
