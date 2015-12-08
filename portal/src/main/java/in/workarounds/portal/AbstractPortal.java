package in.workarounds.portal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import in.workarounds.portal.util.ParamUtils;

/**
 * Created by madki on 16/09/15.
 */
public abstract class AbstractPortal extends ContextWrapper {
    public static final int RESULT_OK = Activity.RESULT_OK;
    public static final int RESULT_CANCELLED = Activity.RESULT_CANCELED;
    public static final int RESULT_DESTROYED = -2;

    public static final int STATE_HIDDEN = 1;
    public static final int STATE_ACTIVE = 2;

    protected
    @PORTAL_STATE
    int mState = STATE_HIDDEN;
    protected View mView;

    public AbstractPortal(Context base) {
        super(base);
    }

    protected void setContentView(@LayoutRes int layoutId) {
        setContentView(LayoutInflater.from(this).cloneInContext(this).inflate(layoutId, new FrameLayout(this), false));
    }

    protected void setContentView(View view) {
        this.mView = view;
    }

    /**
     * Look for a child view with the given id.  If this view has the given
     * id, return this view.
     *
     * @param id The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    @Nullable
    public View findViewById(@IdRes int id) {
        return mView.findViewById(id);
    }

    public int getState() {
        return mState;
    }

    /**
     * @return the layout params to be used while attaching the Portal to window
     */
    @NonNull
    protected WindowManager.LayoutParams getLayoutParams() {
        if (mView.getLayoutParams() instanceof WindowManager.LayoutParams) {
            return (WindowManager.LayoutParams) mView.getLayoutParams();
        } else {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            params.flags = params.flags | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
            params.format = PixelFormat.TRANSLUCENT;

            FrameLayout.LayoutParams viewParams = (FrameLayout.LayoutParams) mView.getLayoutParams();

            ParamUtils.transferMarginAndGravity(params, viewParams);
            return params;
        }
    }

    /**
     * @return the inflated view of the portal
     */
    protected View getView() {
        return mView;
    }

    protected void onCreate(Bundle bundle) {

    }

    protected void onData(Bundle data) {

    }

    @CallSuper
    protected void onResume() {
        mState = STATE_ACTIVE;
    }

    @CallSuper
    protected void onPause() {
        mState = STATE_HIDDEN;
    }

    protected void onDestroy() {

    }

    public abstract void finish();


    @IntDef({STATE_HIDDEN, STATE_ACTIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PORTAL_STATE {
    }

    public abstract void startActivityForResult(Intent intent, int requestCode);

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
