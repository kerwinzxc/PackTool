package com.tyland.tool.ui.widget;

import com.tyland.tool.entity.Channel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface IHomeView extends IView {

    void setUpdateClickAction(ActionListener listener);

    void setPackageClickAction(ActionListener listener);

    void setChannelList(List<Channel> channels);

    JButton getMoreButton();

    JButton getSubmitButton();

    String getAppNameText();

    String getSiteIdText();

    String getAgentIdText();

    String getChannelKeyText();

    String getGameIdText();

    String getGameKeyText();

    String getGameVersionText();

    String getMinSdkText();

    String getTargetSdkText();

    String getVersionCodeText();

    String getVersionNameText();
}
