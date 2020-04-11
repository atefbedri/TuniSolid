package com.example.tunisolid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tunisolid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    protected ListView maListViewPerso;

    private HomeViewModel homeViewModel;
    //private adapter_items adapterItem;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseAuth auth;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);



        // Récupération de la "ListView" créée dans le fichier activity_main.xml
        maListViewPerso = root.findViewById(R.id.listviewperso);

        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        // On déclare la "HashMap" qui contiendra les informations pour un item
        HashMap<String, String> item;

        // Création d'une "HashMap" pour insérer les informations du premier item de notre "ListView"
        String[] title = new String[]{
                getResources().getString(R.string.item),
                getResources().getString(R.string.item2),
                getResources().getString(R.string.item3),
                getResources().getString(R.string.item4)

                /*getResources().getString(R.string.atr2),
                getResources().getString(R.string.appui1),
                getResources().getString(R.string.appui2)*/};
        // Icones (images) des items
        String[] icon = new String[]{

                String.valueOf(R.drawable.kousela),
                String.valueOf(R.drawable.chaussure),
                String.valueOf(R.drawable.cocotte),
                String.valueOf(R.drawable.jacket),
                //String.valueOf(R.drawable.en_appui_faciale)
                // String.valueOf(R.drawable.en_appui_faciale_bassin_haut)
                };
        String[] description = new String[]{
                getResources().getString(R.string.desckous),
                getResources().getString(R.string.descshoes),
                getResources().getString(R.string.desckokot),
                getResources().getString(R.string.desckokot),

                // /String.valueOf(R.drawable.en_appui_faciale_bassin_haut)
        };


        // Creation des items de la liste
        for (int i = 0; i < 4; i++) {
            item = new HashMap<>();
            // Titre
            item.put("title", title[i]);

            // Icone
            item.put("icon", icon[i]);

            //description
            item.put("description", description[i]);
            listItem.add(item);

            SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                    listItem,
                    R.layout.activity_list_item,
                    new String[]{"title", "icon","description"},
                    new int[]{R.id.titre, R.id.img,R.id.description});
            // Association de l’adapter à la liste
            maListViewPerso.setAdapter(adapter);
        }

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap item = (HashMap) maListViewPerso.getItemAtPosition(position);
                Toast.makeText(getActivity(), "" + item.get("title"), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }





}
