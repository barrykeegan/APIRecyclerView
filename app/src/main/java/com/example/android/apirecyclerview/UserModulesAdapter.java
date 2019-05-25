package com.example.android.apirecyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModulesAdapter extends RecyclerView.Adapter<UserModulesAdapter.ViewHolder> {
    private List<Object> modules;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView module_name;
        public TextView course_name;
        public TextView lecturer;
        public TextView start;
        public TextView end;
        public View layout;
        public ImageView avatar;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            module_name = v.findViewById(R.id.tv_module_name);
            course_name = v.findViewById(R.id.tv_course_name);
            lecturer = v.findViewById(R.id.tv_lecturer_name);
            start = v.findViewById(R.id.tv_start_date);
            end = v.findViewById(R.id.tv_end_date);
            avatar = v.findViewById(R.id.iv_lecturer_avatar);
        }
    }

    @Override
    public UserModulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View moduleView = inflater.inflate(R.layout.user_module, parent, false);
        ViewHolder module = new ViewHolder(moduleView);
        return module;
    }

    public UserModulesAdapter(List<Object> dataset)
    {
        modules = dataset;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Map module = (HashMap) modules.get(position);
        holder.module_name.setText(module.get("Module_Code") + " - " + module.get("Module_Name"));
        holder.course_name.setText(module.get("Course") + " - " + module.get("Course_Intake"));
        holder.lecturer.setText(module.get("Lecturer").toString());
        holder.start.setText(module.get("From").toString());
        holder.end.setText(module.get("To").toString());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try
        {
            URL url = new URL( MyApplication.getAppContext().getResources().getString(R.string.api_url)
                                + module.get("Lecturer_Avatar").toString());
            Bitmap avatar = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.avatar.setImageBitmap(avatar);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }
}
