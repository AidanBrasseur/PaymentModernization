package com.example.paymentmodernization.InvoicesHomePage;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentmodernization.R;

import java.util.ArrayList;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.InvoicesViewHolder>
    implements Filterable {

  private ArrayList<InvoiceCard> invoiceCards;
  private ArrayList<InvoiceCard> invoiceCardsFull;
  private onItemClickListener mListener;
  private Filter invoiceFilter =
      new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
          ArrayList<InvoiceCard> filteredList = new ArrayList<>();
          if (charSequence == null || charSequence.length() == 0) {
            filteredList.addAll(invoiceCardsFull);
          } else {
            String filterPattern = charSequence.toString().toLowerCase().trim();
            for (InvoiceCard invoiceCard : invoiceCardsFull) {
              ArrayList<InvoiceItem> items = invoiceCard.getItems();

              if (!filteredList.contains(invoiceCard)) {
                if (invoiceCard.getStatus().toLowerCase().contains(filterPattern)) {
                  filteredList.add(invoiceCard);
                }
                if (invoiceCard.getSupplierToCompany().toLowerCase().contains(filterPattern)) {
                  filteredList.add(invoiceCard);
                }

                if (invoiceCard.getInvoiceId().toLowerCase().contains(filterPattern)) {
                  filteredList.add(invoiceCard);
                }

                for (InvoiceItem item : items) {
                  if (item.getDescription().toLowerCase().contains(filterPattern)
                      && !(filteredList.contains(invoiceCard))) {
                    filteredList.add(invoiceCard);
                  }
                }
              }
              // }
              // }

            }
          }

          FilterResults results = new FilterResults();
          results.values = filteredList;
          return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
          invoiceCards.clear();
          invoiceCards.addAll((ArrayList) filterResults.values);
          notifyDataSetChanged();
        }
      };

  public InvoicesAdapter(ArrayList<InvoiceCard> invoiceCards) {
    this.invoiceCards = invoiceCards;
    this.invoiceCardsFull = new ArrayList<>(invoiceCards);
  }

  public void setOnItemClickListener(onItemClickListener listener) {
    mListener = listener;
  }

  @NonNull
  @Override
  public InvoicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_card, parent, false);
    InvoicesViewHolder ivh = new InvoicesViewHolder(v, mListener);
    return ivh;
  }

  @Override
  public void onBindViewHolder(@NonNull InvoicesViewHolder holder, int position) {
    InvoiceCard currentCard = invoiceCards.get(position);

    holder.supplierToCompany.setText(currentCard.getSupplierToCompany());
    holder.delivery.setText(currentCard.getDelivery());
    holder.status.setText(currentCard.getStatus());
    String idText = "Id: " + currentCard.getInvoiceId();
    holder.id.setText(idText);
    if (holder.status.getText().toString().equals("COMPLETE")) {
      holder.status.setTextColor(Color.parseColor("#3fa657"));
      holder.status.setTypeface(null, Typeface.BOLD);
    } else if (holder.status.getText().toString().equals("NEW")) {
      holder.status.setTextColor(Color.parseColor("#EE3124"));
      holder.status.setTypeface(null, Typeface.BOLD);
    } else if (holder.status.getText().toString().equals("DELIVERED")) {
      holder.status.setTextColor(Color.parseColor("#3B5998"));

    } else {

      holder.status.setTextColor(Color.parseColor("#C750C5"));
    }
  }

  @Override
  public int getItemCount() {
    return invoiceCards.size();
  }

  @Override
  public Filter getFilter() {
    return invoiceFilter;
  }

  public ArrayList<InvoiceCard> getInvoiceCards() {
    return invoiceCards;
  }

  public interface onItemClickListener {
    void onItemClick(int position);
  }

  public static class InvoicesViewHolder extends RecyclerView.ViewHolder {
    public TextView supplierToCompany;
    public TextView delivery;
    public TextView status;
    public TextView id;

    public InvoicesViewHolder(View itemView, final onItemClickListener listener) {
      super(itemView);
      supplierToCompany = itemView.findViewById(R.id.supplierToCompany);
      delivery = itemView.findViewById(R.id.delivery);
      status = itemView.findViewById(R.id.status);
      id = itemView.findViewById(R.id.id);

      itemView.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                  listener.onItemClick(position);
                }
              }
            }
          });
    }
  }
}
