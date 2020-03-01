package mobile.dev.gbkgame.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.dev.gbkgame.R;

public class AdapterList extends RecyclerView.Adapter<AdapterList.AdapterViewHolder> {
    private ArrayList<historyItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewcpu;
        public TextView mTextViewhuman;

        public AdapterViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewcpu = itemView.findViewById(R.id.scCpu);
            mTextViewhuman = itemView.findViewById(R.id.scHuman);

            //tambah OnitemCLickListener
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });

        }
    }

    public AdapterList(ArrayList<historyItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        AdapterViewHolder evh = new AdapterViewHolder(v, mListener); //tambah mListener
        return evh;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        historyItem currentItem = mExampleList.get(position);

        holder.mTextViewcpu.setText(currentItem.getText1());
        holder.mTextViewhuman.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
