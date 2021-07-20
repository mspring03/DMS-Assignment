package com.example.dmsprojectforjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    public static String id;
    Context context;
    public static String editTopic;
    public static String editSubject;


    private ArrayList<MainData> arrayList;
    public static boolean editStatus = false;

    public MainAdapter(ArrayList<MainData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item__view,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        holder.tv_topic.setText(arrayList.get(position).getTv_topic());
        holder.tv_subject.setText(arrayList.get(position).getTv_subject());
        holder.tv_date.setText(arrayList.get(position).getTv_date());
        holder.tv_id.setText(arrayList.get(position).getTv_id());

        holder.itemView.setTag(position);

        holder.bt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업 메뉴
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        // 수정 클릭
                        if (item.getItemId() == R.id.action_edit) {
                            // 데이터 저장
                            editTopic = arrayList.get(position).getTv_topic();
                            editSubject = arrayList.get(position).getTv_subject();


                            editStatus = true;
                            id = arrayList.get(position).getTv_id();

                            Intent intent = new Intent(v.getContext(), AddActivity.class);
                            v.getContext().startActivity(intent);

                        } // 삭제 클릭
                        else if (item.getItemId() == R.id.action_delete) {

                            // 뷰 (서버 게시물 아이디)
                            id = arrayList.get(position).getTv_id();
                            MainActivity.deletePost(id);
                            remove(holder.getAdapterPosition());

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //holder.itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
    public void remove(int position) {
        try { // 예외사항이 생겨도 강제실행
            arrayList.remove(position);
            notifyItemRemoved(position); // 새로고침
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_date;
        protected TextView tv_topic;
        protected TextView tv_subject;
        protected Button bt_more;
        protected TextView tv_id;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_date = (TextView) itemView.findViewById(R.id.date);
            this.tv_topic= (TextView) itemView.findViewById(R.id.tv_topic);
            this.tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            this.bt_more = (Button) itemView.findViewById(R.id.bt_more);
            this.tv_id = (TextView) itemView.findViewById(R.id.tv_id);
        }
    }
}