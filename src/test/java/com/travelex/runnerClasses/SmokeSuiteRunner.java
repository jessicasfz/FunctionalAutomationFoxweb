package com.travelex.runnerClasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.travelex.stepDefinitions.SuiteLevelDesign;

@CucumberOptions(plugin={"pretty", "html:target/cucumber-html-report","json:target/JsonReports/cucumber-report.json"},
	dryRun=false,monochrome=true,
	features ="src/test/resources/features/", tags={"@Pluto"},glue ="com.travelex.stepDefinitions")

public class SmokeSuiteRunner extends SuiteLevelDesign {
   private TestNGCucumberRunner testNGCucumberRunner;
  
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "Smoke", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {    	
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {   	    	
        return testNGCucumberRunner.provideFeatures();      
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
    

}