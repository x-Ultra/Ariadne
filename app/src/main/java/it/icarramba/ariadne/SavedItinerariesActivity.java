package it.icarramba.ariadne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import it.icarramba.ariadne.adapters.AllItinerariesAdapter;
import it.icarramba.ariadne.constants.Constants;
import it.icarramba.ariadne.control.DBManager;
import it.icarramba.ariadne.entities.Itinerary;
import it.icarramba.ariadne.mockClasses.MockServerCall;

public class SavedItinerariesActivity extends AppCompatActivity {

    private RecyclerView rv;

    //TODO, al click di un monumento in lista appare un dialog
    //con l'expanded_monument_layout'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerari_salvati);

        //inserting mock 'saved' itineraries
        (new MockServerCall(this)).mockServerCall(Constants.ItineraryType_Saved);

        //getting saved itineraries form DB
        Itinerary[] itinSaved = DBManager.getInstance(this).getItineraries(Constants.ItineraryType_Saved);

        //test if all was inserted/get correctly from DB
        int numItin = 1;
        for (Itinerary itin : itinSaved){
            System.out.println("\t\tITINERARY n." + numItin);
            itin.showInfo();
            numItin++;
            System.out.print("\n\n");
        }

        //TODO make a cool activity
        rv = findViewById(R.id.rv1);
        AllItinerariesAdapter adapter = new AllItinerariesAdapter(itinSaved,this);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }
}
