package com.example.paymentmodernization.InvoiceDetails;

public class InvoiceDetailsPresenter implements InvoiceDetailsInteractor.OnStatusUpdatedListener{
    private InvoiceDetailsView invoiceDetailsView;
    private InvoiceDetailsInteractor invoiceDetailsInteractor;

    InvoiceDetailsPresenter
            (InvoiceDetailsView invoiceDetailsView, InvoiceDetailsInteractor invoiceDetailsInteractor) {
       this.invoiceDetailsInteractor = invoiceDetailsInteractor;
       this.invoiceDetailsView = invoiceDetailsView;
    }

    public void updateStatus(String invoiceId, String authToken, String newStatus) {
        invoiceDetailsInteractor.updateStatus(invoiceId, authToken, newStatus, this);

    }

    @Override
    public void onSuccess(String newStatus) {
        invoiceDetailsView.statusUpdatedSuccess(newStatus);
    }

    @Override
    public void onFailure(String newStatus) {
        //TODO handleFailure
    }
}
