package com.tyland.tool.ui;

import com.tyland.common.Log;
import com.tyland.tool.entity.Channel;
import com.tyland.tool.ui.widget.IHomeView;
import com.tyland.tool.ui.widget.IView;
import com.tyland.tool.ui.widget.impl.HomeView;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class HomePane extends BasePane {

    public static final String UI_ENABLE_CHANGED_PROPERTY = "HomePane.UiEnableChangedProperty";
    public static final String DRAWABLE_PATH_CHANGED_PROPERTY = "HomePane.DrawablePathChangedProperty";
    public static final String APP_ID_CHANGED_PROPERTY = "HomePane.AppIdChangedProperty";
    public static final String APP_KEY_CHANGED_PROPERTY = "HomePane.AppKeyChangedProperty";
    public static final String PUBLIC_KEY_CHANGED_PROPERTY = "HomePane.PublicKeyChangedProperty";
    public static final String SECRET_KEY_CHANGED_PROPERTY = "HomePane.SecretKeyChangedProperty";
    public static final String CP_ID_CHANGED_PROPERTY = "HomePane.CpIdChangedProperty";
    public static final String CP_KEY_CHANGED_PROPERTY = "HomePane.CpKeyChangedProperty";
    public static final String PACKAGE_DEFAULT_NAME_CHANGED_PROPERTY = "HomePane.PackageDefaultNameChangedProperty";
    public static final String PACKAGE_SUFFIX_CHANGED_PROPERTY = "HomePane.PackageSuffixChangedProperty";
    public static final String PACKAGE_NAME_CHANGED_PROPERTY = "HomePane.PackageNameChangedProperty";
    public static final String PACKAGE_SELECT_CHANGED_PROPERTY = "HomePane.PackageSelectedChangedProperty";
    public static final String MESSAGE_TEXT_CHANGED_PROPERTY = "HomePane.MessageTextChangedProperty";

    public static final String MIN_SDK_TEXT_CHANGED_PROPERTY = "MorePane.MinSdkTextChangedProperty";
    public static final String TARGET_SDK_TEXT_CHANGED_PROPERTY = "MorePane.TargetSdkTextChangedProperty";
    public static final String VERSION_NAME_TEXT_CHANGED_PROPERTY = "MorePane.VersionNameTextChangedProperty";
    public static final String VERSION_CODE_TEXT_CHANGED_PROPERTY = "MorePane.VersionCodeTextChangedProperty";

    private String drawablePath;
    private String appId;
    private String appKey;
    private String publicKey;
    private String secretKey;
    private String cpId;
    private String cpKey;
    private String suffix;
    private String message;
    private String defaultPackageName;
    private String newPackageName;
    private boolean isUseSuffix;
    private boolean isUseDefaultPackage;
    private boolean isEnable = true;

    private String minSdk;
    private String targetSdk;
    private String versionCode;
    private String versionName;

    public HomePane(JFrame frame, List<Channel> channelList) {
        super(frame);
        getView().setChannelList(channelList);
        frame.getContentPane().add(view.getContentView());
    }

    @Override
    protected IView createView() {
        IHomeView view = new HomeView(win, this);
        view.setPackageClickAction(this);
        view.setUpdateClickAction(this);
        return view;
    }

    @Override
    protected IHomeView getView() {
        return (IHomeView) view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getView().getSubmitButton()) {
            returnCode = CODE_ACTION_CLICK_UPDATE;
            fireActionPerformed(ACTION_UPDATE_BUTTON_CLICK);
        } else if (e.getSource() == getView().getMoreButton()) {
            returnCode = CODE_ACTION_CLICK_PACKAGE;
            fireActionPerformed(ACTION_PACKAGE_BUTTON_CLICK);
        }
    }

    public void selectedChannelId(int id) {
        Log.iln(" id=" + id);
        firePropertyChange(CHANNEL_RADIO_CHANGED_PROPERTY, 0, id);
    }

    public void setDrawablePath(String path) {
        String old = drawablePath;
        drawablePath = path;
        firePropertyChange(DRAWABLE_PATH_CHANGED_PROPERTY, old, drawablePath);
    }

    public String getDrawablePath() {
        return drawablePath;
    }

    public void setAppIdText(String id) {
        String old = appId;
        appId = id;
        firePropertyChange(APP_ID_CHANGED_PROPERTY, old, appId);
    }

    public String getAppIdText() {
        return appId;
    }

    public void setAppKeyText(String key) {
        String old = appKey;
        appKey = key;
        firePropertyChange(APP_KEY_CHANGED_PROPERTY, old, appKey);
    }

    public String getAppKeyText() {
        return appKey;
    }

    public void setPublicKeyText(String key) {
        String old = publicKey;
        publicKey = key;
        firePropertyChange(PUBLIC_KEY_CHANGED_PROPERTY, old, publicKey);
    }

    public String getPublicKeyText() {
        return publicKey;
    }

    public void setSecretKeyText(String key) {
        String old = secretKey;
        secretKey = key;
        firePropertyChange(SECRET_KEY_CHANGED_PROPERTY, old, secretKey);
    }

    public String getSecretKeyText() {
        return secretKey;
    }

    public void setCpIdText(String id) {
        String old = cpId;
        cpId = id;
        firePropertyChange(CP_ID_CHANGED_PROPERTY, old, cpId);
    }

    public String getCpIdText() {
        return cpId;
    }

    public void setCpKeyText(String key) {
        String old = cpKey;
        cpKey = key;
        firePropertyChange(CP_KEY_CHANGED_PROPERTY, old, cpKey);
    }

    public String getCpKeyText() {
        return cpKey;
    }

    public void setPackageSuffix(String suffix) {
        String old = this.suffix;
        this.suffix = suffix;
        firePropertyChange(PACKAGE_SUFFIX_CHANGED_PROPERTY, old, this.suffix);
    }

    public String getPackageSuffix() {
        return suffix;
    }

    public void setMessage(String msg) {
        String old = message;
        message = msg;
        firePropertyChange(MESSAGE_TEXT_CHANGED_PROPERTY, old, message);
    }

    public String getMessage() {
        return message;
    }

    public void setDefaultPackageName(String text) {
        if (text != null) {
            if (text.endsWith(".")) {
                if (isUseDefaultPackage()) {
                    text = text.substring(0, text.length() - 1);
                }
            } else {
                if (isUseSuffix()) {
                    text += ".";
                }
            }
        }
        String old = defaultPackageName;
        defaultPackageName = text;
        firePropertyChange(PACKAGE_DEFAULT_NAME_CHANGED_PROPERTY, old, defaultPackageName);
    }

    public String getDefaultPackageName() {
        return defaultPackageName;
    }

    public void setNewPackageName(String text) {
        String old = newPackageName;
        newPackageName = text;
        firePropertyChange(PACKAGE_NAME_CHANGED_PROPERTY, old, newPackageName);
    }

    public String getNewPackageName() {
        return newPackageName;
    }

    public boolean isUseSuffix() {
        return isUseSuffix;
    }

    public boolean isUseDefaultPackage() {
        return isUseDefaultPackage;
    }

    public void selectedPackage(boolean useDefault, boolean useSuffix) {
        if (useDefault) {
            isUseDefaultPackage = true;
            isUseSuffix = false;
        } else {
            isUseDefaultPackage = false;
            isUseSuffix = useSuffix;
        }
        firePropertyChange(PACKAGE_SELECT_CHANGED_PROPERTY, isUseDefaultPackage, !isUseDefaultPackage);
        setDefaultPackageName(getDefaultPackageName());
    }

    public void setViewEnable(boolean enable) {
        boolean old = isEnable;
        isEnable = enable;
        firePropertyChange(UI_ENABLE_CHANGED_PROPERTY, old, isEnable);
    }

    public boolean isViewEnable() {
        return isEnable;
    }

    public void setMinSdk(String min) {
        String old = minSdk;
        minSdk = min;
        firePropertyChange(MIN_SDK_TEXT_CHANGED_PROPERTY, old, minSdk);
    }

    public String getMinSDK() {
        return minSdk;
    }

    public void setTargetSdk(String target) {
        String old = targetSdk;
        targetSdk = target;
        firePropertyChange(TARGET_SDK_TEXT_CHANGED_PROPERTY, old, targetSdk);
    }

    public String getTargetSdk() {
        return targetSdk;
    }

    public void setVersionCode(String code) {
        String old = versionCode;
        versionCode = code;
        firePropertyChange(VERSION_CODE_TEXT_CHANGED_PROPERTY, old, versionCode);
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionName(String name) {
        String old = versionName;
        versionName = name;
        firePropertyChange(VERSION_NAME_TEXT_CHANGED_PROPERTY, old, versionName);
    }

    public String getVersionName() {
        return versionName;
    }
}
