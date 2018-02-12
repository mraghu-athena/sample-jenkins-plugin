package org.jenkinsci.plugins.brownplugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

public class SleepBuilder extends Builder {
  
  private String developer;
  
  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  private long time;

  @DataBoundConstructor
  public SleepBuilder(String developer, long time) {
    this.setTime(time);
    this.setDeveloper(developer);
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  @Override
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
      throws InterruptedException, IOException {
    listener.getLogger().println(developer +" is sleeping for: "+time+"ms.");
    Thread.sleep(time);
    return true;
  }
  
  @Extension
  public static final class DescriptorImpl extends BuildStepDescriptor<Builder>{

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {  
      // return true;
      // return false;
      return jobType == FreeStyleProject.class;
    }

    @Override
    public String getDisplayName() {
      // return super.getDisplayName();
      return "Sleep builder";
    }
    
    @SuppressWarnings("unused")
    public FormValidation doCheckDeveloper(@QueryParameter String developer) {
        if (developer == null || developer.isEmpty()) {
            return FormValidation.error("Required!");
        }
        return FormValidation.ok();
    }
    
    @SuppressWarnings("unused")
    public ListBoxModel doFillDeveloperItems(@QueryParameter String currentDeveloper) {
        List<ListBoxModel.Option> entries = new ArrayList<ListBoxModel.Option>();
        System.out.print("getting developers");
        List<String> developerNames = new ArrayList<String>();
        developerNames.add("Raghu");
        developerNames.add("Janani");
        System.out.print(String.format("developers length = %d", developerNames.size()));
        for (String developer : developerNames) {
            entries.add(new ListBoxModel.Option(developer, developer, developer.equals(currentDeveloper)));
        }
        return new ListBoxModel(entries);
    }
    

  }

}
