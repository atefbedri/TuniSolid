package com.example.tunisolid.ui.besoin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.tunisolid.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBesoin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBesoin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    protected ListView maListViewPerso;
    public FragmentBesoin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBesoin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBesoin newInstance(String param1, String param2) {
        FragmentBesoin fragment = new FragmentBesoin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_besoin, container, false);

        maListViewPerso = root.findViewById(R.id.list_besoin);

        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        // On déclare la "HashMap" qui contiendra les informations pour un item
        HashMap<String, String> item;

        // Création d'une "HashMap" pour insérer les informations du premier item de notre "ListView"
        String[] title = new String[]{
                getResources().getString(R.string.item),
                getResources().getString(R.string.item2),
                getResources().getString(R.string.item3),
                getResources().getString(R.string.item4),

                /*getResources().getString(R.string.atr2),
                getResources().getString(R.string.appui1),
                getResources().getString(R.string.appui2)*/};
        // Icones (images) des items
        String[] telephone = new String[]{
                getResources().getString(R.string.numero),
                getResources().getString(R.string.numero),
                getResources().getString(R.string.numero),
                getResources().getString(R.string.numero),
                /*String.valueOf(R.drawable.atr_forcee),
                String.valueOf(R.drawable.en_appui_faciale),
                String.valueOf(R.drawable.en_appui_faciale_bassin_haut)*/};
        String[] description = new String[]{
                getResources().getString(R.string.descjack),
                getResources().getString(R.string.desckous),
                getResources().getString(R.string.descshoes),
                getResources().getString(R.string.desckokot),
                /*String.valueOf(R.drawable.atr_forcee),
                String.valueOf(R.drawable.en_appui_faciale),
                String.valueOf(R.drawable.en_appui_faciale_bassin_haut)*/};


        // Creation des items de la liste
        for (int i = 0; i < 4; i++) {
            item = new HashMap<>();
            // Titre
            item.put("title", title[i]);

            // Telephone
            item.put("telephone", telephone[i]);

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


        return  root;
    }
}
