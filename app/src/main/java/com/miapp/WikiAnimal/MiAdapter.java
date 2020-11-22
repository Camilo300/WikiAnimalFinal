package com.miapp.WikiAnimal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.MiViewHolder> {

    String N[], C[], D[];
    int A[], M[];
    Context cont;

    public MiAdapter(Context ct, String[] nombre, String[] nombreC,String[] datos, int[] imagen, int[] marca){
        this.cont = ct;
        this.N = nombre;
        this.C = nombreC;
        this.D = datos;
        this.A = imagen;
        this.M = marca;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View v = inflater.inflate(R.layout.mifila,   parent   , false);
        return new MiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.n.setText(this.N[position]);
        holder.nc.setText(this.C[position]);
        holder.d.setText(this.D[position]);
        holder.im.setImageResource(A[position]);
        holder.m.setImageResource(M[position]);

        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return N.length;
    }

    public class MiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        TextView  n, nc, d;
        Button btn;
        ImageView im, m;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            n = itemView.findViewById(R.id.Nombre);
            nc = itemView.findViewById(R.id.NombreC);
            btn =  itemView.findViewById(R.id.boton);
            d = itemView.findViewById(R.id.Datos);
            im = itemView.findViewById(R.id.miimagen);
            m = itemView.findViewById(R.id.Marca);

            d.setVisibility(View.GONE);
        }
        public void setOnClickListener(){
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.boton:
                    Intent intent = new Intent(context, DetallesActivity.class);
                    intent.putExtra("nombre", n.getText());
                    intent.putExtra("nombreC", nc.getText());
                    intent.putExtra("datos", d.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
