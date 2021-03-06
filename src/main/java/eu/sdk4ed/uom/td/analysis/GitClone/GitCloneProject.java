package eu.sdk4ed.uom.td.analysis.GitClone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import eu.sdk4ed.uom.td.analysis.preperation.deletePreAnalysedData;

// Clone Project from Git
public class GitCloneProject 
{
	private String cloneProjectPath;

	public GitCloneProject()
	{
		this.cloneProjectPath = null;
	}

	public String getProjectPath()
	{
		return this.cloneProjectPath;
	}

	// Clone Git Projects with and without credentials
	public boolean cloneCommits(String jarLocation, String username, String password, ArrayList<String> sha, String git, String projectName, int version) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException, IOException
	{
		this.cloneProjectPath = jarLocation + "/Projects/" + projectName;

		int ver = 0;

		// Delete files from previous analysis thta left by mistake
		deletePreAnalysedData del = new deletePreAnalysedData();
		File directory = new File(this.cloneProjectPath);
		del.deleteSourceCode(directory);

		// Public repository, no need for authorization
		if (password.length() < 2)
		{
			for (int i = 0; i < sha.size(); i++)
			{
				ver = i;
				if (sha.size() == 1)
					ver = version - 1;
				//Clone repository
				try (Git result = Git.cloneRepository()
						.setURI(git)
						.setDirectory(new File(this.cloneProjectPath + "/" + projectName + ver))
						.call()) {
					
					result.checkout().setName(sha.get(i)).call();
					
					System.out.println("Cloned Public repository: " + result.getRepository().getDirectory());
				}

			}
		}
		//Private repository, need of authorization
		else
		{	
			for (int i = 0; i < sha.size(); i++)
			{
				ver = i;
				if (sha.size() == 1)
					ver = version - 1;
				// Clone repository in folder for each version, with credentials
				try (Git result = Git.cloneRepository()
						.setURI(git)
						.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
						.setDirectory(new File(this.cloneProjectPath + "/" + projectName + ver))
						.call()) {

					result.checkout().setName(sha.get(i)).call();

					System.out.println("Cloned Private repository: " + result.getRepository().getDirectory());
				}
			}
		}
		
		return true;
	}

}
