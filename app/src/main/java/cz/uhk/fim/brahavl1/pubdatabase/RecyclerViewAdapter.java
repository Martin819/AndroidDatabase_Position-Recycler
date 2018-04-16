package cz.uhk.fim.brahavl1.pubdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by brahavl1 on 16.04.2018.
 */

public class RecyclerViewAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerViewAdapter.PubViewHolder>{
//    private PersonsData personsData;

//    private OnItemDeleteClickListener onItemDeleteClickListener;
    private PubData pubData;

    private OnItemDeleteClickListener onItemDeleteClickListener;


    public void setOnItemDeleteClickListener(OnItemDeleteClickListener listener){
        this.onItemDeleteClickListener = listener;

    }

    public RecyclerViewAdapter(PubData pubData) {
        this.pubData = pubData;
    }

    @Override //vytvori holdery polde poctu co se vejde vola se jenom pri inicializaci
    public PubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //taha xml

        View view = layoutInflater.inflate(R.layout.item_pub, null); //jeden radek bude vypadat jako item person xml - item_pub je ten Recycler view a v mainu se musí hodit odkaz na to

        PubViewHolder viewHolder = new PubViewHolder(view);
        return viewHolder;
    }

    //SPravnce jednoho radkiu
    @Override//cejzhe ba hajy hsen oitucu a hajynz gikderz ti bzdz davat
    public void onBindViewHolder(PubViewHolder holder, int position) {//pozice a data v holderu - do holderu se
        Pub pub = pubData.get(position);
        holder.setPub(pub); //do hodleru se hodi data co se maj dat pro dany radek
    }

    @Override
    public int getItemCount() {
        return pubData.size();
    }

    public class PubViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewPosition;

        public PubViewHolder(View itemView) {
            super(itemView);

            //tady to budeme sypat do view, takže si nejdříve vytáhneme prvky z xml - dostavame instance danych veci z xml
            textViewName= itemView.findViewById(R.id.textViewPubName);
            textViewPosition = itemView.findViewById(R.id.textViewPosition);

        }

        public void setPub (Pub pub){

            textViewName.setText(pub.getName());

            String lat = Double.toString(pub.getLat()) + "lat " + Double.toString(pub.getLon()) + "lon";
            textViewPosition.setText(lat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onItemDeleteClickListener.onItemDelete(getAdapterPosition()); //tohle by smazalo celej řádek, když by se na to kliklo
                }
            });

        }
    }
}
