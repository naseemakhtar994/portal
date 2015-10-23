package in.workarounds.samples.portal;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import in.workarounds.portal.Portal;
import in.workarounds.portal.PortalManager;
import in.workarounds.portal.Portlet;

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
                Portal.with(context).type(TestPortal.class).open();
                break;
            case R.id.btn_show_portal:
                Portal.with(context).show();
                break;
            case R.id.btn_hide_portal:
                Portal.with(context).hide();
                break;
            case R.id.btn_close_portal:
                Portal.with(context).close();
                break;
            case R.id.btn_send_portal:
                Portal.with(context).type(TestPortal.class).data(bundle).send();
            case R.id.btn_open_portlet:
                Portlet.with(context).id(getIdFromET()).type(TestPortlet.class).open();
                break;
            case R.id.btn_show_portlet:
                Portlet.with(context).id(getIdFromET()).show();
                break;
            case R.id.btn_hide_portlet:
                Portlet.with(context).id(getIdFromET()).hide();
                break;
            case R.id.btn_close_portlet:
                Portlet.with(context).id(getIdFromET()).close();
                break;
            case R.id.btn_send_portlet:
                Portlet.with(context).type(TestPortlet.class).id(getIdFromET()).data(bundle).send();
            case R.id.btn_close_service:
                PortalManager.close(context);
                break;
            case R.id.btn_send_to_all:
                PortalManager.send(context, bundle);
        }
    }
}
