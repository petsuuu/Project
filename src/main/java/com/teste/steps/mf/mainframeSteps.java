package com.serasa.steps.mf;

import org.junit.Assert;

import com.serasa.common.utils.mainframe.Mainframe;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class mainframeSteps {

	@Given("^I am connected in session \"([^\"]*)\", address \"([^\"]*)\"$")
	public void iAmConnectedInSessionAddress(String session, String address) {
		Mainframe.mfConnect(session, address, 10);
	}
	
	@When("^I access the \"([^\"]*)\" environment$")
	public void iAccessTheEnvironment(String env) {
		Mainframe.setText(23, 7, env);
		Mainframe.Enter();
	}
	
	@Then("^I am able to see the text \"([^\"]*)\" at row \"([^\"]*)\" and column \"([^\"]*)\"$")
	public void iAmAbleToSeeTheTextAtRowAndColumn(String text, int row, int col) {
		Assert.assertEquals(text, Mainframe.getText(row, col, text.length()));
	}
	
	@After
	public void takeDown() {
		Mainframe.disconnect();
	}
}
