package eu.sdk4ed.uom.td.analysis.artifact;

import java.util.ArrayList;

import eu.sdk4ed.uom.td.analysis.versions.Versions;

public class ProjectArtifact 
{
	private String projectName;
	private int numOfVersion;
	private String language;
	private ArrayList<Versions> versions;
	
	public ProjectArtifact ()
	{
		this.projectName = null;
		this.numOfVersion = 0;
		this.versions = new ArrayList<Versions>();
		this.language = null;
	}
	
	public void setNumOfVersions(int n)
	{
		this.numOfVersion = n;
	}
	
	public void setLanguage(String lan)
	{
		this.language = lan;
	}
	
	public String getLanguage()
	{
		return this.language;
	}
	
	public int getNumOfVersions()
	{
		return this.numOfVersion;
	}
	
	public void setprojectName(String projName)
	{
		this.projectName = projName;
	}
	
	public void setVersion(Versions v)
	{
		this.versions.add(v);
	}
	
	public String getProjectName()
	{
		return this.projectName;
	}
	
	public ArrayList<Versions> getVersions()
	{
		return versions;
	}

}
