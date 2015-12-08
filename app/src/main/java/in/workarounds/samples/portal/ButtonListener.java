package in.workarounds.samples.portal;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by madki on 17/09/15.
 */
public class ButtonListener implements View.OnClickListener {
    private Context context;
    private EditText editText;

    public ButtonListener(Context context) {
        this.context = context;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    private int getIdFromET() {
        return Integer.parseInt(editText.getText().toString());
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "hi");
        switch (v.getId()) {
            case R.id.btn_open_portal:
                break;
            case R.id.btn_show_portal:
                break;
            case R.id.btn_hide_portal:
                break;
            case R.id.btn_close_portal:
                break;
            case R.id.btn_send_portal:
                break;
            case R.id.btn_send_if_portal_open:
                break;
            case R.id.btn_open_portlet:
                break;
            case R.id.btn_show_portlet:
                break;
            case R.id.btn_hide_portlet:
                break;
            case R.id.btn_close_portlet:
                break;
            case R.id.btn_send_portlet:
                break;
            case R.id.btn_close_service:
                break;
            case R.id.btn_send_to_all:
        }
    }
}
