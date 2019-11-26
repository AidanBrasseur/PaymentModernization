package com.example.paymentmodernization.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentmodernization.R;

import java.util.ArrayList;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.InvoicesViewHolder> {

  private ArrayList<InvoiceCard> invoiceCards;

  public InvoicesAdapter(ArrayList<InvoiceCard> invoiceCards) {
    this.invoiceCards = invoiceCards;
  }

  @NonNull
  @Override
  public InvoicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_card, parent, false);
    InvoicesViewHolder ivh = new InvoicesViewHolder(v);
    return ivh;
  }

  @Override
  public void onBindViewHolder(@NonNull InvoicesViewHolder holder, int position) {
    InvoiceCard currentCard = invoiceCards.get(position);

    holder.supplierToCompany.setText(currentCard.getSupplierToCompany());
    holder.delivery.setText(currentCard.getDelivery());
    holder.status.setText(currentCard.getStatus());
  }

  @Override
  public int getItemCount() {
    return invoiceCards.size();
  }

  public static class InvoicesViewHolder extends RecyclerView.ViewHolder {
    public TextView supplierToCompany;
    public TextView delivery;
    public  TextView status;

    public InvoicesViewHolder(View itemView) {
      super(itemView);
      supplierToCompany = itemView.findViewById(R.id.supplierToCompany);
      delivery = itemView.findViewById(R.id.delivery);
      status = itemView.findViewById(R.id.status);
    }
  }
}
