package com.example.moviecollectionmanager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

// This dialog asks the user before deleting a movie
public class DeleteDialog extends DialogFragment {

    // Interface used to notify when the user confirms deletion
    public interface DeleteDialogListener {
        void onDeleteConfirmed();
    }
    private DeleteDialogListener listener;

    // Set the listener
    public void setDeleteDialogListener(DeleteDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Delete this movie?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if (listener != null) {
                            listener.onDeleteConfirmed();
                        }

                    }
                })
                .setNegativeButton("No", null);
        return builder.create();
    }
}