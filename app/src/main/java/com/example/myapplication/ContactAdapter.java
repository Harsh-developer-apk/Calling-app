package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    ArrayList<contactModel> contactList;
    int lastposition = -1;
    ContactAdapter(Context context, ArrayList<contactModel> contactList){
        this.context = context;
        this.contactList= contactList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.img.setImageResource(contactList.get(position).image_id);
        holder.txtName.setText(contactList.get(position).name);
        holder.txtNumber.setText(contactList.get(position).number);
        holder.contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent call = new Intent(Intent.ACTION_CALL);
              call.setData(Uri.parse("tel:"+contactList.get(position).number));
              view.getContext().startActivity(call);
            }
        });
     /* holder.contactButton.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
             public boolean onLongClick(View view) {
              AlertDialog.Builder builder = new AlertDialog.Builder(context)
                      .setTitle("Delete Contact")
                      .setMessage("Shi main delete krdu!")
                      .setIcon(R.drawable.baseline_delete_24)
                      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              contactList.remove(position);
                              notifyItemRemoved(position);
                          }
                      }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {

                          }
                      });
              builder.show();
              return false;
          }
      });*/
        holder.contactButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               /* Dialog miniDialog = new Dialog(context);
                miniDialog.setContentView(R.layout.mini_dialog);
                Window window  = miniDialog.getWindow();
                window.setGravity(Gravity.END);
                miniDialog.show();*/
                 PopupWindow popupWindow = new PopupWindow(context);
                View popupView = LayoutInflater.from(context).inflate(R.layout.mini_dialog, null);
                popupWindow.setContentView(popupView);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    popupWindow.showAsDropDown(view,Gravity.END,0,0);
                }
                else{
                    popupWindow.showAsDropDown((view));
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                },2500);
                ImageButton delete = popupView.findViewById(R.id.deleteContact);
                ImageButton edit   = popupView.findViewById(R.id.editContact);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                .setTitle("Delete Contact")
                                .setMessage("Shi main delete krdu!")
                                .setIcon(R.drawable.baseline_delete_24)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        contactList.remove(position);
                                        notifyItemRemoved(position);
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        builder.show();
                        popupWindow.dismiss();
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                          Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_contact);
                EditText Name = dialog.findViewById(R.id.personName);
                EditText Number = dialog.findViewById(R.id.personNumber);
          //      EditText Email  = dialog.findViewById(R.id.personEmail);
                ImageButton update = dialog.findViewById(R.id.save);
                ImageButton discard = dialog.findViewById(R.id.notsave);
                Name.setText((contactList.get(position).name));
                Number.setText(contactList.get(position).number);
             //   Email.setText(contactList.get(position).email);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String PersonName,PersonNumber;
                        String PersonEmail;
                        String blank = "";
                        if(!(Name.getText().toString().equals(blank))){
                            PersonName =  Name.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Enter a valid name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(!Number.getText().toString().equals(blank)){
                            PersonNumber = Number.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Enter a valid number", Toast.LENGTH_SHORT).show();
                            return;
                        }
           /*   if(!Email.getText().toString().equals(blank)){
                   PersonEmail = Email.getText().toString();
               }*/
              contactList.set(position,new contactModel(R.drawable.ic_launcher_foreground,PersonName,PersonNumber));
              notifyItemChanged(position);
                        Toast.makeText(context, "Contact Updated", Toast.LENGTH_SHORT).show();
              dialog.dismiss();
                    }
                });
                discard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                    }
                });
                return true;
            }
        });
      setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public  void filterList(ArrayList<contactModel> filteredList){
        contactList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtNumber;
        CircleImageView img;
       // EditText Email;
        LinearLayout contactButton;
         public ViewHolder(View item){
             super(item);
             img = item.findViewById(R.id.ContactImage);
             txtName = item.findViewById(R.id.ContactName);
             txtNumber = item.findViewById(R.id.ContactNumber);
             contactButton = item.findViewById(R.id.ContactButton);
           //  Email = item.findViewById(R.id.personEmail);
         }
    }
    private void setAnimation(View view, int position){
        if(position>lastposition){
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(slideIn);
        lastposition = position;}
    }
}
