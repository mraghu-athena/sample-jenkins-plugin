package org.jenkinsci.plugins.brownplugin;

import hudson.Extension;
import hudson.model.RootAction;

@Extension
public class RootActionUrl implements RootAction {

  @Override
  public String getIconFileName() {
    // TODO Auto-generated method stub
    return "clipboard.png";
  }

  @Override
  public String getDisplayName() {
    // TODO Auto-generated method stub
    return "Brownbag_URL";
  }

  @Override
  public String getUrlName() {
    return "https://www.facebook.com";
  }

}
