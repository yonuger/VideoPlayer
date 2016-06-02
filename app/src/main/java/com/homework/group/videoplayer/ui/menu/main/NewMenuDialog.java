package com.homework.group.videoplayer.ui.menu.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.sql.dao.impl.MenuDaoImpl;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/31  09:37
 */
public class NewMenuDialog implements View.OnClickListener {

    private VideoMenuDelegate delegate;
    private Dialog dialog;
    private Button submit, cancel;
    private EditText title;
    private Context context;

    public NewMenuDialog(Context context, VideoMenuDelegate delegate){
        this.context = context;
        this.delegate = delegate;
        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(R.layout.view_dialog_new_menu);

        title = (EditText) dialog.findViewById(R.id.et_menu_dialog_create);

        submit = (Button) dialog.findViewById(R.id.btn_menu_dialog_create_submit);
        cancel = (Button) dialog.findViewById(R.id.btn_menu_dialog_create_cancel);

        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_menu_dialog_create_submit:
                createMenu();
                dismiss();
                delegate.refresh();
                break;
            case R.id.btn_menu_dialog_create_cancel:
                dismiss();
                break;
        }
    }

    private void createMenu() {
        MenuDao menuDao = new MenuDaoImpl(context);
        String content = title.getText().toString();
        if( content.isEmpty() ){
            Toast.makeText(context, "影单名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        menuDao.createMenu(title.getText().toString());
    }
}
