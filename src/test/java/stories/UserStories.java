package stories;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.*;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;

import steps.CreateUserSteps;

import org.jbehave.core.configuration.*;
	
public class UserStories extends JUnitStories{

	@Override
	public Configuration configuration(){
		Class<? extends Embeddable>	embeddableClass = this.getClass(); 
		LoadFromClasspath storyLoader = new LoadFromClasspath(embeddableClass);
		StoryReporterBuilder storyBuilder = new StoryReporterBuilder()
				.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
				.withDefaultFormats()
				.withFormats(CONSOLE, TXT, HTML, XML);
		return new MostUsefulConfiguration()
				.useStoryLoader(storyLoader)
				.useStoryReporterBuilder(storyBuilder);
	}
	
	@Override
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration(), 
				new CreateUserSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				codeLocationFromClass(this.getClass()), "**/*.story", 
						"**/excluded*.story");
	}

}
